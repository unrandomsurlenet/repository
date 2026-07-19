package com.dimproject.client.entity.model;

import com.dimproject.content.entity.librairy.ChickenDroneEntity;
import com.dimproject.content.entity.librairy.ChickenWorkerEntity;
import com.dimproject.content.entity.pet.BatlingEntity;
import com.dimproject.content.entity.projectile.ThrownBookEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChickenDroneModel extends GeoModel<ChickenDroneEntity> {

    @Override
    public ResourceLocation getModelResource(ChickenDroneEntity entity) {
        return new ResourceLocation("dimproject", "geo/chicken_drone.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChickenDroneEntity entity) {
        return new ResourceLocation("dimproject", "textures/entity/chicken_worker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChickenDroneEntity entity) {
        return new ResourceLocation("dimproject", "animations/chicken_drone.animation.json");
    }
}