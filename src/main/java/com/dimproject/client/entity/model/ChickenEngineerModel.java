package com.dimproject.client.entity.model;

import com.dimproject.content.entity.librairy.ChickenDroneEntity;
import com.dimproject.content.entity.librairy.ChickenEngineerEntity;
import com.dimproject.content.entity.librairy.ChickenWorkerEntity;
import com.dimproject.content.entity.pet.BatlingEntity;
import com.dimproject.content.entity.projectile.ThrownBookEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChickenEngineerModel extends GeoModel<ChickenEngineerEntity> {

    @Override
    public ResourceLocation getModelResource(ChickenEngineerEntity entity) {
        return new ResourceLocation("dimproject", "geo/chicken_engineer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChickenEngineerEntity entity) {
        return new ResourceLocation("dimproject", "textures/entity/chicken_engineer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChickenEngineerEntity entity) {
        return new ResourceLocation("dimproject", "animations/chicken_engineer.animation.json");
    }
}