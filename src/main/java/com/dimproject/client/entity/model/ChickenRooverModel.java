package com.dimproject.client.entity.model;


import com.dimproject.content.entity.librairy.ChickenRooverEntity;


import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChickenRooverModel extends GeoModel<ChickenRooverEntity> {

    @Override
    public ResourceLocation getModelResource(ChickenRooverEntity entity) {
        return new ResourceLocation("dimproject", "geo/chicken_roover.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChickenRooverEntity entity) {
        return new ResourceLocation("dimproject", "textures/entity/chicken_roover.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChickenRooverEntity entity) {
        return new ResourceLocation("dimproject", "animations/chicken_roover.animation.json");
    }
}