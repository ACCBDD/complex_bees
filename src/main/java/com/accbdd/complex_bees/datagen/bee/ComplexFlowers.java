package com.accbdd.complex_bees.datagen.bee;

import com.accbdd.complex_bees.datagen.BlockTagGenerator;
import com.accbdd.complicated_bees.bees.Flower;
import com.accbdd.complicated_bees.registry.FlowerRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.JsonCodecProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.accbdd.complex_bees.ComplexBees.MODID;

public class ComplexFlowers extends JsonCodecProvider<Flower> {
    public static final List<FlowerEntry> FLOWERS = new ArrayList<>();

    public ComplexFlowers(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, PackOutput.Target.DATA_PACK, "complicated_bees/flower", PackType.SERVER_DATA, FlowerRegistration.CODEC, lookupProvider, MODID, existingFileHelper);
    }

    public static final FlowerEntry ANDESITE = flower("andesite", Blocks.ANDESITE, BlockTagGenerator.ANDESITE);

    protected static FlowerEntry flower(String path, TagKey<Block> tag) {
        FlowerEntry flower = new FlowerEntry(path, tag);
        FLOWERS.add(flower);
        return flower;
    }

    protected static FlowerEntry flower(String path, Block block, TagKey<Block> tag) {
        FlowerEntry flower = new FlowerEntry(path, tag);
        FLOWERS.add(flower);
        return flower;
    }

    @Override
    protected void gather() {
        for (FlowerEntry entry : FLOWERS) {
            unconditional(entry.key.location(), entry.flower);
        }
    }

    public record FlowerEntry(ResourceKey<Flower> key, Flower flower, TagKey<Block> tag) {
        public FlowerEntry(String path, TagKey<Block> tag) {
            this(ResourceKey.create(FlowerRegistration.FLOWER_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(MODID, path)), new Flower(List.of(), List.of(tag)), tag);
        }

        public FlowerEntry(String path, Flower flower, TagKey<Block> tag) {
            this(ResourceKey.create(FlowerRegistration.FLOWER_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(MODID, path)), flower, tag);
        }
    }
}
