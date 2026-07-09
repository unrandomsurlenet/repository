package com.dimproject.util;


import com.dimproject.network.PacketHandler;
import com.dimproject.network.packet.ServerboundBetterCombatAttackStartListenerPacket;

import net.bettercombat.api.client.BetterCombatClientEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class BetterCombatIntegration {
   public static void register() {
      BetterCombatClientEvents.ATTACK_START.register((BetterCombatClientEvents.PlayerAttackStart)(player, hand) -> {
         if (player.level() instanceof ClientLevel) {
            if (hand != null) {
               ItemStack stack = hand.isOffHand() ? player.getOffhandItem() : player.getMainHandItem();
               PacketHandler.sendToServer(new ServerboundBetterCombatAttackStartListenerPacket(stack, hand.combo().current()));
            }
         }
      });
      BetterCombatClientEvents.ATTACK_HIT.register((BetterCombatClientEvents.PlayerAttackHit)(player, hand, targets, cursorTarget) -> {
         if (player.level() instanceof ClientLevel) {
            if (hand != null) {
               ItemStack stack = hand.isOffHand() ? player.getOffhandItem() : player.getMainHandItem();
               PacketHandler.sendToServer(new ServerboundBetterCombatAttackStartListenerPacket(stack, hand.combo().current()));
            }
         }
      });
   }
}
