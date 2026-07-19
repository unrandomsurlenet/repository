package com.dimproject.content.item.curios;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class FocusReticleItem extends Item implements ICurioItem{

	public FocusReticleItem(Properties p_41383_) {
		super(p_41383_);
	}
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
	      super.appendHoverText(itemstack, level, list, flag);
	      list.add(Component.translatable("item.terramity.agility_ring.description_0"));
	      list.add(Component.translatable("item.terramity.agility_ring.description_1"));
	      list.add(Component.translatable("item.terramity.agility_ring.description_2"));
	      list.add(Component.translatable("item.terramity.agility_ring.description_3"));
	   }
	

}
