package com.dimproject.world.feature.features;


import java.util.Random;

import com.dimproject.registries.DimProjectBlocks;
import com.dimproject.world.feature.placement.BlockStatePlacementConfig;
import com.mojang.serialization.Codec;

import net.minecraft.client.multiplayer.chat.LoggedChatMessage.System;
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
		BlockPos.MutableBlockPos position = new BlockPos.MutableBlockPos().set(pos.below());

		
		
		
		
			setAirBlock(reader, pos, 1, 0, 1, 14, 256, 14);
			setAirBlock(reader, pos, 0, 0, 0, 15, 55, 15);
			for (int y = 55; y <= 60; y++) {
				for (int x = 1; x <= 14; x++) {
					for (int z = 1; z <= 14; z++) {
						if ((reader.getBlockState(position.offset(x, y, z))) == Blocks.BOOKSHELF.defaultBlockState()){
						int rng = rand.nextInt((int) Math.pow(2, (y - 55) / 2));
						java.lang.System.out.print(rng);
						if (rng == 0) {
							placeBlock(reader, position.offset(x, y, z), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
							
						}
						}
						
					}
				}
			}
			
			


		
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
