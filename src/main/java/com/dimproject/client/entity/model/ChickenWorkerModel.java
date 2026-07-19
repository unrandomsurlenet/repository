package com.dimproject.client.entity.model;

import com.dimproject.content.entity.librairy.ChickenWorkerEntity;
import com.dimproject.content.entity.pet.BatlingEntity;
import com.dimproject.content.entity.projectile.ThrownBookEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChickenWorkerModel extends GeoModel<ChickenWorkerEntity> {

    @Override
    public ResourceLocation getModelResource(ChickenWorkerEntity entity) {
        return new ResourceLocation("dimproject", "geo/chicken_worker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChickenWorkerEntity entity) {
        return new ResourceLocation("dimproject", "textures/entity/chicken_worker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChickenWorkerEntity entity) {
        return new ResourceLocation("dimproject", "animations/chicken_worker.animation.json");
    }
}