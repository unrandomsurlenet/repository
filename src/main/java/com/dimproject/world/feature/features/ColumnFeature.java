package com.dimproject.world.feature.features;

import com.dimproject.world.feature.config.ColumnConfig;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class ColumnFeature extends Feature<ColumnConfig> {
	public ColumnFeature(Codec<ColumnConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<ColumnConfig> context) {
		boolean placed = false;
		LevelAccessor reader = context.level();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
		ColumnConfig config = context.config();
		int stemHeight = rand.nextInt(1 + Math.max(0, config.maxSize - config.minSize));

		if (!config.uniformHeightDistribution)
			stemHeight = 1 + rand.nextInt(1 + stemHeight);

		stemHeight += config.minSize;

		for (int i = 0; i < stemHeight; i++) {
			BlockState block = config.block.getState(rand, placementPos);

			if (reader.isEmptyBlock(placementPos) && block.canSurvive(reader, placementPos)) {
				reader.setBlock(placementPos, block, 2);
				placementPos.move(Direction.UP);

				placed = true;
			}
			else {
				break;
			}
		}

		return placed;
	}
}