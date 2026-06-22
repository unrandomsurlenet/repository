package com.dimproject.registries;

import com.dimproject.DimProjectMod;
import com.dimproject.world.biome.HeightBiomeSource;
import com.mojang.serialization.Codec;

import net.minecraft.world.level.biome.BiomeSource;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import com.mojang.serialization.MapCodec; // ou Codec selon version

import com.dimproject.DimProjectMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DimProjectBiomeSources {

    public static final DeferredRegister<Codec<? extends BiomeSource>> BIOME_SOURCES =
        DeferredRegister.create(Registries.BIOME_SOURCE, DimProjectMod.MODID);

    public static final RegistryObject<Codec<? extends BiomeSource>> HEIGHT_BIOME_SOURCE =
        BIOME_SOURCES.register("height_biome_source", () -> HeightBiomeSource.CODEC);

    public static void register(IEventBus eventBus) {
        BIOME_SOURCES.register(eventBus);
    }
}