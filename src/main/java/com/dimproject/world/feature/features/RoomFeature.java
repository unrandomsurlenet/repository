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

		
			int chunkX = pos.getX(); // ou pos.getX() >> 4
			int chunkZ = pos.getZ();
			int rnge = rand.nextInt(10);
		
			setAirBlock(reader, pos, 1, 0, 1, 14, 256, 14);
			setAirBlock(reader, pos, 0, 0, 0, 15, 55, 15);
			BlockPos.MutableBlockPos position2 = new BlockPos.MutableBlockPos().set(pos.getX(),0,pos.getZ());
			for (int y = 0; y <= 60; y++) {
				
				for (int x = 0; x <= 15; x++) {
					for (int z = 0; z <= 15; z++) {
						if (rnge == 3 && y ==32) {generateFlyingRock(reader, pos, Blocks.ANDESITE.defaultBlockState(), rand);}
						
						if (y == 54 && ((x == 0 && z <= 15) || (x == 15 && z <= 15) || (z == 0 && x <= 15) || (z == 15 && x <= 15))) {
							placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
							
						}
						if (y == 53 && ((x == 0 && (z <= 5 || z >=10)) || (x == 15 && (z <= 5 || z >=10)) || (z == 0 && (x <= 5 || x >=10)) || (z == 15 && (x <= 5 || x >=10)))) {
							placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
							
						}
						if (y == 52 && ((x == 0 && (z <= 2 || z >=13)) || (x == 15 && (z <= 2 || z >=13)) || (z == 0 && (x <= 2 || x >=13)) || (z == 15 && (x <= 2 || x >=13)))) {
							placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
							
						}
						if (y == 51 && ((x == 0 && z == 15) || (x == 15 && z == 15) || (z == 0 && x == 15) || (z == 15 && x == 15))) {
							placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
							
						}
						if (y == 50 && ((x == 0 && z == 15) || (x == 15 && z == 15) || (z == 0 && x == 15) || (z == 15 && x == 15))) {
							placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
							
						}
						
						
						
						
						
						if (((reader.getBlockState(position2.offset(x, y, z))) == Blocks.BOOKSHELF.defaultBlockState()) && (y<= 64 && y>= 55)){
						int rng = rand.nextInt((int) Math.pow(2, (y - 55) / 2));
						//java.lang.System.out.print(position2.offset(x, y, z).getY());
						if (rng == 0) {
							placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.SAGE_BOOKSHELF.get().defaultBlockState());
							
						}
						}
						if (((reader.getBlockState(position2.offset(x, y, z))) == DimProjectBlocks.SAGE_BOOKSHELF.get().defaultBlockState()) && (y<= 57 && y>= 55)){
							int rng2 = rand.nextInt((int) Math.pow(2, (y - 55) / 2));
							//java.lang.System.out.print(position2.offset(x, y, z).getY());
							if (rng2 == 0) {
								placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
								
							}
							}
						
					}
				}
				if ((chunkX % 3 == 0) && (y <= 49) && (y >= 30) && (chunkZ % 3 == 0)) {
					placeBlock(reader, position2.offset(0, y, 0), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
					placeBlock(reader, position2.offset(0, y, 15), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
					placeBlock(reader, position2.offset(15, y, 0), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());
					placeBlock(reader, position2.offset(15, y, 15), DimProjectBlocks.ARCANE_LIBRAIRY_BRICKS.get().defaultBlockState());}
				else if ((y <= 49) && (chunkZ % 3 == 0)) {
					placeBlock(reader, position2.offset(8, y, 8), DimProjectBlocks.ENCHANTED_BOOKSHELF.get().defaultBlockState());}
				else if ((y <= 49) && (chunkX % 3 == 0)) {
					placeBlock(reader, position2.offset(8, y, 8), DimProjectBlocks.MAHOU_BOOKSHELF.get().defaultBlockState());}
					
				
				
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
	
	private void generateFlyingRock(WorldGenLevel reader, BlockPos pos, final BlockState block, RandomSource rand) {
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos().set(pos);
		int rnga = rand.nextInt(2);
		switch (rnga) {
		case 0 : 
			for (int x = -1; x <= 1; x++) {
			    for (int y = -1; y <= 1; y++) {
			        for (int z = -1; z <= 1; z++) {
			            placeBlock(reader, mutablePos.offset(x, y, z), block);
			        }
			    }
			}
		case 1 : 
			for (int x = -4; x <= 4; x++) {
			    for (int y = -4; y <= 4; y++) {
			        for (int z = -4; z <= 4; z++) {
			            placeBlock(reader, mutablePos.offset(x, y, z), block);
			        }
			    }
			}
//		case 2 : 
//			float radius = 4.0f + (random.nextFloat() - 0.5f) * 1.5f;
//
//			for (int x = -5; x <= 5; x++) {
//			    for (int y = -5; y <= 5; y++) {
//			        for (int z = -5; z <= 5; z++) {
//
//			            double dist = Math.sqrt(x*x + y*y + z*z);
//
//			            if (dist <= radius + random.nextFloat() * 0.4f) {
//			                placeBlock(reader, mutablePos.offset(x, y, z), block);
//			            }
//			        }
//			    }
//			}
			
			
		
		}
	}


}
