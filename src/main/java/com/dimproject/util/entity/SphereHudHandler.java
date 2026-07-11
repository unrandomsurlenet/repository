package com.dimproject.util.entity;

import com.dimproject.DimProjectMod;
import com.dimproject.client.entity.shader.SpeedEffectShader;
import com.dimproject.content.entity.tool.SphereEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;

import java.util.List;

@Mod.EventBusSubscriber(modid = DimProjectMod.MODID, value = Dist.CLIENT)
public class SphereHudHandler {

    @SubscribeEvent
    public static void onFovModifier(ComputeFovModifierEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        SphereEntity sphere = getNearbySphere(mc);
        if (sphere == null || !sphere.isEffectActive()) return;

        float intensity = sphere.getEffectIntensity(mc.getFrameTime());
        intensity = intensity * intensity * (3f - 2f * intensity);

        float fovBoost = intensity * 50f;
        event.setNewFovModifier(event.getNewFovModifier() + fovBoost);
    }

    @SubscribeEvent
    public static void onRenderHud(RenderGuiOverlayEvent.Post event) {
        if (event.getOverlay() != VanillaGuiOverlay.CROSSHAIR.type()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        SphereEntity sphere = getNearbySphere(mc);
        if (sphere == null || !sphere.isEffectActive()) return;

        float intensity = sphere.getEffectIntensity(mc.getFrameTime());
        intensity = intensity * intensity * (3f - 2f * intensity);

        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();

        GuiGraphics graphics = event.getGuiGraphics();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1f, 1f, 1f, intensity * 0.85f);

        graphics.blit(
            new ResourceLocation("dimproject", "textures/hud/speed_effect.png"),
            0, 0, 0, 0, w, h, w, h
        );

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableBlend();
    }

    private static SphereEntity getNearbySphere(Minecraft mc) {
        List<SphereEntity> spheres = mc.level.getEntitiesOfClass(
            SphereEntity.class,
            AABB.ofSize(mc.player.position(), 200, 200, 200)
        );
        return spheres.isEmpty() ? null : spheres.get(0);
    }
}