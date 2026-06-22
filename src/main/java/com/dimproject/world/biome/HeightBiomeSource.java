package com.dimproject.world.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;


import java.util.stream.Stream;

public class HeightBiomeSource extends BiomeSource {

    public static final Codec<HeightBiomeSource> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Biome.CODEC.fieldOf("biome_top").forGetter(s -> s.biomeTop),
            Biome.CODEC.fieldOf("biome_bottom").forGetter(s -> s.biomeBottom),
            Codec.INT.fieldOf("threshold_y").forGetter(s -> s.thresholdY)
    ).apply(instance, HeightBiomeSource::new));

    private final Holder<Biome> biomeTop;
    private final Holder<Biome> biomeBottom;
    private final int thresholdY;

    public HeightBiomeSource(Holder<Biome> biomeTop, Holder<Biome> biomeBottom, int thresholdY) {
        super();
        this.biomeTop = biomeTop;
        this.biomeBottom = biomeBottom;
        this.thresholdY = thresholdY;
    }

    @Override
    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return Stream.of(biomeTop, biomeBottom);
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        int blockY = QuartPos.toBlock(y); // y est en "quart" (biome coords), conversion en coordonnée bloc
        return blockY >= thresholdY ? biomeTop : biomeBottom;
    }
}