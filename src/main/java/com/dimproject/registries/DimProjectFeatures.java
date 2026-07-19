package com.dimproject.registries;

import com.dimproject.DimProjectMod;
import com.dimproject.world.feature.config.CappedColumnConfig;
import com.dimproject.world.feature.config.ColumnConfig;
import com.dimproject.world.feature.features.CappedColumnFeature;
import com.dimproject.world.feature.features.ColumnFeature;
import com.dimproject.world.feature.features.FlyingRockFeature;
import com.dimproject.world.feature.features.LedgeFeature;
import com.dimproject.world.feature.features.RoomFeature;
import com.dimproject.world.feature.features.tree.ArcaneTreeFeature;
import com.dimproject.world.feature.placement.BlockStatePlacementConfig;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;




public final class DimProjectFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.Keys.FEATURES, DimProjectMod.MODID);
	
	public static final RegistryObject<Feature<BlockStatePlacementConfig>> LELYETIAN_HOLE = FEATURES.register("lelyetian_hole", () -> new RoomFeature(BlockStatePlacementConfig.CODEC));
	public static final RegistryObject<Feature<BlockStatePlacementConfig>> LEDGE = FEATURES.register("ledge", () -> new LedgeFeature(BlockStatePlacementConfig.CODEC));
	public static final RegistryObject<Feature<BlockStatePlacementConfig>> FLYING_ROCK = FEATURES.register("flying_rock", () -> new FlyingRockFeature(BlockStatePlacementConfig.CODEC));
	public static final RegistryObject<Feature<ColumnConfig>> COLUMN = FEATURES.register("column", () -> new ColumnFeature(ColumnConfig.CODEC));
	public static final RegistryObject<Feature<CappedColumnConfig>> CAPPED_COLUMN = FEATURES.register("capped_column", () -> new CappedColumnFeature(CappedColumnConfig.CODEC));
	
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> ARCANE_TREE =FEATURES.register("arcane_tree", ArcaneTreeFeature::new);
	
	public static void register(IEventBus eventBus) {
		FEATURES.register(eventBus);
    }
	

}






