package com.dimproject.world.feature.features;


import java.util.Random;

import com.dimproject.registries.DimProjectBlocks;
import com.dimproject.world.feature.placement.BlockStatePlacementConfig;
import com.mojang.serialization.Codec;


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
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
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
							 int rng = rand.nextInt(32);
							
							 if (rng == 8 && (reader.getBlockState(position2.offset(x, y, z).above())) == Blocks.AIR.defaultBlockState()) {
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

							
						}
						

					}
				
			       }
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

				// === Mur NORD (Z=4) : de X=4 à X=11 ===
				placeBlock(reader, position2.offset(4,  y+1, 4), cornerNW);
				placeBlock(reader, position2.offset(5,  y+1, 4), fenceEW);
				placeBlock(reader, position2.offset(6,  y+1, 4), fenceEW);
				placeBlock(reader, position2.offset(7,  y+1, 4), fenceEW);
				placeBlock(reader, position2.offset(8,  y+1, 4), fenceEW);
				placeBlock(reader, position2.offset(9,  y+1, 4), fenceEW);
				placeBlock(reader, position2.offset(10, y+1, 4), fenceEW);
				placeBlock(reader, position2.offset(11, y+1, 4), cornerNE);

				// === Mur SUD (Z=11) : de X=4 à X=11 ===
				placeBlock(reader, position2.offset(4,  y+1, 11), cornerSW);
				placeBlock(reader, position2.offset(5,  y+1, 11), fenceEW);
				placeBlock(reader, position2.offset(6,  y+1, 11), fenceEW);
				placeBlock(reader, position2.offset(7,  y+1, 11), fenceEW);
				placeBlock(reader, position2.offset(8,  y+1, 11), fenceEW);
				placeBlock(reader, position2.offset(9,  y+1, 11), fenceEW);
				placeBlock(reader, position2.offset(10, y+1, 11), fenceEW);
				placeBlock(reader, position2.offset(11, y+1, 11), cornerSE);

				// === Mur OUEST (X=4) : de Z=5 à Z=10 (coins déjà placés) ===
				placeBlock(reader, position2.offset(4, y+1, 5),  fenceNS);
				placeBlock(reader, position2.offset(4, y+1, 6),  fenceNS);
				placeBlock(reader, position2.offset(4, y+1, 7),  fenceNS);
				placeBlock(reader, position2.offset(4, y+1, 8),  fenceNS);
				placeBlock(reader, position2.offset(4, y+1, 9),  fenceNS);
				placeBlock(reader, position2.offset(4, y+1, 10), fenceNS);

				// === Mur EST (X=11) : de Z=5 à Z=10 (coins déjà placés) ===
				placeBlock(reader, position2.offset(11, y+1, 5),  fenceNS);
				placeBlock(reader, position2.offset(11, y+1, 6),  fenceNS);
				placeBlock(reader, position2.offset(11, y+1, 7),  fenceNS);
				placeBlock(reader, position2.offset(11, y+1, 8),  fenceNS);
				placeBlock(reader, position2.offset(11, y+1, 9),  fenceNS);
				placeBlock(reader, position2.offset(11, y+1, 10), fenceNS);
				
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
	

}
