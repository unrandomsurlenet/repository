package com.dimproject.content.block;

import com.dimproject.registries.DimProjectBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ArcaneGrass extends BushBlock {
	
	public ArcaneGrass(Properties p_51021_) {
		super(p_51021_);
		
	}
	
	@Override
	 protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
	      return p_51042_.is(DimProjectBlocks.SAGE_SOIL.get()) || p_51042_.is((DimProjectBlocks.SAGE_GRASS.get()));
	   }
	

}
