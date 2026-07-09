package com.accbdd.complex_bees.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.accbdd.complex_bees.ComplexBees.MODID;

public class BlockTagGenerator extends BlockTagsProvider {
    public static final TagKey<Block> ANDESITE = blockTag("c", "andesite");
    public static final TagKey<Block> TIN = blockTag("c", "storage_blocks/tin");
    public static final TagKey<Block> OSMIUM = blockTag("c", "storage_blocks/osmium");
    public static final TagKey<Block> LEAD = blockTag("c", "storage_blocks/lead");
    public static final TagKey<Block> SILVER = blockTag("c", "storage_blocks/silver");
    public static final TagKey<Block> ZINC = blockTag("c", "storage_blocks/zinc");
    public static final TagKey<Block> URANIUM = blockTag("c", "storage_blocks/uranium");

    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }

    public static TagKey<Block> blockTag(String modId, String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(modId, name));
    }
}
