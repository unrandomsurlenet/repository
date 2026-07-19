package com.dimproject.client.entity.renderer;

import com.dimproject.client.entity.model.ChickenEngineerModel;
import com.dimproject.content.entity.librairy.ChickenEngineerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChickenEngineerEntityRenderer extends GeoEntityRenderer<ChickenEngineerEntity> {

    public ChickenEngineerEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenEngineerModel());
    }
}