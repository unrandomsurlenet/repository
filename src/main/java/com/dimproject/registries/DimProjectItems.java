package com.dimproject.registries;





import com.dimproject.DimProjectMod;
import com.dimproject.content.item.SkulrexItem;
import com.dimproject.content.tiers.ModTiers;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DimProjectItems {
	public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DimProjectMod.MODID);

    public static final RegistryObject<Item> SKULREX = ITEMS.register("skulrex",
            () -> new SkulrexItem(new Item.Properties()));
    public static final RegistryObject<Item> FOCUS_RETICLE = ITEMS.register("focus_reticle",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INFINITE_VOID = ITEMS.register("infinite_void",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOOK = ITEMS.register("book",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EVANESCENCE = ITEMS.register("evanescence",
    	    () -> new com.dimproject.content.item.Evanescence(
    	        ModTiers.MON_MATERIAU,
    	        7,      // dégâts supplémentaires
    	        -2.8F,  // vitesse d'attaque (valeur vanilla épée)
    	        new Item.Properties()
    	    )
    	);
    public static final RegistryObject<Item> NIGHT_TERROR = ITEMS.register("night_terror",
    	    () -> new com.dimproject.content.item.NightTerror(
    	        ModTiers.MON_MATERIAU,
    	        7,      // dégâts supplémentaires
    	        -2.8F,  // vitesse d'attaque (valeur vanilla épée)
    	        new Item.Properties()
    	    )
    	);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}