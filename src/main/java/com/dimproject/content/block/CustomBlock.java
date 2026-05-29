package com.dimproject.content.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;

/**
 * Classe de bloc personnalisé pour le mod DimProject
 * Extends la classe Block de Minecraft
 */
public class CustomBlock extends Block {

    public CustomBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            player.displayClientMessage(net.minecraft.network.chat.Component.literal("Vous avez cliqué sur un bloc personnalisé !"), false);
        }
        return InteractionResult.SUCCESS;
    }
}
