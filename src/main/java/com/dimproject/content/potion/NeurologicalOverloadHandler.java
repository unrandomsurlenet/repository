package com.dimproject.content.potion;

import com.dimproject.registries.DimProjectMobEffect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dimproject", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NeurologicalOverloadHandler {
	private static double lockedX, lockedY, lockedZ;
    private static boolean posLocked = false;

    // Bloque les interactions clavier (attaque, utilisation d'item)
    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!player.hasEffect(DimProjectMobEffect.NEUROLOGICAL_OVERLOAD.get())) return;
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        if (!event.getEntity().hasEffect(DimProjectMobEffect.NEUROLOGICAL_OVERLOAD.get())) return;
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getEntity().hasEffect(DimProjectMobEffect.NEUROLOGICAL_OVERLOAD.get())) return;
        event.setCanceled(true);
    }
    @SubscribeEvent
    public static void onPlayerLeftClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getEntity().hasEffect(DimProjectMobEffect.NEUROLOGICAL_OVERLOAD.get())) return;
        event.setCanceled(true);
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer player)) return;

        if (!player.hasEffect(DimProjectMobEffect.NEUROLOGICAL_OVERLOAD.get())) {
            posLocked = false; // reset quand l'effet se termine
            return;
        }

        // Verrouille la position au premier tick
        if (!posLocked) {
            lockedX = player.getX();
            lockedY = player.getY();
            lockedZ = player.getZ();
            posLocked = true;
        }

        // Force la position verrouillée chaque tick
        player.setPos(lockedX, lockedY, lockedZ);
        player.setDeltaMovement(0, 0, 0);
        player.connection.teleport(lockedX, lockedY, lockedZ,
            player.getYRot(), player.getXRot());
    }
}