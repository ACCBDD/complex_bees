package com.accbdd.complex_bees.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.accbdd.complex_bees.datagen.DataGenerators.itemTag;

public class ItemTagGenerator extends ItemTagsProvider {
    public static final TagKey<Item> RAW_LEAD = itemTag("c", "raw_materials/lead");
    public static final TagKey<Item> RAW_OSMIUM = itemTag("c", "raw_materials/osmium");
    public static final TagKey<Item> RAW_SILVER = itemTag("c", "raw_materials/silver");
    public static final TagKey<Item> RAW_TIN = itemTag("c", "raw_materials/tin");
    public static final TagKey<Item> RAW_URANIUM = itemTag("c", "raw_materials/uranium");
    public static final TagKey<Item> RAW_ZINC = itemTag("c", "raw_materials/zinc");

    public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, blockTagProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
