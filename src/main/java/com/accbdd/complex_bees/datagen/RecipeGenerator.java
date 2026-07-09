package com.accbdd.complex_bees.datagen;

import com.accbdd.complex_bees.datagen.bee.ComplexCombs;
import com.accbdd.complicated_bees.bees.Product;
import com.accbdd.complicated_bees.recipe.CentrifugeRecipe;
import com.accbdd.complicated_bees.registry.EsotericRegistration;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static com.accbdd.complex_bees.ComplexBees.MODID;

public class RecipeGenerator extends RecipeProvider {


    public RecipeGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, completableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        super.buildRecipes(output);

        tagCombCentrifugeRecipe(output, "tin_comb", ComplexCombs.TIN);
    }

    protected static void centrifugeRecipe(RecipeOutput output, String name, Ingredient input, Product... outputs) {
        output.accept(ResourceLocation.fromNamespaceAndPath(MODID, "centrifuge/" + name),
                new CentrifugeRecipe(input, Arrays.stream(outputs).toList()),
                null);
    }

    protected static Ingredient combIngredient(ComplexCombs.CombEntry comb) {
        return DataComponentIngredient.of(false, EsotericRegistration.COMB_TYPE, comb.key().location(), ItemsRegistration.COMB.get());
    }

    protected static Product tagProduct(TagKey<Item> tag, int count, float chance) {
        return new Product(Items.AIR, count, null, tag, chance);
    }

    protected static Product tagProduct(TagKey<Item> tag,float chance) {
        return tagProduct(tag, 1, chance);
    }

    protected static void tagCombCentrifugeRecipe(RecipeOutput output, String name, ComplexCombs.CombEntry entry) {
        centrifugeRecipe(output.withConditions(new NotCondition(new TagEmptyCondition(entry.tag()))), name, combIngredient(entry),
                new Product(ItemsRegistration.BEESWAX.get(), 0.5f),
                new Product(ItemsRegistration.HONEY_DROPLET.get(), 0.3f),
                tagProduct(entry.tag(), 0.25f));
    }
}
