package com.accbdd.complex_bees.compat.emi;

import com.accbdd.complex_bees.datagen.bee.ComplexSpecies;
import com.accbdd.complicated_bees.ComplicatedBees;
import com.accbdd.complicated_bees.bees.Species;
import com.accbdd.complicated_bees.block.BeeNestBlock;
import com.accbdd.complicated_bees.compat.emi.recipe.BeeProduceEmiRecipe;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
import com.accbdd.complicated_bees.registry.SpeciesRegistration;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

@EmiEntrypoint
public class ComplexBeesEMI implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        for (ComplexSpecies.SpeciesEntry entry : ComplexSpecies.SPECIES) {
            if (entry.tag() != null && BuiltInRegistries.ITEM.getTag(entry.tag()).isEmpty()) {
                Species species = entry.species();
                species.toMembers().forEach(stack -> registry.removeEmiStacks(EmiStack.of(stack)));
                registry.removeEmiStacks(EmiStack.of(BeeNestBlock.stackNest(ItemsRegistration.BEE_NEST.get().getDefaultInstance(), SpeciesRegistration.getFromResourceLocation(entry.key().location()))));
                //fix this, maybe very fragile?
                registry.removeRecipes(recipe -> {
                    if (recipe instanceof BeeProduceEmiRecipe produce) {
                        return produce.getId().equals(ResourceLocation.fromNamespaceAndPath(ComplicatedBees.MODID, "/bee_produce/" + entry.key().location().toString().replace(":", "/")));
                    }
                    return false;
                });
            }
        }
    }
}
