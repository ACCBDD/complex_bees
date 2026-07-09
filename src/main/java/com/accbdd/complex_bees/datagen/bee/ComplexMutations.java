package com.accbdd.complex_bees.datagen.bee;

import com.accbdd.complex_bees.datagen.BlockTagGenerator;
import com.accbdd.complex_bees.datagen.ItemTagGenerator;
import com.accbdd.complicated_bees.bees.Species;
import com.accbdd.complicated_bees.bees.mutation.Mutation;
import com.accbdd.complicated_bees.bees.mutation.condition.BlockTagUnderCondition;
import com.accbdd.complicated_bees.bees.mutation.condition.IMutationCondition;
import com.accbdd.complicated_bees.datagen.builtin.BuiltInSpecies;
import com.accbdd.complicated_bees.registry.MutationRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.JsonCodecProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.accbdd.complex_bees.ComplexBees.MODID;
import static com.accbdd.complex_bees.datagen.DataGenerators.loc;

public class ComplexMutations extends JsonCodecProvider<Mutation> {
    public static final List<MutationEntry> MUTATIONS = new ArrayList<>();

    public ComplexMutations(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, PackOutput.Target.DATA_PACK, "complicated_bees/mutation", PackType.SERVER_DATA, Mutation.MUTATION_CODEC, lookupProvider, MODID, existingFileHelper);
        mutation("metallic/stannum",
                ItemTagGenerator.RAW_TIN,
                BuiltInSpecies.FERROUS.getKey(),
                BuiltInSpecies.CUPROUS.getKey(),
                ComplexSpecies.STANNUM.key(),
                0.10f,
                new BlockTagUnderCondition(BlockTagGenerator.TIN));
        mutation("metallic/osmium",
                ItemTagGenerator.RAW_OSMIUM,
                BuiltInSpecies.FERROUS.getKey(),
                BuiltInSpecies.DILIGENT.getKey(),
                ComplexSpecies.OSMIUM.key(),
                0.10f,
                new BlockTagUnderCondition(BlockTagGenerator.OSMIUM));
        mutation("metallic/zincum",
                ItemTagGenerator.RAW_ZINC,
                BuiltInSpecies.CUPROUS.getKey(),
                BuiltInSpecies.ROBUST.getKey(),
                ComplexSpecies.ZINCUM.key(),
                0.12f,
                new BlockTagUnderCondition(BlockTagGenerator.ZINC));
        mutation("metallic/radioactive",
                ItemTagGenerator.RAW_URANIUM,
                BuiltInSpecies.CUPROUS.getKey(),
                BuiltInSpecies.CONDUCTIVE.getKey(),
                ComplexSpecies.RADIOACTIVE.key(),
                0.08f,
                new BlockTagUnderCondition(BlockTagGenerator.URANIUM));
        mutation("metallic/argentum",
                ItemTagGenerator.RAW_SILVER,
                BuiltInSpecies.PRECIOUS.getKey(),
                BuiltInSpecies.RESILIENT.getKey(),
                ComplexSpecies.ARGENTUM.key(),
                0.08f,
                new BlockTagUnderCondition(BlockTagGenerator.SILVER));
        mutation("metallic/plumbum",
                ItemTagGenerator.RAW_LEAD,
                BuiltInSpecies.FERROUS.getKey(),
                BuiltInSpecies.ROCKY.getKey(),
                ComplexSpecies.PLUMBUM.key(),
                0.10f,
                new BlockTagUnderCondition(BlockTagGenerator.LEAD));
    }

    @Override
    protected void gather() {
        for (MutationEntry entry : MUTATIONS) {
            unconditional(entry.key.location(), entry.mutation);
        }
    }

    protected static MutationEntry mutation(String path, TagKey<Item> tag, ResourceKey<Species> first, ResourceKey<Species> second, ResourceKey<Species> result, float chance, IMutationCondition... conditions) {
        MutationEntry mutation = new MutationEntry(path, tag, first, second, result, chance, conditions);
        MUTATIONS.add(mutation);
        return mutation;
    }

    public record MutationEntry(ResourceKey<Mutation> key, Mutation mutation, TagKey<Item> tag) {
        public MutationEntry(String path, TagKey<Item> tag, ResourceKey<Species> first, ResourceKey<Species> second, ResourceKey<Species> result, float chance, IMutationCondition... conditions) {
            this(ResourceKey.create(MutationRegistration.MUTATION_REGISTRY_KEY, loc(path)),
                    new Mutation(first.location(), second.location(), result.location(), chance, Arrays.stream(conditions).toList()),
                    tag);
        }
    }
}
