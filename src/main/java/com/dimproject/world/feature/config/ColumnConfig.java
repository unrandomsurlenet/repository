package com.dimproject.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Random;

public class ColumnConfig implements FeatureConfiguration {
	public static final Codec<ColumnConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockStateProvider.CODEC.fieldOf("block").forGetter(config -> config.block),
			Codec.INT.fieldOf("min_size").forGetter(config -> config.minSize),
			Codec.INT.fieldOf("max_size").forGetter(config -> config.maxSize),
			Codec.BOOL.optionalFieldOf("uniform_height_distribution", true).forGetter(config -> config.uniformHeightDistribution)
	).apply(instance, ColumnConfig::new));

	public final BlockStateProvider block;
	public final int minSize;
	public final int maxSize;
	public final boolean uniformHeightDistribution;

	public ColumnConfig(BlockStateProvider block, int minSize, int maxSize, boolean uniformHeightDistribution) {
		super();
		this.block = block;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.uniformHeightDistribution = uniformHeightDistribution;
	}

	public int getHeight(Random rand) {
		if (minSize == maxSize)
			return maxSize;

		return rand.nextInt(1 + maxSize - minSize) + minSize;
	}
	public static class Builder {
        private final BlockStateProvider block;
        private int minSize = 1;
        private int maxSize = 6;
        private boolean uniformHeightDistribution = true;

        public Builder(BlockStateProvider block) {
            this.block = block;
        }

        public Builder minStemHeight(int minSize) {
            this.minSize = minSize;
            return this;
        }

        public Builder maxStemHeight(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public Builder weightTowardsSmallerColumns() {
            this.uniformHeightDistribution = false;
            return this;
        }


        public ColumnConfig build() {
            return new ColumnConfig( block,  minSize,  maxSize,  uniformHeightDistribution);
        }
    }
}
