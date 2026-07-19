package com.dimproject.content.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;

public class NeurologicalOverloadEffect extends MobEffect{

	public  NeurologicalOverloadEffect() {
	      super(MobEffectCategory.HARMFUL, -16724890);
	}
	@Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Mobs : désactive l'IA
        if (entity instanceof Mob mob && !(entity instanceof Player)) {
            mob.setNoAi(true);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap map, int amplifier) {
        super.removeAttributeModifiers(entity, map, amplifier);
        // Réactive l'IA quand l'effet se termine
        if (entity instanceof Mob mob && !(entity instanceof Player)) {
            mob.setNoAi(false);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // applique applyEffectTick chaque tick
    }
}


