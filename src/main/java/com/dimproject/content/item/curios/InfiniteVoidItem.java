package com.dimproject.content.item.curios;

import java.util.List;

import com.dimproject.procedure.InfiniteVoidProcedure;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class InfiniteVoidItem extends Item implements ICurioItem{

	public InfiniteVoidItem(Properties p_41383_) {
		super(p_41383_);
	}
	
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
	      super.appendHoverText(itemstack, level, list, flag);
	      list.add(Component.translatable("item.dimproject.infinite_void.description_0"));
	      list.add(Component.translatable("item.dimproject.infinite_void.description_1"));
	      list.add(Component.translatable("item.dimproject.infinite_void.description_2"));
	      list.add(Component.translatable("item.dimproject.infinite_void.description_3"));
	   }
	
	
	

}
