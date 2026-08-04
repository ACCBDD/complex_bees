package com.accbdd.complex_bees.compat.jei;

import com.accbdd.complex_bees.ComplexBees;
import com.accbdd.complex_bees.datagen.bee.ComplexSpecies;
import com.accbdd.complicated_bees.bees.Species;
import com.accbdd.complicated_bees.block.BeeNestBlock;
import com.accbdd.complicated_bees.compat.jei.BeeProduceRecipeCategory;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import com.accbdd.complicated_bees.registry.SpeciesRegistration;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

@JeiPlugin
public class ComplexBeesJEI implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(ComplexBees.MODID, "jei_plugin");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        for (ComplexSpecies.SpeciesEntry entry : ComplexSpecies.SPECIES) {
            if (BuiltInRegistries.ITEM.getTag(entry.tag()).isEmpty()) {
                Species species = SpeciesRegistration.getFromResourceLocation(entry.key().location());
                if (species == null)
                    continue;
                jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, species.toMembers());
                jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, List.of(BeeNestBlock.stackNest(ItemsRegistration.BEE_NEST.get().getDefaultInstance(), species)));

                jeiRuntime.getRecipeManager().hideRecipes(BeeProduceRecipeCategory.TYPE, List.of(species));
            }
        }
    }
}
