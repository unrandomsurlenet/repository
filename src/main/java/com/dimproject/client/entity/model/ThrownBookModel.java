package com.dimproject.client.entity.model;

import com.dimproject.content.entity.projectile.ThrownBookEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ThrownBookModel extends GeoModel<ThrownBookEntity> {

    @Override
    public ResourceLocation getModelResource(ThrownBookEntity entity) {
        return new ResourceLocation("dimproject", "geo/thrown_book.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ThrownBookEntity entity) {
        return new ResourceLocation("dimproject", "textures/entity/thrown_book.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ThrownBookEntity entity) {
        return new ResourceLocation("dimproject", "animations/thrown_book.animation.json");
    }
}