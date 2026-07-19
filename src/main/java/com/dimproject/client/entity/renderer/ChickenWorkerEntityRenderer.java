package com.dimproject.client.entity.renderer;

import com.dimproject.client.entity.model.ChickenWorkerModel;
import com.dimproject.content.entity.librairy.ChickenWorkerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChickenWorkerEntityRenderer extends GeoEntityRenderer<ChickenWorkerEntity> {

    public ChickenWorkerEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenWorkerModel());
    }
}