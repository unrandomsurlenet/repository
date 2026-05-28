package com.dimproject.registries;





import com.dimproject.DimProjectMod;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DimProjectItems {
	public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DimProjectMod.MODID);

    public static final RegistryObject<Item> SKULREX = ITEMS.register("skulrex",
            () -> new Item(new Item.Properties()));
    

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}