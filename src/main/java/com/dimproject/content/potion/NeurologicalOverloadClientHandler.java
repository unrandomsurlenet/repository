package com.dimproject.content.potion;

import com.dimproject.registries.DimProjectMobEffect;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dimproject", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class NeurologicalOverloadClientHandler {

    private static float lockedYaw = 0f;
    private static float lockedPitch = 0f;
    private static boolean locked = false;

    @SubscribeEvent
    public static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (!mc.player.hasEffect(DimProjectMobEffect.NEUROLOGICAL_OVERLOAD.get())) {
            locked = false; // reset quand l'effet se termine
            return;
        }

        // Verrouille au premier appel
        if (!locked) {
            lockedYaw = mc.player.getYRot();
            lockedPitch = mc.player.getXRot();
            locked = true;
        }

        event.setYaw(lockedYaw);
        event.setPitch(lockedPitch);

        // Force aussi la rotation du joueur pour éviter les désync
        mc.player.setYRot(lockedYaw);
        mc.player.setXRot(lockedPitch);
    }
}