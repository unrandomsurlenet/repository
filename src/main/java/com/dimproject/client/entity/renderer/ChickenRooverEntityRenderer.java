package com.dimproject.client.entity.renderer;

import com.dimproject.client.entity.model.ChickenEngineerModel;
import com.dimproject.client.entity.model.ChickenRooverModel;
import com.dimproject.content.entity.librairy.ChickenEngineerEntity;
import com.dimproject.content.entity.librairy.ChickenRooverEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChickenRooverEntityRenderer extends GeoEntityRenderer<ChickenRooverEntity> {

    public ChickenRooverEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenRooverModel());
    }
}