package com.accbdd.complex_bees.datagen;

import com.accbdd.complex_bees.datagen.bee.ComplexCombs;
import com.accbdd.complex_bees.datagen.bee.ComplexSpecies;
import com.accbdd.complicated_bees.ComplicatedBees;
import com.accbdd.complicated_bees.bees.Product;
import com.accbdd.complicated_bees.bees.Species;
import com.accbdd.complicated_bees.datagen.builtin.BuiltInSpecies;
import com.accbdd.complicated_bees.recipe.CentrifugeRecipe;
import com.accbdd.complicated_bees.recipe.mutation.MutationRecipe;
import com.accbdd.complicated_bees.recipe.mutation.condition.BlockTagUnderCondition;
import com.accbdd.complicated_bees.recipe.mutation.condition.IMutationCondition;
import com.accbdd.complicated_bees.registry.EsotericRegistration;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
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
        tagCombCentrifugeRecipe(output, "silver_comb", ComplexCombs.SILVER);
        tagCombCentrifugeRecipe(output, "lead_comb", ComplexCombs.LEAD);
        tagCombCentrifugeRecipe(output, "uranium_comb", ComplexCombs.URANIUM);
        tagCombCentrifugeRecipe(output, "osmium_comb", ComplexCombs.OSMIUM);
        tagCombCentrifugeRecipe(output, "zinc_comb", ComplexCombs.ZINC);

        mutationRecipe(output, "metallic/stannum",
                ItemTagGenerator.RAW_TIN,
                BuiltInSpecies.FERROUS.getKey(),
                BuiltInSpecies.CUPROUS.getKey(),
                ComplexSpecies.STANNUM.key(),
                0.10f,
                new BlockTagUnderCondition(BlockTagGenerator.TIN));
        mutationRecipe(output, "metallic/osmium",
                ItemTagGenerator.RAW_OSMIUM,
                BuiltInSpecies.FERROUS.getKey(),
                BuiltInSpecies.DILIGENT.getKey(),
                ComplexSpecies.OSMIUM.key(),
                0.10f,
                new BlockTagUnderCondition(BlockTagGenerator.OSMIUM));
        mutationRecipe(output, "metallic/zincum",
                ItemTagGenerator.RAW_ZINC,
                BuiltInSpecies.CUPROUS.getKey(),
                BuiltInSpecies.ROBUST.getKey(),
                ComplexSpecies.ZINCUM.key(),
                0.12f,
                new BlockTagUnderCondition(BlockTagGenerator.ZINC));
        mutationRecipe(output, "metallic/radioactive",
                ItemTagGenerator.RAW_URANIUM,
                BuiltInSpecies.CUPROUS.getKey(),
                BuiltInSpecies.CONDUCTIVE.getKey(),
                ComplexSpecies.RADIOACTIVE.key(),
                0.08f,
                new BlockTagUnderCondition(BlockTagGenerator.URANIUM));
        mutationRecipe(output, "metallic/argentum",
                ItemTagGenerator.RAW_SILVER,
                BuiltInSpecies.PRECIOUS.getKey(),
                BuiltInSpecies.RESILIENT.getKey(),
                ComplexSpecies.ARGENTUM.key(),
                0.08f,
                new BlockTagUnderCondition(BlockTagGenerator.SILVER));
        mutationRecipe(output, "metallic/plumbum",
                ItemTagGenerator.RAW_LEAD,
                BuiltInSpecies.FERROUS.getKey(),
                BuiltInSpecies.ROCKY.getKey(),
                ComplexSpecies.PLUMBUM.key(),
                0.10f,
                new BlockTagUnderCondition(BlockTagGenerator.LEAD));
    }

    protected static void mutationRecipe(RecipeOutput output, String path, TagKey<Item> tag, ResourceKey<Species> first, ResourceKey<Species> second, ResourceKey<Species> result, float chance, IMutationCondition... conditions) {
        output.accept(ResourceLocation.fromNamespaceAndPath(ComplicatedBees.MODID, "mutation/" + path),
                new MutationRecipe(first.location(), second.location(), result.location(), chance, Arrays.stream(conditions).toList()),
                null,
                new NotCondition(new TagEmptyCondition(tag))
        );
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
