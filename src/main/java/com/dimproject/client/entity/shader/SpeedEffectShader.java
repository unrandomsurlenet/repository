package com.dimproject.client.entity.shader;

import com.dimproject.DimProjectMod;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = DimProjectMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SpeedEffectShader {

    public static ShaderInstance INSTANCE = null;

    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(
                new ShaderInstance(
                        event.getResourceProvider(),
                        new ResourceLocation("dimproject", "speed_effect"),
                        DefaultVertexFormat.POSITION_TEX
                ),
                shader -> INSTANCE = shader
        );
    }
}