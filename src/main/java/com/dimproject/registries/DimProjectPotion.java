package com.dimproject.registries;

import com.dimproject.DimProjectMod;
import com.dimproject.content.item.SkulrexItem;
import com.dimproject.content.item.curios.FocusReticleItem;
import com.dimproject.content.item.curios.InfiniteVoidItem;
import com.dimproject.content.tiers.ModTiers;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DimProjectPotion {
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, DimProjectMod.MODID);

	 public static final RegistryObject<Potion> NEUROLOGICAL_OVERLOAD = POTIONS.register("neurological_overload", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)DimProjectMobEffect.NEUROLOGICAL_OVERLOAD.get(), 600, 0, false, true)}));

    public static void register(IEventBus eventBus) {
    	POTIONS.register(eventBus);
    }
}