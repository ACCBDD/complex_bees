package com.accbdd.complex_bees.registry;

import com.accbdd.complex_bees.bee.effect.RadioactiveEffect;
import com.accbdd.complicated_bees.bees.effect.IBeeEffect;
import com.accbdd.complicated_bees.registry.BeeEffectRegistration;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.accbdd.complex_bees.ComplexBees.MODID;

public class ComplexEffectRegistration {
    public static final DeferredRegister<IBeeEffect> EFFECTS = DeferredRegister.create(BeeEffectRegistration.BEE_REGISTRY_KEY, MODID);

    public static final Supplier<RadioactiveEffect> RADIOACTIVE = EFFECTS.register("radioactive", RadioactiveEffect::new);
}
