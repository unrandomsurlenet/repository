package com.dimproject.content.item;

import com.dimproject.registries.DimProjectSounds;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SkulrexItem extends Item{

	public SkulrexItem(Properties p_41383_) {
		super(p_41383_);
		// TODO Auto-generated constructor stub
	}
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                DimProjectSounds.SKULREX.get(), SoundSource.NEUTRAL, 0.5F, 1.0f);

        

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

}
