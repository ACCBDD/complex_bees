package com.accbdd.complex_bees.bee.effect;

import com.accbdd.complicated_bees.bees.effect.BeeEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class LivingEntityEffect extends BeeEffect {
    @Override
    public void runEffect(BlockEntity apiary, ItemStack queen, int cycleProgress) {
        if (apiary.getLevel() == null) return;

        if (cycleProgress % 80 == 0) {
            for (Entity entity : getTerritoryEntities(apiary, queen)) {
                if (entity instanceof LivingEntity living) {
                    affectLiving(living);
                }
            }
        }
    }

    public abstract void affectLiving(LivingEntity living);
}
