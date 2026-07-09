package com.accbdd.complex_bees.datagen.bee;

import com.accbdd.complicated_bees.bees.Flower;
import com.accbdd.complicated_bees.bees.Species;
import com.accbdd.complicated_bees.bees.mutation.Mutation;
import com.accbdd.complicated_bees.bees.mutation.condition.IMutationCondition;
import com.accbdd.complicated_bees.registry.FlowerRegistration;
import com.accbdd.complicated_bees.registry.MutationRegistration;
import com.accbdd.complicated_bees.registry.SpeciesRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.*;

import static com.accbdd.complex_bees.datagen.DataGenerators.loc;

public class ComplexBeeDataProvider {

    public static final Map<ResourceKey<Flower>, Flower> FLOWERS = new HashMap<>();
    public static final Map<ResourceKey<Species>, Species> SPECIES = new HashMap<>();
    public static final Map<ResourceKey<Mutation>, Mutation> MUTATIONS = new HashMap<>();

    protected static Map.Entry<ResourceKey<Flower>, Flower> flower(String path, List<Block> blocks, List<TagKey<Block>> tags) {
        List<ResourceLocation> blockLocations = blocks.stream().map(BuiltInRegistries.BLOCK::getKey).toList();
        Flower flower = new Flower(blockLocations, tags);
        ResourceKey<Flower> key = ResourceKey.create(FlowerRegistration.FLOWER_REGISTRY_KEY, loc(path));
        FLOWERS.put(key, flower);
        return new AbstractMap.SimpleEntry<>(key, flower);
    }

    protected static Map.Entry<ResourceKey<Species>, Species> species(Species.Builder builder) {
        Species species = builder.build();
        ResourceKey<Species> key = ResourceKey.create(SpeciesRegistration.SPECIES_REGISTRY_KEY, species.builderOverride);
        SPECIES.put(key, species);
        return new AbstractMap.SimpleEntry<>(key, species);
    }

    protected static Map.Entry<ResourceKey<Mutation>, Mutation> mutation(String path, ResourceKey<Species> first, ResourceKey<Species> second, ResourceKey<Species> result, float chance, IMutationCondition... conditions) {
        Mutation mutation = new Mutation(first.location(), second.location(), result.location(), chance, Arrays.stream(conditions).toList());
        ResourceKey<Mutation> key = ResourceKey.create(MutationRegistration.MUTATION_REGISTRY_KEY, loc(path));
        MUTATIONS.put(key, mutation);
        return new AbstractMap.SimpleEntry<>(key, mutation);
    }
}
