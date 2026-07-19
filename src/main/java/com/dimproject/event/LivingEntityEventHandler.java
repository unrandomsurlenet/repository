package com.dimproject.event;

import com.dimproject.DimProjectMod;
import com.dimproject.registries.DimProjectItems;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import top.theillusivec4.curios.api.CuriosApi;

@EventBusSubscriber(modid = DimProjectMod.MODID)
public class LivingEntityEventHandler {
	@SubscribeEvent
	   public static void onLivingHurt(LivingHurtEvent event) {
		LivingEntity victim = event.getEntity();
	     Entity attacker = event.getSource().getEntity();
	     Level level = victim.level();
	     float[] modifiedAmount = new float[]{event.getAmount()};
		if (attacker instanceof LivingEntity livingAttacker) {
			CuriosApi.getCuriosInventory(livingAttacker).ifPresent((handler) -> {
				if (handler.isEquipped((Item)DimProjectItems.FOCUS_RETICLE.get()) && victim.getHealth() / victim.getMaxHealth() < 0.5F) {

		               modifiedAmount[0] *= 1.3F;
		            }
			});
				
			
			
		}
		event.setAmount(modifiedAmount[0]);
		
	}
	
}