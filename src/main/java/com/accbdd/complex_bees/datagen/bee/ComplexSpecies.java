package com.accbdd.complex_bees.datagen.bee;

import com.accbdd.complex_bees.datagen.ItemTagGenerator;
import com.accbdd.complex_bees.registry.ComplexEffectRegistration;
import com.accbdd.complicated_bees.bees.Product;
import com.accbdd.complicated_bees.bees.Species;
import com.accbdd.complicated_bees.bees.gene.*;
import com.accbdd.complicated_bees.bees.gene.enums.*;
import com.accbdd.complicated_bees.datagen.builtin.Combs;
import com.accbdd.complicated_bees.datagen.builtin.Flowers;
import com.accbdd.complicated_bees.registry.GeneRegistration;
import com.accbdd.complicated_bees.registry.SpeciesRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.JsonCodecProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.accbdd.complex_bees.ComplexBees.MODID;
import static com.accbdd.complex_bees.datagen.DataGenerators.loc;
import static com.accbdd.complex_bees.datagen.DataGenerators.stack;

public class ComplexSpecies extends JsonCodecProvider<Species> {
    public static final List<SpeciesEntry> SPECIES = new ArrayList<>();

    public static final List<ResourceLocation> RED_MODELS = getModelList("complicated_bees", "red");
    public static final List<ResourceLocation> GRAY_MODELS = getModelList("complicated_bees", "gray");
    public static final List<ResourceLocation> ENDER_MODELS = getModelList("complicated_bees", "ender");
    public static final List<ResourceLocation> JAZZY_MODELS = getModelList("complicated_bees", "jazzy");
    public static final List<ResourceLocation> TRICKY_MODELS = getModelList("complicated_bees", "tricky");

    public static final SpeciesEntry STANNUM = species(ItemTagGenerator.RAW_TIN, new Species.Builder(loc("stannum"))
            .dominant(true)
            .foil(false)
            .colors(0xabbebd)
            .models(GRAY_MODELS)
            .products(List.of(new Product(stack(Combs.ROCKY), 0.30f), new Product(stack(ComplexCombs.TIN), 0.15f)))
            .gene(GeneRegistration.LIFESPAN, new GeneLifespan(EnumLifespan.SHORT, true))
            .gene(GeneRegistration.TEMPERATURE, new GeneTemperature(EnumTemperature.NORMAL, EnumTolerance.UP_1, true))
            .gene(GeneRegistration.HUMIDITY, new GeneHumidity(EnumHumidity.NORMAL, EnumTolerance.NONE, true))
            .gene(GeneRegistration.PRODUCTIVITY, new GeneProductivity(EnumProductivity.AVERAGE, true))
            .gene(GeneRegistration.FLOWER, new GeneFlower(Flowers.CALCITE.getKey().location(), true))
            .gene(GeneRegistration.ACTIVE_TIME, new GeneActiveTime(EnumActiveTime.NOCTURNAL, true))
            .gene(GeneRegistration.CAVE_DWELLING, new GeneBoolean(true, true))
            .gene(GeneRegistration.FERTILITY, new GeneFertility(3, false))
    );
    public static final SpeciesEntry OSMIUM = species(ItemTagGenerator.RAW_OSMIUM, Species.Builder.of(STANNUM.species, loc("osmium"))
            .colors(0x97a7f7)
            .products(List.of(new Product(stack(Combs.ROCKY), 0.3f), new Product(stack(ComplexCombs.OSMIUM), 0.15f)))
            .gene(GeneRegistration.FLOWER, new GeneFlower(Flowers.TUFF.getKey().location(), true))
            .gene(GeneRegistration.FERTILITY, new GeneFertility(2, true))
    );
    public static final SpeciesEntry ZINCUM = species(ItemTagGenerator.RAW_OSMIUM, Species.Builder.of(STANNUM.species, loc("zincum"))
            .colors(0xbbefc1)
            .products(List.of(new Product(stack(Combs.ROCKY), 0.3f), new Product(stack(ComplexCombs.ZINC), 0.15f)))
            .gene(GeneRegistration.FLOWER, new GeneFlower(ComplexFlowers.ANDESITE.key().location(), true))
            .gene(GeneRegistration.FERTILITY, new GeneFertility(2, false))
    );
    public static final SpeciesEntry PLUMBUM = species(ItemTagGenerator.RAW_OSMIUM, Species.Builder.of(STANNUM.species, loc("plumbum"))
            .colors(0x846dba)
            .products(List.of(new Product(stack(Combs.ROCKY), 0.3f), new Product(stack(ComplexCombs.LEAD), 0.15f)))
            .gene(GeneRegistration.FLOWER, new GeneFlower(ComplexFlowers.ANDESITE.key().location(), true))
            .gene(GeneRegistration.FERTILITY, new GeneFertility(2, false))
    );
    public static final SpeciesEntry RADIOACTIVE = species(ItemTagGenerator.RAW_URANIUM, Species.Builder.of(STANNUM.species, loc("radioactive"))
            .colors(0x81c04e)
            .products(List.of(new Product(stack(Combs.ROCKY), 0.3f), new Product(stack(ComplexCombs.URANIUM), 0.15f)))
            .gene(GeneRegistration.FLOWER, new GeneFlower(Flowers.DIORITE.getKey().location(), true))
            .gene(GeneRegistration.LIFESPAN, new GeneLifespan(EnumLifespan.SHORTER, true))
            .gene(GeneRegistration.PRODUCTIVITY, new GeneProductivity(EnumProductivity.SLOW, true))
            .gene(GeneRegistration.FERTILITY, new GeneFertility(2, true))
            .gene(GeneRegistration.EFFECT, new GeneEffect(ComplexEffectRegistration.RADIOACTIVE.get(), true))
    );
    public static final SpeciesEntry ARGENTUM = species(ItemTagGenerator.RAW_SILVER, Species.Builder.of(STANNUM.species, loc("argentum"))
            .colors(0xcfcfcf)
            .products(List.of(new Product(stack(Combs.ROCKY), 0.3f), new Product(stack(ComplexCombs.SILVER), 0.15f)))
            .gene(GeneRegistration.FLOWER, new GeneFlower(ComplexFlowers.ANDESITE.key().location(), true))
            .gene(GeneRegistration.FERTILITY, new GeneFertility(2, true))
    );

    public ComplexSpecies(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, PackOutput.Target.DATA_PACK, "complicated_bees/species", PackType.SERVER_DATA, Species.CODEC, lookupProvider, MODID, existingFileHelper);
    }

    private static List<ResourceLocation> getModelList(String name) {
        return getModelList(MODID, name);
    }

    private static List<ResourceLocation> getModelList(String namespace, String name) {
        return List.of(ResourceLocation.fromNamespaceAndPath(namespace, "bee/" + name + "_drone"),
                ResourceLocation.fromNamespaceAndPath(namespace, "bee/" + name + "_princess"),
                ResourceLocation.fromNamespaceAndPath(namespace, "bee/" + name + "_queen"));
    }

    protected static SpeciesEntry species(TagKey<Item> tag, Species.Builder builder) {
        SpeciesEntry comb = new SpeciesEntry(tag, builder);
        SPECIES.add(comb);
        return comb;
    }

    @Override
    protected void gather() {
        for (SpeciesEntry entry : SPECIES) {
            unconditional(entry.key.location(), entry.species);
        }
    }

    public record SpeciesEntry(ResourceKey<Species> key, Species species, TagKey<Item> tag) {
        public SpeciesEntry(TagKey<Item> tag, Species.Builder builder) {
            this(ResourceKey.create(SpeciesRegistration.SPECIES_REGISTRY_KEY, builder.build().builderOverride), builder.build(), tag);
        }
    }
}
