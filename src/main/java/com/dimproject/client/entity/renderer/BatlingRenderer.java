package com.dimproject.client.entity.renderer;

import com.dimproject.content.entity.pet.BatlingEntity;
import com.dimproject.client.entity.model.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BatlingRenderer extends GeoEntityRenderer<BatlingEntity> {

    public BatlingRenderer(EntityRendererProvider.Context context) {
        super(context, new BatlingModel());
    }
}