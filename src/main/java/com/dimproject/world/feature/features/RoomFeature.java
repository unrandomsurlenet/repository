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



public class RoomFeature extends Feature<BlockStatePlacementConfig>{
	public RoomFeature(Codec<BlockStatePlacementConfig> codec) {
		super(codec);
	}
	
//	public boolean generate(ServerLevel world, RandomSource rand, BlockPos pos) {
//		return generateHole(world, rand, pos, false);
//	}
	
	@Override
	public boolean place(FeaturePlaceContext<BlockStatePlacementConfig> pContext) {
		return false;
	}
	
	@Override
	public boolean place(BlockStatePlacementConfig config, WorldGenLevel level, ChunkGenerator chunkGen, RandomSource rand, BlockPos pos) {
		return generateHole(level,chunkGen, rand, pos);
	}
	
	
	protected void placeBlock(WorldGenLevel reader, BlockPos pos, BlockState state, boolean overwriteBlocks) {
		
		reader.setBlock(pos, state, 19);
	}
	
	protected void placeBlock(WorldGenLevel reader, BlockPos pos, BlockState state) {
		placeBlock(reader, pos, state, false);
	}
	
	
	public boolean generateHole(WorldGenLevel reader, ChunkGenerator chunkGen, RandomSource rand, BlockPos pos) {
		Random rng = new Random();
		BlockPos.MutableBlockPos position = new BlockPos.MutableBlockPos().set(pos.below());

		
		
		
		
			setAirBlock(reader, pos, 1, 0, 1, 14, 256, 14);
			
					
//					placeBlock(reader, position.offset(1, 59-59, 1), Blocks.BLACK_WOOL.defaultBlockState());
//					placeBlock(reader, position.offset(14, 59-59, 14), Blocks.BLACK_WOOL.defaultBlockState());
//					placeBlock(reader, position.offset(1, 59-59, 14), Blocks.BLACK_WOOL.defaultBlockState());
//					placeBlock(reader, position.offset(14, 59-59, 1), Blocks.BLACK_WOOL.defaultBlockState());
//
//					placeBlock(reader, position.offset(2, 58-59, 2), Blocks.BLACK_WOOL.defaultBlockState());
//					placeBlock(reader, position.offset(13, 58-59, 13), Blocks.BLACK_WOOL.defaultBlockState());
//					placeBlock(reader, position.offset(2, 58-59, 13), Blocks.BLACK_WOOL.defaultBlockState());
//					placeBlock(reader, position.offset(13, 58-59, 2), Blocks.BLACK_WOOL.defaultBlockState());
//
//					placeBlock(reader, position.offset(3, 57-59, 3), Blocks.BLACK_WOOL.defaultBlockState());
//					placeBlock(reader, position.offset(12, 57-59, 12), Blocks.BLACK_WOOL.defaultBlockState());
//					placeBlock(reader, position.offset(3, 57-59, 12), Blocks.BLACK_WOOL.defaultBlockState());
//					placeBlock(reader, position.offset(12, 57-59, 3), Blocks.BLACK_WOOL.defaultBlockState());
//
//			for (int x = 0; x <= 15; x++) {
//				for (int z = 0; z <= 15; z++) {
//					BlockPos posWest = pos.west(); // équivalent à x-1, y, z
//					BlockState stateWest = reader.getBlockState(posWest);
//					if (stateWest ==  Blocks.BOOKSHELF.defaultBlockState()){
//						placeBlock(reader, position.offset(0, 65, 0), Blocks.OAK_PLANKS.defaultBlockState());
//						placeBlock(reader, position.offset(-1, 65, 0), Blocks.OAK_PLANKS.defaultBlockState());
//						placeBlock(reader, position.offset(-2, 65, 0), Blocks.OAK_PLANKS.defaultBlockState());
//						placeBlock(reader, position.offset(-2, 66, 0), Blocks.OAK_FENCE.defaultBlockState());
//						
//						
////					}
//				}
//			}
//		

		
		return false;
	}
	
	private void setAllBlocksInRegion(WorldGenLevel reader, BlockPos pos, final int lowerX, final int lowerY, final int lowerZ, final int upperX, final int upperY, final int upperZ, final BlockState block) {
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos().set(pos.below());
		int x;
		int y;
		int z;

		for (x = lowerX; x <= upperX; x++) {
			for (y = lowerY; y <= upperY; y++) {
				for (z = lowerZ; z <= upperZ; z++) {
					placeBlock(reader, mutablePos.offset(x, y, z), block);
				}
			}
		}
	}
	private void setAirBlock(WorldGenLevel reader, BlockPos pos, final int lowerX, final int lowerY, final int lowerZ, final int upperX, final int upperY, final int upperZ) {
		setAllBlocksInRegion(reader, pos, lowerX, lowerY, lowerZ, upperX, lowerY, upperZ, Blocks.AIR.defaultBlockState());
		setAllBlocksInRegion(reader,pos, lowerX, lowerY + 1, lowerZ, upperX, upperY - 1, upperZ, Blocks.AIR.defaultBlockState());
		setAllBlocksInRegion(reader,pos,  lowerX, upperY, lowerZ, upperX, upperY, upperZ, Blocks.AIR.defaultBlockState());
	}


}
