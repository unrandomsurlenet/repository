package com.dimproject.world.feature.features;


import java.util.Random;

import com.dimproject.world.feature.placement.BlockStatePlacementConfig;
import com.mojang.serialization.Codec;


import net.minecraft.core.BlockPos;
//import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
//import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;



public class LedgeFeature extends Feature<BlockStatePlacementConfig>{
	public LedgeFeature(Codec<BlockStatePlacementConfig> codec) {
		super(codec);
	}
	
	@Override
	public boolean place(FeaturePlaceContext<BlockStatePlacementConfig> pContext) {
		System.out.print("lol");
		return false;
	}
	
	@Override
	public boolean place(BlockStatePlacementConfig config, WorldGenLevel level, ChunkGenerator chunkGen, RandomSource rand, BlockPos pos) {
		return generateLedge(level,chunkGen, rand, pos);
	}
	
	
	protected void placeBlock(WorldGenLevel reader, BlockPos pos, BlockState state, boolean overwriteBlocks) {
		
		reader.setBlock(pos, state, 19);
	}
	
	protected void placeBlock(WorldGenLevel reader, BlockPos pos, BlockState state) {
		placeBlock(reader, pos, state, false);
	}
	
	
	public boolean generateLedge(WorldGenLevel reader, ChunkGenerator chunkGen, RandomSource rand, BlockPos pos) {
		for (int x = 0; x <= 15; x++) {
				for (int z = 0; z <= 15; z++) {
					BlockPos.MutableBlockPos position = new BlockPos.MutableBlockPos().set(pos.offset(x, 0, z));
					BlockState stateWest = reader.getBlockState(position.west());
					BlockState stateEast = reader.getBlockState(position.east());
					BlockState stateNorth = reader.getBlockState(position.north());
					BlockState stateSouth = reader.getBlockState(position.south());
					
					BlockState state = reader.getBlockState(pos.offset(x, 0, z));
					if (state ==  Blocks.BOOKSHELF.defaultBlockState() && stateWest == Blocks.AIR.defaultBlockState()){
						//64
						placeBlock(reader, position.offset(0, 0, 0), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(-1, 0, 0), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(-2, 0, 0), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(-2, 1, 0), Blocks.OAK_FENCE.defaultBlockState());
						
						
					}
					if (state ==  Blocks.BOOKSHELF.defaultBlockState() && stateEast == Blocks.AIR.defaultBlockState()){
						//64
						placeBlock(reader, position.offset(0, 0, 0), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(1, 0, 0), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(2, 0, 0), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(2, 1, 0), Blocks.OAK_FENCE.defaultBlockState());
						
						
					}
					if (state ==  Blocks.BOOKSHELF.defaultBlockState() && stateSouth == Blocks.AIR.defaultBlockState()){
						//64
						placeBlock(reader, position.offset(0, 0, 0), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(0, 0, 1), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(0, 0, 2), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(0, 1, 2), Blocks.OAK_FENCE.defaultBlockState());
						
						
					}
					if (state ==  Blocks.BOOKSHELF.defaultBlockState() && stateNorth == Blocks.AIR.defaultBlockState()){
						//64
						placeBlock(reader, position.offset(0, 0, 0), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(0, 0, -1), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(0, 0, -2), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(0, 1, -2), Blocks.OAK_FENCE.defaultBlockState());
						//96
						placeBlock(reader, position.offset(0, 0+32, 0), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(0, 0+32, -1), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(0, 0+32, -2), Blocks.OAK_PLANKS.defaultBlockState());
						placeBlock(reader, position.offset(0, 1+32, -2), Blocks.OAK_FENCE.defaultBlockState());
					
					}
				}
			}
		    BlockPos.MutableBlockPos position2 = new BlockPos.MutableBlockPos().set(pos);
			for (int y = 0; y <= 256; y++) {
				
		
			//if (y== 16 || y == 32 || y ==48 || y == 80 ) {
					placeBlock(reader, position2.offset(1, y, 1), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
					placeBlock(reader, position2.offset(14, y, 1), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
					placeBlock(reader, position2.offset(14, y, 14), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
					placeBlock(reader, position2.offset(1, y, 14), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
		
	
			}

		
		return false;
	}
	

}
