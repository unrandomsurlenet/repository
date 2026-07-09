package com.dimproject.client.entity.model;

import com.dimproject.content.entity.pet.BatlingEntity;
import com.dimproject.content.entity.projectile.ThrownBookEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BatlingModel extends GeoModel<BatlingEntity> {

    @Override
    public ResourceLocation getModelResource(BatlingEntity entity) {
        return new ResourceLocation("dimproject", "geo/batling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BatlingEntity entity) {
        return new ResourceLocation("dimproject", "textures/entity/batling.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BatlingEntity entity) {
        return new ResourceLocation("dimproject", "animations/batling.animation.json");
    }
}