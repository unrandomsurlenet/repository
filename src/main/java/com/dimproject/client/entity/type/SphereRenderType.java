package com.dimproject.client.entity.type;

import com.dimproject.client.shader.SphereShader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class SphereRenderType extends RenderStateShard {

    public SphereRenderType(String name, Runnable setup, Runnable clear) {
        super(name, setup, clear);
    }

    private static ShaderStateShard createShaderState() {
        try {
            java.lang.reflect.Constructor<ShaderStateShard> ctor =
                ObfuscationReflectionHelper.findConstructor(
                    ShaderStateShard.class,
                    java.util.function.Supplier.class
                );
            return ctor.newInstance((java.util.function.Supplier<ShaderInstance>) () -> SphereShader.INSTANCE);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create ShaderStateShard", e);
        }
    }
    public static final RenderType SPHERE = RenderType.create(
            "sphere_shell",
            DefaultVertexFormat.POSITION_TEX,
            VertexFormat.Mode.TRIANGLES,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(createShaderState())  // <-- utilise la réflexion
                    .setTextureState(new TextureStateShard(
                        new ResourceLocation("dimproject", "textures/entity/sphere.png"),
                        false,
                        false
                    ))
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setDepthTestState(LEQUAL_DEPTH_TEST)
                    .setCullState(NO_CULL)
                    .createCompositeState(false)
    );
}