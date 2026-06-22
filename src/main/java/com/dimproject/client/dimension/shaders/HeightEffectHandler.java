package com.dimproject.client.dimension.shaders;

import com.dimproject.DimProjectMod;
import com.dimproject.client.dimension.PostChainUniformHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DimProjectMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HeightEffectHandler {

    private static final ResourceLocation EFFECT_LOCATION =
        new ResourceLocation(DimProjectMod.MODID, "shaders/post/height_effect.json");

    private static final int THRESHOLD_Y = 100;       // seuil où l'effet commence
    private static final int MAX_Y = 150;              // hauteur où l'effet est à 100%

    private static boolean effectActive = false;
    private static float currentIntensity = 0.0f;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) {
            disableEffect(mc);
            return;
        }

        // Vérifie qu'on est bien dans ta dimension
        boolean inCustomDim = mc.level.dimension().location()
            .equals(new ResourceLocation(DimProjectMod.MODID, "test")); // adapte l'ID

        if (!inCustomDim) {
            disableEffect(mc);
            return;
        }

        double y = player.getY();
        float targetIntensity;

        if (y <= THRESHOLD_Y) {
            targetIntensity = 0.0f;
        } else {
            float t = (float) ((y - THRESHOLD_Y) / (MAX_Y - THRESHOLD_Y));
            targetIntensity = Math.min(1.0f, Math.max(0.0f, t));
        }

        // Lissage pour éviter les changements brusques
        currentIntensity += (targetIntensity - currentIntensity) * 0.05f;

        if (currentIntensity > 0.001f) {
            if (!effectActive) {
                mc.gameRenderer.loadEffect(EFFECT_LOCATION);
                effectActive = true;
            }
            updateIntensityUniform(mc, currentIntensity);
        } else {
            disableEffect(mc);
        }
    }

    private static void updateIntensityUniform(Minecraft mc, float intensity) {
        PostChain effect = mc.gameRenderer.currentEffect();
        if (effect != null) {
            PostChainUniformHelper.setUniform(effect, "Intensity", intensity);
        }
    }

    private static void disableEffect(Minecraft mc) {
        if (effectActive) {
            mc.gameRenderer.shutdownEffect();
            effectActive = false;
            currentIntensity = 0.0f;
        }
    }
}