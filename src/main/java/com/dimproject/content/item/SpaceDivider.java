package com.dimproject.content.item;

import com.dimproject.content.entity.pet.BatlingEntity;
import com.dimproject.content.entity.projectile.ThrownBookEntity;
import com.dimproject.registries.DimProjectEntities;

import java.util.List;
import net.minecraft.network.chat.Component;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;

public class SpaceDivider extends SwordItem {



    public SpaceDivider(Tier tier, int attackDamageBonus, float attackSpeed, Properties properties) {
        super(tier, attackDamageBonus, attackSpeed, properties);
    }
    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("item.dimproject.evanescence.description_0"));
        list.add(Component.translatable("§7"));
        list.add(Component.translatable("§6On Swing:"));
        list.add(Component.translatable("§e -Release 3 book"));
     }

//    @Override
//    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
//        if (entity instanceof Player player && !player.level().isClientSide) {
//            if (!ModList.get().isLoaded("bettercombat")) {
//            	spawnBatling(player); // vanilla seulement
//            }
//        }
//        return super.onEntitySwing(stack, entity);
//    }
//
//    public void spawnBatling(Player player) {
//        Level level = player.level();
//        if (level.random.nextInt(3) == 0) {
//            BatlingEntity bat = new BatlingEntity(DimProjectEntities.BATLING.get(), level);
//            bat.moveTo(player.getX(), player.getY() + 1, player.getZ(), 0f, 0f);
//            level.addFreshEntity(bat);
//        }
//        
//    }
}