package com.dimproject.content.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Classe de bloc personnalisé pour le mod DimProject
 * Extends la classe Block de Minecraft
 * Bounding box de 1x3x4
 */
public class CustomBlock extends Block {

    // Définition de la bounding box personnalisée
    // Format: minX, minY, minZ, maxX, maxY, maxZ (en unités de 1/16ème de block)
    // Pour 1x3x4 : 1 block de large, 3 blocks de haut, 4 blocks de profondeur
    private static final VoxelShape SHAPE = Shapes.box(
            0.0,   // minX
            0.0,   // minY
            -1.5,  // minZ (2 blocks en arrière, 1.5 de chaque côté)
            1.0,   // maxX
            3.0,   // maxY (3 blocks en hauteur)
            2.5    // maxZ (2 blocks en avant)
    );

    public CustomBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            player.displayClientMessage(net.minecraft.network.chat.Component.literal("Vous avez cliqué sur un bloc personnalisé !"), false);
        }
        return InteractionResult.SUCCESS;
    }
}
