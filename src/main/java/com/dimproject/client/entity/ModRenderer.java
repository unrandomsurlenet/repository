package com.dimproject.client.entity;

import com.dimproject.DimProjectMod;
import com.dimproject.client.entity.renderer.BatlingRenderer;
import com.dimproject.client.entity.renderer.ChickenDroneEntityRenderer;
import com.dimproject.client.entity.renderer.ChickenEngineerEntityRenderer;
import com.dimproject.client.entity.renderer.ChickenRooverEntityRenderer;
import com.dimproject.client.entity.renderer.ChickenWorkerEntityRenderer;
import com.dimproject.client.entity.renderer.SphereEntityRenderer;
import com.dimproject.client.entity.renderer.ThrownBookRenderer;
import com.dimproject.content.particles.ArcaneParticle;
import com.dimproject.registries.DimProjectEntities;
import com.dimproject.registries.DimProjectParticles;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DimProjectMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderer {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DimProjectEntities.THROWN_BOOK.get(), ThrownBookRenderer::new);
        event.registerEntityRenderer(DimProjectEntities.BATLING.get(), BatlingRenderer::new);
        event.registerEntityRenderer(DimProjectEntities.SPHERE_ENTITY.get(), SphereEntityRenderer::new);
        event.registerEntityRenderer(DimProjectEntities.CHICKEN_WORKER.get(), ChickenWorkerEntityRenderer::new);
        event.registerEntityRenderer(DimProjectEntities.CHICKEN_DRONE.get(), ChickenDroneEntityRenderer::new);
        event.registerEntityRenderer(DimProjectEntities.CHICKEN_ENGINEER.get(), ChickenEngineerEntityRenderer::new);
        event.registerEntityRenderer(DimProjectEntities.CHICKEN_ROOVER.get(), ChickenRooverEntityRenderer::new);




    }
    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(
            DimProjectParticles.ARCANE.get(),
            ArcaneParticle.Provider::new
        );
    }
}