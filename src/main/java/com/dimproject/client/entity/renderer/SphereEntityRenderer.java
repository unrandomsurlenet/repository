package com.dimproject.client.entity.renderer;

import java.util.List;

import org.joml.Matrix4f;

import com.dimproject.client.entity.mesh.SphereMesh;
import com.dimproject.client.entity.type.SphereRenderType;
import com.dimproject.client.shader.SphereShader;
import com.dimproject.content.entity.tool.SphereEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SphereEntityRenderer extends EntityRenderer<SphereEntity> {

    public SphereEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(SphereEntity entity, float yaw, float partialTick,
                       PoseStack pose, MultiBufferSource buffers, int light) {

        ShaderInstance shader = SphereShader.INSTANCE;
        if (shader == null) return;

        float time = (entity.level().getGameTime() + partialTick) / 20f;

        RenderSystem.setShaderTexture(0, new ResourceLocation("dimproject", "textures/entity/sphere.png"));
        RenderSystem.setShaderTexture(1, new ResourceLocation("dimproject", "textures/entity/sphere_animated2.png"));
        RenderSystem.setShader(() -> SphereShader.INSTANCE);

        shader.safeGetUniform("SphereColor").set(1.0f, 1.0f, 1.0f, 0.8f);
        shader.safeGetUniform("Progress").set(entity.getProgress(partialTick));
        shader.safeGetUniform("Time").set(time);

        pose.pushPose();

        VertexConsumer consumer = buffers.getBuffer(SphereRenderType.SPHERE);
        Matrix4f matrix = pose.last().pose();

        for (SphereMesh.Vertex[] tri : SphereMesh.TRIANGLES) {
            for (SphereMesh.Vertex v : tri) {
                consumer.vertex(matrix, v.x(), v.y(), v.z())
                        .uv(v.u(), v.v())
                        .endVertex();
            }
        }

        if (buffers instanceof MultiBufferSource.BufferSource bufferSource) {
            bufferSource.endBatch(SphereRenderType.SPHERE);
        }

        pose.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(SphereEntity entity) {
        return new ResourceLocation("minecraft", "textures/block/stone.png");
    }
    @Override
    public boolean shouldRender(SphereEntity entity, Frustum frustum, double x, double y, double z) {
        return true; // toujours rendu, peu importe l'angle
    }	
}