package com.dimproject.content.block;



import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class ArcaneLight extends Block {

	public ArcaneLight(Block.Properties properties) {
		super(properties);
			}
	@Override
	public boolean propagatesSkylightDown(BlockState p_48740_, BlockGetter p_48741_, BlockPos p_48742_) {
	      return true;
	   }
	

	
}
