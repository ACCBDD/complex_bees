package com.accbdd.complex_bees.datagen;

import com.accbdd.complex_bees.ComplexBees;
import com.accbdd.complex_bees.datagen.bee.*;
import com.accbdd.complicated_bees.bees.Comb;
import com.accbdd.complicated_bees.bees.Flower;
import com.accbdd.complicated_bees.bees.Species;
import com.accbdd.complicated_bees.bees.mutation.Mutation;
import com.accbdd.complicated_bees.registry.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.accbdd.complex_bees.ComplexBees.MODID;


public class DataGenerators {

    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new RecipeGenerator(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ComplexCombs(packOutput, lookupProvider, existingFileHelper));

        //instantiate utility classes for datagen
        ComplexBees.LOGGER.info("flowers: {}, species: {}, mutations: {}", new ComplexFlowers(), new ComplexSpecies(), new ComplexMutations());
        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, new RegistrySetBuilder().add(
                FlowerRegistration.FLOWER_REGISTRY_KEY,
                bootstrap -> {
                    for (Map.Entry<ResourceKey<Flower>, Flower> entry : ComplexBeeDataProvider.FLOWERS.entrySet())
                        bootstrap.register(
                                entry.getKey(),
                                entry.getValue()
                        );
                }
        ).add(
                SpeciesRegistration.SPECIES_REGISTRY_KEY,
                bootstrap -> {
                    for (Map.Entry<ResourceKey<Species>, Species> entry : ComplexBeeDataProvider.SPECIES.entrySet())
                        bootstrap.register(
                                entry.getKey(),
                                entry.getValue()
                        );
                }
        ).add(
                MutationRegistration.MUTATION_REGISTRY_KEY,
                bootstrap -> {
                    ComplexMutations.generateMutations();
                    for (Map.Entry<ResourceKey<Mutation>, Mutation> entry : ComplexBeeDataProvider.MUTATIONS.entrySet()) {
                        bootstrap.register(
                                entry.getKey(),
                                entry.getValue()
                        );
                    }
                }
        ), Set.of(MODID)));
    }

    public static ResourceLocation loc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static ItemStack stack(ComplexCombs.CombEntry combEntry) {
        ItemStack stack = new ItemStack(ItemsRegistration.COMB.get(), 1);
        stack.set(EsotericRegistration.COMB_TYPE.get(), combEntry.key().location());
        return stack;
    }

    public static ItemStack stack(Map.Entry<ResourceKey<Comb>, Comb> combEntry) {
        ItemStack stack = new ItemStack(ItemsRegistration.COMB.get(), 1);
        stack.set(EsotericRegistration.COMB_TYPE.get(), combEntry.getKey().location());
        return stack;
    }

    public static TagKey<Block> blockTag(String modId, String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(modId, name));
    }

    public static TagKey<Item> itemTag(String modId, String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(modId, name));
    }
}
