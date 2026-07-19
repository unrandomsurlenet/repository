package com.dimproject.client.entity.renderer;

import com.dimproject.client.entity.model.ChickenDroneModel;
import com.dimproject.content.entity.librairy.ChickenDroneEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChickenDroneEntityRenderer extends GeoEntityRenderer<ChickenDroneEntity> {

    public ChickenDroneEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenDroneModel());
    }
}