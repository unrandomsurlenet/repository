package com.dimproject.world.feature.features;


import java.util.Random;

import com.dimproject.registries.DimProjectBlocks;
import com.dimproject.world.feature.placement.BlockStatePlacementConfig;
import com.mojang.serialization.Codec;

import net.mcreator.terramity.TerramityMod;
import net.mcreator.terramity.init.TerramityModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BarrelBlock;
//import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
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
		BlockPos.MutableBlockPos position2 = new BlockPos.MutableBlockPos().set(pos);
		for (int y = 0; y <= 256; y++) {
			placeBlock(reader, position2.offset(1, y, 1), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(1, y, 5), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(1, y, 9), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(5, y, 1), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(9, y, 1), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(14, y, 1), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(14, y, 5), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(14, y, 9), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(5, y, 14), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(9, y, 14), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(14, y, 14), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			placeBlock(reader, position2.offset(1, y, 14), Blocks.STRIPPED_OAK_LOG.defaultBlockState());
			
			if (y%16 == 0){
				
				for (int x = 1; x <= 14; x++) {
					for (int z = 1; z <= 14; z++) {
						if ((x < 5 || x >10) || (z < 5 || z > 10)) {
							placeBlock(reader, position2.offset(x, y, z), Blocks.OAK_PLANKS.defaultBlockState());
							 int rng = rand.nextInt(120);
							
							 if ((rng == 8 || rng == 10) && (reader.getBlockState(position2.offset(x, y, z).above())) == Blocks.AIR.defaultBlockState()) {
								 int rng2 = rand.nextInt(5);
								 BlockPos barrelPos = position2.offset(x, y+1, z);
								 switch (rng2) {
									 case 0 :
										 placeBlock(reader, barrelPos, Blocks.BARREL.defaultBlockState().setValue(BarrelBlock.FACING, Direction.UP));
										 break;
									 case 1 :
										 placeBlock(reader, barrelPos, Blocks.BARREL.defaultBlockState().setValue(BarrelBlock.FACING, Direction.EAST));
										 break;
									 case 2 :
										 placeBlock(reader, barrelPos, Blocks.BARREL.defaultBlockState().setValue(BarrelBlock.FACING, Direction.WEST));
										 break;
									 case 3 :
										 placeBlock(reader, barrelPos, Blocks.BARREL.defaultBlockState().setValue(BarrelBlock.FACING, Direction.SOUTH));
										 break;
									 case 4 :
										 placeBlock(reader, barrelPos, Blocks.BARREL.defaultBlockState().setValue(BarrelBlock.FACING, Direction.NORTH));
										 break;
										 
								 }	 
								 if (reader.getBlockEntity(barrelPos) instanceof RandomizableContainerBlockEntity barrel) {
								     barrel.setLootTable(
								         new ResourceLocation("dimproject", "chests/test"),
								         reader.getRandom().nextLong() // seed aléatoire
								     );
								 }
							 }
//							 if (rng == 6 && (reader.getBlockState(position2.offset(x, y, z).above())) == Blocks.AIR.defaultBlockState()) {
//								 int rng2 = rand.nextInt(4);
//								 BlockPos lecternpos = position2.offset(x, y+1, z);
//								 switch (rng2) {
//								 case 0 :
//									 placeBlock(reader, lecternpos, Blocks.LECTERN.defaultBlockState().setValue(LecternBlock.FACING, Direction.EAST));
//									 break;
//								 case 1 :
//									 placeBlock(reader, lecternpos, Blocks.LECTERN.defaultBlockState().setValue(LecternBlock.FACING, Direction.WEST));
//									 break;
//								 case 2 :
//									 placeBlock(reader, lecternpos, Blocks.LECTERN.defaultBlockState().setValue(LecternBlock.FACING, Direction.SOUTH));
//									 break;
//								 case 3 :
//									 placeBlock(reader, lecternpos, Blocks.LECTERN.defaultBlockState().setValue(LecternBlock.FACING, Direction.NORTH));
//									 break;
//								 }
//							 }

							
						}
						

					}
				
			       }
				 choseDecoration(reader, position2, y, rand);
				 placeBlock(reader, position2.offset(0, y+1, 6), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(0, y+1, 7), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(0, y+1, 8), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(0, y+2, 6), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(0, y+2, 7), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(0, y+2, 8), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(0, y+3, 6), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(0, y+3, 7), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(0, y+3, 8), Blocks.AIR.defaultBlockState());
				 
				 placeBlock(reader, position2.offset(6, y+1, 0), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(7, y+1, 0), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(8, y+1, 0), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(6, y+2, 0), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(7, y+2, 0), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(8, y+2, 0), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(6, y+3, 0), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(7, y+3, 0), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(8, y+3, 0), Blocks.AIR.defaultBlockState());
 
				 placeBlock(reader, position2.offset(6, y+1, 15), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(7, y+1, 15), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(8, y+1, 15), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(6, y+2, 15), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(7, y+2, 15), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(8, y+2, 15), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(6, y+3, 15), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(7, y+3, 15), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(8, y+3, 15), Blocks.AIR.defaultBlockState());
				 
				 placeBlock(reader, position2.offset(15, y+1, 6), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(15, y+1, 7), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(15, y+1, 8), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(15, y+2, 6), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(15, y+2, 7), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(15, y+2, 8), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(15, y+3, 6), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(15, y+3, 7), Blocks.AIR.defaultBlockState());
				 placeBlock(reader, position2.offset(15, y+3, 8), Blocks.AIR.defaultBlockState());


				 }
		}
			

		
		return false;
	}
	private void choseDecoration(WorldGenLevel reader, BlockPos pos, int y, RandomSource rand) {
		// === States réutilisables ===
		BlockState fenceEW = Blocks.OAK_FENCE.defaultBlockState() // horizontal (côtés nord/sud)
		    .setValue(FenceBlock.EAST, true).setValue(FenceBlock.WEST, true)
		    .setValue(FenceBlock.NORTH, false).setValue(FenceBlock.SOUTH, false);

		BlockState fenceNS = Blocks.OAK_FENCE.defaultBlockState() // vertical (côtés est/ouest)
		    .setValue(FenceBlock.NORTH, true).setValue(FenceBlock.SOUTH, true)
		    .setValue(FenceBlock.EAST, false).setValue(FenceBlock.WEST, false);

		// Coins
		BlockState cornerNW = Blocks.OAK_FENCE.defaultBlockState() // coin nord-ouest (4,4)
		    .setValue(FenceBlock.EAST, true).setValue(FenceBlock.SOUTH, true)
		    .setValue(FenceBlock.NORTH, false).setValue(FenceBlock.WEST, false);

		BlockState cornerNE = Blocks.OAK_FENCE.defaultBlockState() // coin nord-est (11,4)
		    .setValue(FenceBlock.WEST, true).setValue(FenceBlock.SOUTH, true)
		    .setValue(FenceBlock.NORTH, false).setValue(FenceBlock.EAST, false);

		BlockState cornerSW = Blocks.OAK_FENCE.defaultBlockState() // coin sud-ouest (4,11)
		    .setValue(FenceBlock.EAST, true).setValue(FenceBlock.NORTH, true)
		    .setValue(FenceBlock.SOUTH, false).setValue(FenceBlock.WEST, false);

		BlockState cornerSE = Blocks.OAK_FENCE.defaultBlockState() // coin sud-est (11,11)
		    .setValue(FenceBlock.WEST, true).setValue(FenceBlock.NORTH, true)
		    .setValue(FenceBlock.SOUTH, false).setValue(FenceBlock.EAST, false);
		int rand1 = rand.nextInt(9);
		int rand2 = rand.nextInt(11);
		if (rand1 == 1 || rand1 == 5) {
			for (int x = 5; x <= 10; x++) {
				for (int z = 5; z <= 10; z++) {
						placeBlock(reader, pos.offset(x, y, z), Blocks.OAK_PLANKS.defaultBlockState());
						if (rand2 == 1) {
							generateFairyStructure(reader, pos.offset(5,y+1,5));
							
						}
						if (rand2 == 2) {
							generateStatueStructure(reader, pos.offset(5,y+1,5));
							
						}
			
				}}
		}
		else if (rand1 == 2 || rand1 == 3 || rand1 == 4) {
			// === Mur NORD (Z=5) : de X=5 à X=10 ===
			placeBlock(reader, pos.offset(5, y, 5), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(5, y + 1, 5), cornerNW);

			placeBlock(reader, pos.offset(6, y, 5), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(6, y + 1, 5), fenceEW);

			placeBlock(reader, pos.offset(7, y, 5), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(7, y + 1, 5), fenceEW);

			placeBlock(reader, pos.offset(8, y, 5), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(8, y + 1, 5), fenceEW);

			placeBlock(reader, pos.offset(9, y, 5), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(9, y + 1, 5), fenceEW);

			placeBlock(reader, pos.offset(10, y, 5), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(10, y + 1, 5), cornerNE);

			// === Mur SUD (Z=10) : de X=5 à X=10 ===
			placeBlock(reader, pos.offset(5, y, 10), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(5, y + 1, 10), cornerSW);

			placeBlock(reader, pos.offset(6, y, 10), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(6, y + 1, 10), fenceEW);

			placeBlock(reader, pos.offset(7, y, 10), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(7, y + 1, 10), fenceEW);

			placeBlock(reader, pos.offset(8, y, 10), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(8, y + 1, 10), fenceEW);

			placeBlock(reader, pos.offset(9, y, 10), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(9, y + 1, 10), fenceEW);

			placeBlock(reader, pos.offset(10, y, 10), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(10, y + 1, 10), cornerSE);

			// === Mur OUEST (X=5) : de Z=6 à Z=9 ===
			placeBlock(reader, pos.offset(5, y, 6), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(5, y + 1, 6), fenceNS);

			placeBlock(reader, pos.offset(5, y, 7), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(5, y + 1, 7), fenceNS);

			placeBlock(reader, pos.offset(5, y, 8), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(5, y + 1, 8), fenceNS);

			placeBlock(reader, pos.offset(5, y, 9), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(5, y + 1, 9), fenceNS);

			// === Mur EST (X=10) : de Z=6 à Z=9 ===
			placeBlock(reader, pos.offset(10, y, 6), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(10, y + 1, 6), fenceNS);

			placeBlock(reader, pos.offset(10, y, 7), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(10, y + 1, 7), fenceNS);

			placeBlock(reader, pos.offset(10, y, 8), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(10, y + 1, 8), fenceNS);

			placeBlock(reader, pos.offset(10, y, 9), Blocks.OAK_PLANKS.defaultBlockState());
			placeBlock(reader, pos.offset(10, y + 1, 9), fenceNS);
		}
		else {
			placeBlock(reader, pos.offset(4,  y+1, 4), cornerNW);
			placeBlock(reader, pos.offset(5,  y+1, 4), fenceEW);
			placeBlock(reader, pos.offset(6,  y+1, 4), fenceEW);
			placeBlock(reader, pos.offset(7,  y+1, 4), fenceEW);
			placeBlock(reader, pos.offset(8,  y+1, 4), fenceEW);
			placeBlock(reader, pos.offset(9,  y+1, 4), fenceEW);
			placeBlock(reader, pos.offset(10, y+1, 4), fenceEW);
			placeBlock(reader, pos.offset(11, y+1, 4), cornerNE);

			// === Mur SUD (Z=11) : de X=4 à X=11 ===
			placeBlock(reader, pos.offset(4,  y+1, 11), cornerSW);
			placeBlock(reader, pos.offset(5,  y+1, 11), fenceEW);
			placeBlock(reader, pos.offset(6,  y+1, 11), fenceEW);
			placeBlock(reader, pos.offset(7,  y+1, 11), fenceEW);
			placeBlock(reader, pos.offset(8,  y+1, 11), fenceEW);
			placeBlock(reader, pos.offset(9,  y+1, 11), fenceEW);
			placeBlock(reader, pos.offset(10, y+1, 11), fenceEW);
			placeBlock(reader, pos.offset(11, y+1, 11), cornerSE);

			// === Mur OUEST (X=4) : de Z=5 à Z=10 (coins déjà placés) ===
			placeBlock(reader, pos.offset(4, y+1, 5),  fenceNS);
			placeBlock(reader, pos.offset(4, y+1, 6),  fenceNS);
			placeBlock(reader, pos.offset(4, y+1, 7),  fenceNS);
			placeBlock(reader, pos.offset(4, y+1, 8),  fenceNS);
			placeBlock(reader, pos.offset(4, y+1, 9),  fenceNS);
			placeBlock(reader, pos.offset(4, y+1, 10), fenceNS);

			// === Mur EST (X=11) : de Z=5 à Z=10 (coins déjà placés) ===
			placeBlock(reader, pos.offset(11, y+1, 5),  fenceNS);
			placeBlock(reader, pos.offset(11, y+1, 6),  fenceNS);
			placeBlock(reader, pos.offset(11, y+1, 7),  fenceNS);
			placeBlock(reader, pos.offset(11, y+1, 8),  fenceNS);
			placeBlock(reader, pos.offset(11, y+1, 9),  fenceNS);
			placeBlock(reader, pos.offset(11, y+1, 10), fenceNS);
			}
		
	}
	//=== FAIRY ===
			private void generateFairyStructure(WorldGenLevel reader, BlockPos pos) {
			    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
			    mutable.setWithOffset(pos, 0, 0, 1);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.OUTER_RIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST));
			    mutable.setWithOffset(pos, 0, 0, 2);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST));
			    mutable.setWithOffset(pos, 0, 0, 3);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST));
			    mutable.setWithOffset(pos, 0, 0, 4);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.OUTER_RIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
			    mutable.setWithOffset(pos, 1, 0, 1);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH));
			    mutable.setWithOffset(pos, 1, 0, 2);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 1, 0, 3);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 1, 0, 4);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
			    mutable.setWithOffset(pos, 2, 0, 1);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH));
			    mutable.setWithOffset(pos, 2, 0, 2);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 2, 0, 3);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 2, 0, 4);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
			    mutable.setWithOffset(pos, 3, 0, 1);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.OUTER_RIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH));
			    mutable.setWithOffset(pos, 3, 0, 2);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST));
			    mutable.setWithOffset(pos, 3, 0, 3);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST));
			    mutable.setWithOffset(pos, 3, 0, 4);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.OUTER_RIGHT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST));
			    mutable.setWithOffset(pos, 0, 1, 1);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 1, 4);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 1, 1);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 1, 4);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 2, 1);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 2, 4);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 2, 2);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 1, 2, 3);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 2, 2, 2);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 2, 2, 3);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 3, 2, 1);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 2, 4);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 3, 1);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 3, 2);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.TOP));
			    mutable.setWithOffset(pos, 0, 3, 3);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.TOP));
			    mutable.setWithOffset(pos, 0, 3, 4);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 3, 1);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.TOP));
			    mutable.setWithOffset(pos, 1, 3, 2);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.OUTER_LEFT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH));
			    mutable.setWithOffset(pos, 1, 3, 3);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.OUTER_LEFT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST));
			    mutable.setWithOffset(pos, 1, 3, 4);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.TOP));
			    mutable.setWithOffset(pos, 2, 3, 1);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.TOP));
			    mutable.setWithOffset(pos, 2, 3, 2);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.OUTER_LEFT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST));
			    mutable.setWithOffset(pos, 2, 3, 3);
			    placeBlock(reader, mutable, Blocks.OAK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HALF, Half.TOP).setValue(BlockStateProperties.STAIRS_SHAPE, StairsShape.OUTER_LEFT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
			    mutable.setWithOffset(pos, 2, 3, 4);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.TOP));
			    mutable.setWithOffset(pos, 3, 3, 1);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 3, 2);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.TOP));
			    mutable.setWithOffset(pos, 3, 3, 3);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.TOP));
			    mutable.setWithOffset(pos, 3, 3, 4);
			    placeBlock(reader, mutable, Blocks.OAK_FENCE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 4, 1);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 0, 4, 2);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 0, 4, 3);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 0, 4, 4);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 1, 4, 1);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 1, 4, 4);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 2, 4, 1);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 2, 4, 4);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 3, 4, 1);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 3, 4, 2);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 3, 4, 3);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 3, 4, 4);
			    placeBlock(reader, mutable, Blocks.OAK_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 1, 1, 2);
			    placeBlock(reader, mutable, TerramityModBlocks.PINK_FAIRY_JAR.get().defaultBlockState());
			    mutable.setWithOffset(pos, 1, 1, 3);
			    placeBlock(reader, mutable, TerramityModBlocks.PINK_FAIRY_JAR.get().defaultBlockState());
			    mutable.setWithOffset(pos, 2, 1, 2);
			    placeBlock(reader, mutable, TerramityModBlocks.PINK_FAIRY_JAR.get().defaultBlockState());
			    mutable.setWithOffset(pos, 2, 1, 3);
			    placeBlock(reader, mutable, TerramityModBlocks.PINK_FAIRY_JAR.get().defaultBlockState());
			}

			//=== STATUE ===
			private void generateStatueStructure(WorldGenLevel reader, BlockPos pos) {
			    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
			    mutable.setWithOffset(pos, 0, 3, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 3, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 3, 1);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 3, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 3, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 3, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 3, 1);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 3, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 3, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 3, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 3, 1);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 3, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 3, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 3, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 4, 3, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 4, 3, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 4, 3, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 4, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 4, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 4, 1);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 4, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 4, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 4, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 4, 5);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 4, 1);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 4, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 4, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 4, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 4, 5);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 4, 1);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 4, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 4, 3);
			    placeBlock(reader, mutable, Blocks.STONE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 4, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 4, 5);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 4, 4, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 4, 4, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 4, 4, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 5, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 0, 5, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 5, 1);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 5, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 5, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 5, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 5, 5);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 5, 6);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 5, 1);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 5, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 5, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 5, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 5, 5);
			    placeBlock(reader, mutable, Blocks.STONE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 5, 6);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 5, 1);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 5, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 5, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 5, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 5, 5);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 5, 6);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 4, 5, 2);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 4, 5, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 4, 5, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 6, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 6, 5);
			    placeBlock(reader, mutable, Blocks.BLACK_CONCRETE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 6, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 6, 5);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 6, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 6, 5);
			    placeBlock(reader, mutable, Blocks.BLACK_CONCRETE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 7, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 7, 5);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 7, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 2, 7, 5);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 7, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 7, 5);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 0, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE_WALL.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 0, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 3, 0, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE_WALL.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 0, 4);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE_SLAB.defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM));
			    mutable.setWithOffset(pos, 1, 1, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE_WALL.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 1, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE_WALL.defaultBlockState());
			    mutable.setWithOffset(pos, 1, 2, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE_WALL.defaultBlockState());
			    mutable.setWithOffset(pos, 3, 2, 3);
			    placeBlock(reader, mutable, Blocks.COBBLED_DEEPSLATE_WALL.defaultBlockState());
			}
	

}
