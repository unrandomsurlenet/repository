package com.dimproject.registries;





import com.dimproject.DimProjectMod;
import com.dimproject.content.potion.NeurologicalOverloadEffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DimProjectMobEffect {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DimProjectMod.MODID);

	public static final RegistryObject<MobEffect> NEUROLOGICAL_OVERLOAD = MOB_EFFECTS.register("neurological_overload", () -> new NeurologicalOverloadEffect());

    public static void register(IEventBus eventBus) {
    	MOB_EFFECTS.register(eventBus);
    }
}