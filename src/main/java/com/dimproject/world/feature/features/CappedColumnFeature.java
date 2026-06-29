package com.dimproject.world.feature.features;

import com.dimproject.world.feature.config.CappedColumnConfig;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class CappedColumnFeature extends Feature<CappedColumnConfig> {
	public CappedColumnFeature(Codec<CappedColumnConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<CappedColumnConfig> context) {
	    boolean placed = false;

	    LevelAccessor reader = context.level();
	    BlockPos pos = context.origin();
	    BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
	    RandomSource rand = context.random();
	    CappedColumnConfig config = context.config();

	    int stemHeight = rand.nextInt(1 + Math.max(0, config.maxStemSize - config.minStemSize));
	    if (!config.uniformHeightDistribution)
	        stemHeight = 1 + rand.nextInt(1 + stemHeight);
	    stemHeight += config.minStemSize;

	    for (int i = 0; i < stemHeight; i++) {
	        boolean capping = false;
	        BlockState state;

	        if (i == stemHeight - 1) {
	            state = config.capBlock.getState(rand, placementPos);
	            capping = true;
	        } else {
	            state = config.stemBlock.getState(rand, placementPos);
	        }
	        
	        if ((reader.isEmptyBlock(placementPos) && state.canSurvive(reader, placementPos)) || (config.placementCondition == true)) {
	            reader.setBlock(placementPos, state, 2);
	            placementPos.move(config.upsideDown ? Direction.DOWN : Direction.UP);
	            placed = true;
	        } else {
	            break;
	        }

	        if (capping)
	            break;
	    }

	    return placed;
	}
}
