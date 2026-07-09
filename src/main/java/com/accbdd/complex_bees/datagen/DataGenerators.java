package com.accbdd.complex_bees.datagen;

import com.accbdd.complex_bees.datagen.bee.ComplexCombs;
import com.accbdd.complex_bees.datagen.bee.ComplexFlowers;
import com.accbdd.complex_bees.datagen.bee.ComplexMutations;
import com.accbdd.complex_bees.datagen.bee.ComplexSpecies;
import com.accbdd.complicated_bees.bees.Comb;
import com.accbdd.complicated_bees.registry.EsotericRegistration;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.accbdd.complex_bees.ComplexBees.MODID;


public class DataGenerators {

    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        BlockTagGenerator blockTagGenerator = new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagGenerator);
        generator.addProvider(event.includeServer(), new ItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new RecipeGenerator(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ComplexCombs(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ComplexFlowers(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ComplexMutations(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ComplexSpecies(packOutput, lookupProvider, existingFileHelper));
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

    public static TagKey<Item> itemTag(String modId, String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(modId, name));
    }
}
