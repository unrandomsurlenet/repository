package com.dimproject.client.dimension;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;

import java.lang.reflect.Field;
import java.util.List;

public class PostChainUniformHelper {
    private static Field passesField;
    private static Field effectField;

    static {
        try {
            passesField = PostChain.class.getDeclaredField("passes");
            passesField.setAccessible(true);
            effectField = PostPass.class.getDeclaredField("effect");
            effectField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void setUniform(PostChain chain, String uniformName, float value) {
        try {
            List<PostPass> passes = (List<PostPass>) passesField.get(chain);
            for (PostPass pass : passes) {
                EffectInstance effect = (EffectInstance) effectField.get(pass);
                if (effect != null) {
                    var uniform = effect.getUniform(uniformName);
                    if (uniform != null) {
                        uniform.set(value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}