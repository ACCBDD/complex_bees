package com.accbdd.complex_bees.compat.emi;

import com.accbdd.complex_bees.datagen.bee.ComplexSpecies;
import com.accbdd.complicated_bees.bees.Species;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.core.registries.BuiltInRegistries;

@EmiEntrypoint
public class ComplexBeesEMI implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        for (ComplexSpecies.SpeciesEntry entry : ComplexSpecies.SPECIES) {
            if (BuiltInRegistries.ITEM.getTag(entry.tag()).isEmpty()) {
                Species species = entry.species();
                species.toMembers().forEach(stack -> registry.removeEmiStacks(EmiStack.of(stack)));
                //registry.removeEmiStacks(EmiStack.of(BeeNestBlock.stackNest(ItemsRegistration.BEE_NEST.get().getDefaultInstance(), species)));
            }
        }
    }
}
