package com.accbdd.complex_bees;

import com.accbdd.complex_bees.datagen.DataGenerators;
import com.accbdd.complex_bees.registry.ComplexEffectRegistration;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(ComplexBees.MODID)
public class ComplexBees {
    public static final String MODID = "complex_bees";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ComplexBees(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGenerators::generate);
        ComplexEffectRegistration.EFFECTS.register(modEventBus);
        //NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }
}
