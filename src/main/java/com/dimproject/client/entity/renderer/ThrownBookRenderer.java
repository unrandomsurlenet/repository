package com.dimproject.client.entity.renderer;

import com.dimproject.content.entity.projectile.ThrownBookEntity;
import com.dimproject.client.entity.model.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ThrownBookRenderer extends GeoEntityRenderer<ThrownBookEntity> {

    public ThrownBookRenderer(EntityRendererProvider.Context context) {
        super(context, new ThrownBookModel());
    }
}