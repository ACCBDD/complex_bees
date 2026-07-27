package com.accbdd.complex_bees.compat.emi;

import com.accbdd.complex_bees.datagen.bee.ComplexMutations;
import com.accbdd.complicated_bees.ComplicatedBees;
import com.accbdd.complicated_bees.bees.mutation.Mutation;
import com.accbdd.complicated_bees.block.BeeNestBlock;
import com.accbdd.complicated_bees.registry.ItemsRegistration;
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
        for (ComplexMutations.MutationEntry entry : ComplexMutations.MUTATIONS) {
            if (BuiltInRegistries.ITEM.getTag(entry.tag()).isEmpty()) {
                Mutation mutation = entry.mutation();
                mutation.getResultSpecies().toMembers().forEach(stack -> registry.removeEmiStacks(EmiStack.of(stack)));
                registry.removeEmiStacks(EmiStack.of(BeeNestBlock.stackNest(ItemsRegistration.BEE_NEST.get().getDefaultInstance(), mutation.getResultSpecies())));
                ResourceLocation location = ResourceLocation.tryBuild(ComplicatedBees.MODID,
                        "/mutation/first/" +
                                mutation.getFirst().toString().replace(":", "/") +
                                "/second/" +
                                mutation.getSecond().toString().replace(":", "/") +
                                "/result/" +
                                mutation.getResult().toString().replace(":", "/")
                );
                registry.removeRecipes(location);
            }
        }
    }
}
