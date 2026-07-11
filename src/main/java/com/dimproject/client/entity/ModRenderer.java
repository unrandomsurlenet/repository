package com.dimproject.client.entity;

import com.dimproject.DimProjectMod;
import com.dimproject.client.entity.renderer.BatlingRenderer;
import com.dimproject.client.entity.renderer.SphereEntityRenderer;
import com.dimproject.client.entity.renderer.ThrownBookRenderer;
import com.dimproject.registries.DimProjectEntities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DimProjectMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderer {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DimProjectEntities.THROWN_BOOK.get(), ThrownBookRenderer::new);
        event.registerEntityRenderer(DimProjectEntities.BATLING.get(), BatlingRenderer::new);
        event.registerEntityRenderer(DimProjectEntities.SPHERE_ENTITY.get(), SphereEntityRenderer::new);

    }
}