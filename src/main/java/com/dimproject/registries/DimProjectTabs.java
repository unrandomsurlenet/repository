package com.dimproject.registries;

import com.dimproject.DimProjectMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DimProjectTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DimProjectMod.MODID);
    
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> DimProjectItems.BOOK.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
            	DimProjectItems.ITEMS.getEntries().forEach(item ->
                output.accept(item.get()));
            }).build());
    
    
    
    public static void register(IEventBus eventBus) {
    	CREATIVE_MODE_TABS.register(eventBus);
    }


}
