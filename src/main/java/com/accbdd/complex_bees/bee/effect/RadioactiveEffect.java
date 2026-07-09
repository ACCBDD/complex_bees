package com.accbdd.complex_bees.bee.effect;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class RadioactiveEffect extends LivingEntityEffect {
    @Override
    public void affectLiving(LivingEntity living) {
        living.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200));
        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1));
        living.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 2));
    }
}
