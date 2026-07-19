package com.dimproject.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LibrairyLauncherBlock extends Block{
	

	public LibrairyLauncherBlock(Properties p_49795_) {
		super(p_49795_);
	}
	@Override
	public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity entity) {
		if(!entity.isShiftKeyDown() && entity instanceof LivingEntity) {
		entity.push(0.0D, 3.5D, 0.0D);
		((LivingEntity) entity).addEffect(new MobEffectInstance(
				MobEffects.DAMAGE_RESISTANCE,
				90,
				2
			));
		}

	}
	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
	    // Aucun dégât de chute
	}
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
	    level.addParticle(
	        ParticleTypes.BUBBLE_COLUMN_UP,
	        pos.getX() + 0.5,
	        pos.getY() + 1.0,
	        pos.getZ() + 0.5,
	        0.0,
	        0.02,
	        0.0
	    );
	}
	
	

}
