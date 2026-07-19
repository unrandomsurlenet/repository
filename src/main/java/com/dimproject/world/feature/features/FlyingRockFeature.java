package com.dimproject.world.feature.features;



import com.dimproject.registries.DimProjectBlocks;
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



public class FlyingRockFeature extends Feature<BlockStatePlacementConfig>{
	public FlyingRockFeature(Codec<BlockStatePlacementConfig> codec) {
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
		// Taille max possible (baseRadius max = 7, scaleMax = 1.4, +2)
	    int maxR = (int)(7.0f * 1.4f) + 2; // = 11 blocs

	    // Vérifie que toute la zone est en air
	    for (int x = -maxR; x <= maxR; x++) {
	        for (int y = -maxR; y <= maxR; y++) {
	            for (int z = -maxR; z <= maxR; z++) {
	                if (!reader.getBlockState(pos.offset(x, y, z)).isAir()) {
	                    return false; // pas assez de place
	                }
	            }
	        }
	    }

	    generateFlyingRock(reader, pos, DimProjectBlocks.SAGE_SOIL.get().defaultBlockState(), DimProjectBlocks.SAGE_GRASS.get().defaultBlockState(), rand);
	    return true;
	}
	
	
	
	private void generateFlyingRock(WorldGenLevel reader, BlockPos pos, final BlockState block, final BlockState surfaceBlock, RandomSource rand) {
	    float baseRadius = 1.0f + rand.nextFloat() * 6.0f;

	    float scaleX = 0.6f + rand.nextFloat() * 0.8f;
	    float scaleY = 0.2f + rand.nextFloat() * 0.3f; // très aplati verticalement
	    float scaleZ = 0.6f + rand.nextFloat() * 0.8f;

	    float tiltX = (rand.nextFloat() - 0.5f) * 0.4f;
	    float tiltZ = (rand.nextFloat() - 0.5f) * 0.4f;

	    int bumpCount = 6 + rand.nextInt(6);
	    float[] bumpX = new float[bumpCount];
	    float[] bumpY = new float[bumpCount];
	    float[] bumpZ = new float[bumpCount];
	    float[] bumpR = new float[bumpCount];

	    for (int i = 0; i < bumpCount; i++) {
	        bumpX[i] = (rand.nextFloat() - 0.5f) * 2.0f;
	        bumpY[i] = -0.2f - rand.nextFloat() * 0.6f; // bosses uniquement sur la moitié basse
	        bumpZ[i] = (rand.nextFloat() - 0.5f) * 2.0f;
	        bumpR[i] = 0.3f + rand.nextFloat() * 0.5f;
	    }

	    int r = (int)(baseRadius * Math.max(scaleX, Math.max(scaleY, scaleZ))) + 2;

	    BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

	    // Offset vertical : le centre de l'ellipsoïde est décalé vers le bas
	    float verticalOffset = baseRadius * scaleY * 0.4f;

	    java.util.List<BlockPos> placedPositions = new java.util.ArrayList<>();

	    for (int x = -r; x <= r; x++) {
	        for (int y = -r; y <= r; y++) {
	            for (int z = -r; z <= r; z++) {

	                float rx = x + y * tiltX;
	                float ry = y + verticalOffset;
	                float rz = z + y * tiltZ;

	                float dx = rx / (baseRadius * scaleX);
	                float dy = ry / (baseRadius * scaleY);
	                float dz = rz / (baseRadius * scaleZ);
	                float dist = dx*dx + dy*dy + dz*dz;

	                float bumpInfluence = 0.0f;
	                for (int i = 0; i < bumpCount; i++) {
	                    float bdx = rx / baseRadius - bumpX[i];
	                    float bdy = ry / baseRadius - bumpY[i];
	                    float bdz = rz / baseRadius - bumpZ[i];
	                    float bDist = bdx*bdx + bdy*bdy + bdz*bdz;
	                    bumpInfluence += bumpR[i] * (float)Math.exp(-bDist * 4.0f);
	                }

	                float bumpFactor = y < 0 ? 0.35f : 0.05f;
	                float threshold = 1.0f + bumpInfluence * bumpFactor;

	                if (dist < threshold) {
	                    mutablePos.setWithOffset(pos, x, y, z);
	                    placeBlock(reader, mutablePos, block);
	                    placedPositions.add(mutablePos.immutable());
	                }
	            }
	        }
	    }

	    // Renforce la face inférieure
	    for (int x = -r; x <= r; x++) {
	        for (int z = -r; z <= r; z++) {
	            float dx = x / (baseRadius * scaleX);
	            float dz = z / (baseRadius * scaleZ);
	            if (dx*dx + dz*dz < 0.85f) {
	                mutablePos.setWithOffset(pos, x, -1, z);
	                if (reader.getBlockState(mutablePos).isAir()) {
	                    placeBlock(reader, mutablePos, block);
	                    placedPositions.add(mutablePos.immutable());
	                }
	            }
	        }
	    }

	    // Passe finale : remplace par surfaceBlock uniquement les blocs
	    // qui ont de l'air juste au-dessus (face du dessus visible)
	    for (BlockPos p : placedPositions) {
	        BlockPos above = p.above();
	        if (reader.getBlockState(above).isAir()) {
	            reader.setBlock(p, surfaceBlock, 3);
	        }
	    }
	}

}
