package com.dimproject.registries;

import com.dimproject.DimProjectMod;
import com.dimproject.world.feature.features.LedgeFeature;
import com.dimproject.world.feature.features.RoomFeature;
import com.dimproject.world.feature.placement.BlockStatePlacementConfig;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;




public final class DimProjectFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.Keys.FEATURES, DimProjectMod.MODID);
	
	public static final RegistryObject<Feature<BlockStatePlacementConfig>> LELYETIAN_HOLE = FEATURES.register("lelyetian_hole", () -> new RoomFeature(BlockStatePlacementConfig.CODEC));
	public static final RegistryObject<Feature<BlockStatePlacementConfig>> LEDGE = FEATURES.register("ledge", () -> new LedgeFeature(BlockStatePlacementConfig.CODEC));
	
	public static void register(IEventBus eventBus) {
		FEATURES.register(eventBus);
    }
	

}






