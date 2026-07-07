package com.accbdd.complex_bees;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import static com.accbdd.complex_bees.ComplexBees.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ComplexBeesClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ComplexBees.LOGGER.info("HELLO FROM CLIENT SETUP");
        ComplexBees.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
