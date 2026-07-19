package com.dimproject.world.feature.features;



import com.dimproject.registries.DimProjectBlocks;
import com.dimproject.world.feature.placement.BlockStatePlacementConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;



public class RoomFeature extends Feature<BlockStatePlacementConfig>{
	public RoomFeature(Codec<BlockStatePlacementConfig> codec) {
		super(codec);
	}
	
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
			int rnge = rand.nextInt(7);
		
			setAirBlock(reader, pos, 1, 0, 1, 14, 256, 14);
			setAirBlock(reader, pos, 0, 0, 0, 15, 55, 15);
			BlockPos.MutableBlockPos position2 = new BlockPos.MutableBlockPos().set(pos.getX(),0,pos.getZ());
			for (int y = 0; y <= 64; y++) {
				
				for (int x = 0; x <= 15; x++) {
					for (int z = 0; z <= 15; z++) {
						if (y == 54 && ((x == 0 && z <= 15) || (x == 15 && z <= 15) || (z == 0 && x <= 15) || (z == 15 && x <= 15))) {
							placeBlock(reader, position2.offset(x, y, z), getBricksBlock(rand));
							
						}
						if (y == 53 && ((x == 0 && (z <= 5 || z >=10)) || (x == 15 && (z <= 5 || z >=10)) || (z == 0 && (x <= 5 || x >=10)) || (z == 15 && (x <= 5 || x >=10)))) {
							placeBlock(reader, position2.offset(x, y, z), getBricksBlock(rand));
							
						}
						if (y == 52 && ((x == 0 && (z <= 2 || z >=13)) || (x == 15 && (z <= 2 || z >=13)) || (z == 0 && (x <= 2 || x >=13)) || (z == 15 && (x <= 2 || x >=13)))) {
							placeBlock(reader, position2.offset(x, y, z), getBricksBlock(rand));
							
						}
						if (y == 51 && ((x == 0 && z == 15) || (x == 15 && z == 15) || (z == 0 && x == 15) || (z == 15 && x == 15))) {
							placeBlock(reader, position2.offset(x, y, z), getBricksBlock(rand));
							
						}
						if (y == 50 && ((x == 0 && z == 15) || (x == 15 && z == 15) || (z == 0 && x == 15) || (z == 15 && x == 15))) {
							placeBlock(reader, position2.offset(x, y, z), getBricksBlock(rand));
							
						}
						
						
						
						
						
//						if (((reader.getBlockState(position2.offset(x, y, z))) == Blocks.BOOKSHELF.defaultBlockState()) && (y<= 64 && y>= 55)){
//						int rng = rand.nextInt((int) Math.pow(2, (y - 55) / 2));
//						if (rng == 0) {
//							placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.SAGE_BOOKSHELF.get().defaultBlockState());
//							
//						}
//						}
//						if (((reader.getBlockState(position2.offset(x, y, z))) == DimProjectBlocks.SAGE_BOOKSHELF.get().defaultBlockState()) && (y<= 57 && y>= 55)){
//							int rng2 = rand.nextInt((int) Math.pow(2, (y - 55) / 2));
//							if (rng2 == 0) {
//								placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.LIBRAIRY_BRICKS.get().defaultBlockState());
//								
//							}
//						}
						if ((chunkX % 9 == 0) && (chunkZ % 9 == 0)) {
							if (y ==22) {placeBlock(
					                reader,
					                position2.offset(x, y, z),
					                getBricksBlock(rand)
					            );}
							double centerX = 7.5;
							double centerZ = 7.5;
							int radius = 7;

							double distance = Math.sqrt(
							    Math.pow(x - centerX, 2) +
							    Math.pow(z - centerZ, 2)
							);
						    boolean hole = false;


						    if (y >= 22 && y <= 26) {

						        // côté nord et sud (ouverture de 5 blocs de large ici)
						        if ((Math.abs(z - (centerZ - radius)) <= 0.5 || Math.abs(z - (centerZ + radius)) <= 0.5)
						                && x >= centerX - 2 && x <= centerX + 2) {
						            hole = true;
						        }

						        // côté est et ouest (ouverture de 5 blocs de large ici)
						        if ((Math.abs(x - (centerX - radius)) <= 0.5 || Math.abs(x - (centerX + radius)) <= 0.5)
						                && z >= centerZ - 2 && z <= centerZ + 2) {
						            hole = true;
						        }
						    }


						    // seulement l'anneau extérieur du cylindre
						    if (distance >= radius - 0.5 && distance <= radius + 0.5
						            && y <= 63 && y > 22
						            && !hole) {
						    	if (y == 48 || y == 40 | y == 32 || y == 24) {
						    		placeBlock(
								            reader,
								            position2.offset(x, y, z),
								            DimProjectBlocks.GILDED_LIBRAIRY_BRICKS.get().defaultBlockState()
								        );
						    		
						    	}
						    	else {
						        placeBlock(
						            reader,
						            position2.offset(x, y, z),
						            getBricksBlock(rand)
						        );}
						    }
						    placeBlock(reader,position2.offset(8, 23, 8),DimProjectBlocks.LIBRAIRY_LAUNCHER.get().defaultBlockState());
						    placeBlock(reader,position2.offset(8, 23, 7),DimProjectBlocks.LIBRAIRY_LAUNCHER.get().defaultBlockState());
						    placeBlock(reader,position2.offset(7, 23, 8),DimProjectBlocks.LIBRAIRY_LAUNCHER.get().defaultBlockState());
						    placeBlock(reader,position2.offset(7, 23, 7),DimProjectBlocks.LIBRAIRY_LAUNCHER.get().defaultBlockState());

						}
						else if (chunkZ % 9 == 0) {
							placeBlock(reader,position2.offset(1, 23, 1),DimProjectBlocks.ARCANE_LIGHT.get().defaultBlockState());
						    placeBlock(reader,position2.offset(1, 23, 14),DimProjectBlocks.ARCANE_LIGHT.get().defaultBlockState());
						    placeBlock(reader,position2.offset(14, 23, 14),DimProjectBlocks.ARCANE_LIGHT.get().defaultBlockState());
						    placeBlock(reader,position2.offset(14, 23, 14),DimProjectBlocks.ARCANE_LIGHT.get().defaultBlockState());
							 if (y <= 40 && y >22 && x >=3 && x <= 12) {

							        if (z == 3 || z == 7 || z == 8 || z == 12) {
							        	if(x ==3 || x == 12|| y == 40|| y == 23) {
							            placeBlock(
							                reader,
							                position2.offset(x, y, z),
							                getBricksBlock(rand)
							            );}
							        	else {placeBlock(
								                reader,
								                position2.offset(x, y, z),
								                DimProjectBlocks.SAGE_BOOKSHELF.get().defaultBlockState()
								            );}
							        }
							    }
							 if (y <= 53 && y >22 && (x == 8 || x== 7)&& (z == 0 || z== 15)) {placeBlock(
						                reader,
						                position2.offset(x, y, z),
						                Blocks.CHAIN.defaultBlockState()
						            );}
							 if (y==22) {
								 if (z== 1 || z == 5 || z== 10 || z == 14 || (z != 0 && z !=15 && (x == 0 || x == 1)) || (z != 0 && z !=15 && (x == 14 || x ==15))) {
										placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.SAGE_PLANKS.get().defaultBlockState());

									}
								 else {
							placeBlock(reader, position2.offset(x, y, z), getBricksBlock(rand));}}}
						
						else if (chunkX % 9 == 0) {
							placeBlock(reader,position2.offset(1, 23, 1),DimProjectBlocks.ARCANE_LIGHT.get().defaultBlockState());
						    placeBlock(reader,position2.offset(1, 23, 14),DimProjectBlocks.ARCANE_LIGHT.get().defaultBlockState());
						    placeBlock(reader,position2.offset(14, 23, 1),DimProjectBlocks.ARCANE_LIGHT.get().defaultBlockState());
						    placeBlock(reader,position2.offset(14, 23, 14),DimProjectBlocks.ARCANE_LIGHT.get().defaultBlockState());
							if (y <= 40 && y >22 && z >=3 && z <= 12) {

						        if (x == 3 || x == 7 || x == 8 || x == 12) {
						        	if(z ==3 || z == 12|| y == 40|| y == 23) {
						            placeBlock(
						                reader,
						                position2.offset(x, y, z),
						                getBricksBlock(rand)
						            );}
						        	else {placeBlock(
							                reader,
							                position2.offset(x, y, z),
							                DimProjectBlocks.SAGE_BOOKSHELF.get().defaultBlockState()
							            );}
						        }
						    }
							if (y <= 53 && y >22 && (z == 8 || z== 7) && (x == 0 || x== 15)) {placeBlock(
					                reader,
					                position2.offset(x, y, z),
					                Blocks.CHAIN.defaultBlockState()
					            );}
							if (y==22) {
								if (x== 1 || x == 5 || x== 10 || x == 14 || (x != 0 && x !=15 && (z == 0 || z == 1)) || (x != 0 && x !=15 && (z == 14 || z ==15))) {
									placeBlock(reader, position2.offset(x, y, z), DimProjectBlocks.SAGE_PLANKS.get().defaultBlockState());

								}
						else {
							placeBlock(reader, position2.offset(x, y, z), getBricksBlock(rand));}}}
							
						
						
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
	private BlockState getBricksBlock(RandomSource rand) {
		int rng = rand.nextInt(10);
		if (rng == 2){
			return DimProjectBlocks.GILDED_LIBRAIRY_BRICKS.get().defaultBlockState();
			
		}
		else {
			return DimProjectBlocks.LIBRAIRY_BRICKS.get().defaultBlockState();
		}
		
		
		
	}
	
	


}
