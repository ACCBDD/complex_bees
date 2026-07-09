package com.accbdd.complex_bees.datagen.bee;

import com.accbdd.complicated_bees.bees.Product;
import com.accbdd.complicated_bees.bees.Species;
import com.accbdd.complicated_bees.bees.gene.*;
import com.accbdd.complicated_bees.bees.gene.enums.*;
import com.accbdd.complicated_bees.datagen.builtin.Combs;
import com.accbdd.complicated_bees.datagen.builtin.Flowers;
import com.accbdd.complicated_bees.registry.GeneRegistration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

import static com.accbdd.complex_bees.ComplexBees.MODID;
import static com.accbdd.complex_bees.datagen.DataGenerators.loc;
import static com.accbdd.complex_bees.datagen.DataGenerators.stack;
import static com.accbdd.complex_bees.datagen.bee.ComplexBeeDataProvider.species;

public class ComplexSpecies {
    public static final List<ResourceLocation> RED_MODELS = getModelList("complicated_bees", "red");
    public static final List<ResourceLocation> GRAY_MODELS = getModelList("complicated_bees", "gray");
    public static final List<ResourceLocation> ENDER_MODELS = getModelList("complicated_bees", "ender");
    public static final List<ResourceLocation> JAZZY_MODELS = getModelList("complicated_bees", "jazzy");
    public static final List<ResourceLocation> TRICKY_MODELS = getModelList("complicated_bees", "tricky");

    public static final Map.Entry<ResourceKey<Species>, Species> STANNUM = species(new Species.Builder(loc("stannum"))
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
            .gene(GeneRegistration.ACTIVE_TIME, new GeneActiveTime(EnumActiveTime.DIURNAL, true))
            .gene(GeneRegistration.CAVE_DWELLING, new GeneBoolean(true, true))
            .gene(GeneRegistration.FERTILITY, new GeneFertility(3, false))
    );

    private static List<ResourceLocation> getModelList(String name) {
        return getModelList(MODID, name);
    }

    private static List<ResourceLocation> getModelList(String namespace, String name) {
        return List.of(ResourceLocation.fromNamespaceAndPath(namespace, "bee/" + name + "_drone"),
                ResourceLocation.fromNamespaceAndPath(namespace, "bee/" + name + "_princess"),
                ResourceLocation.fromNamespaceAndPath(namespace, "bee/" + name + "_queen"));
    }
}
