package com.dimproject.util.entity;

import com.dimproject.client.shader.SpeedEffectShader;
import com.dimproject.content.entity.tool.SphereEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = "dimproject", value = Dist.CLIENT)
public class SphereHudHandler {

	@SubscribeEvent
	public static void onFovModifier(ComputeFovModifierEvent event) {
	    Minecraft mc = Minecraft.getInstance();
	    if (mc.level == null) return;

	    SphereEntity sphere = getNearbySphere(mc);
	    if (sphere == null || !sphere.isFovEffectActive()) return;

	    float intensity = sphere.getFovIntensity(mc.getFrameTime());
	    event.setNewFovModifier(event.getNewFovModifier() + intensity);
	}

	@SubscribeEvent
	public static void onRenderHud(RenderGuiOverlayEvent.Post event) {
	    if (event.getOverlay() != VanillaGuiOverlay.CROSSHAIR.type()) return;

	    Minecraft mc = Minecraft.getInstance();
	    if (mc.level == null || mc.player == null) return;

	    SphereEntity sphere = getNearbySphere(mc);
	    if (sphere == null || !sphere.isSpeedEffectActive()) return;

	    ShaderInstance shader = SpeedEffectShader.INSTANCE;
	    if (shader == null) return;

	    float intensity = sphere.getSpeedIntensity(mc.getFrameTime());
	    if (intensity <= 0.01f) return;


        float time = (mc.level.getGameTime() + mc.getFrameTime()) / 20f;
        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();

        shader.safeGetUniform("Time").set(time);
        shader.safeGetUniform("Intensity").set(intensity);
        shader.safeGetUniform("Resolution").set((float) w, (float) h);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(
            com.mojang.blaze3d.platform.GlStateManager.SourceFactor.SRC_ALPHA,
            com.mojang.blaze3d.platform.GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA // additif pour l'effet de lumière
        );
        RenderSystem.setShader(() -> shader);
        RenderSystem.disableDepthTest();

        // Dessine un quad plein écran
        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        buffer.vertex(0,  h,  0).uv(0, 1).endVertex();
        buffer.vertex(w,  h,  0).uv(1, 1).endVertex();
        buffer.vertex(w,  0,  0).uv(1, 0).endVertex();
        buffer.vertex(0,  0,  0).uv(0, 0).endVertex();
        Tesselator.getInstance().end();

        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
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