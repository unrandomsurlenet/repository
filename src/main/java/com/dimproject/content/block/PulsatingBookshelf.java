package com.dimproject.content.block;

import net.minecraft.world.level.block.Block;

import com.dimproject.registries.DimProjectBlocks;
import com.dimproject.registries.DimProjectParticles;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PulsatingBookshelf extends Block {

    public PulsatingBookshelf(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        ItemStack stack = player.getItemInHand(hand);

        if (!stack.is(Items.SHEARS)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide) {
            // Remplace le bloc par de la pierre
            level.setBlock(pos, DimProjectBlocks.SAGE_BOOKSHELF.get().defaultBlockState(), Block.UPDATE_ALL);

            // Fait tomber un diamant
            popResource(level, pos, new ItemStack(Items.DIAMOND));

            // Joue le son des cisailles
            level.playSound(
                    null,
                    pos,
                    SoundEvents.SHEEP_SHEAR,
                    SoundSource.BLOCKS,
                    1.0F,
                    1.0F
            );

            // Endommage les cisailles
            stack.hurtAndBreak(1, player,
                    p -> p.broadcastBreakEvent(hand));
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        for (int i = 0; i < 3; i++) {
            double x = pos.getX() + 0.2 + random.nextDouble() * 0.6;
            double y = pos.getY() + 0.2 + random.nextDouble() * 0.8;
            double z = pos.getZ() + 0.2 + random.nextDouble() * 0.6;

            level.addParticle(
                    DimProjectParticles.ARCANE.get(),
                    x, y, z,
                    0.3D, 0.02D, 0.0D
            );
            level.addParticle(
                    DimProjectParticles.ARCANE.get(),
                    x, y, z,
                    0.0D, 0.02D, 0.0D
            );
            level.addParticle(
                    DimProjectParticles.ARCANE.get(),
                    x, y, z,
                    0.0D, 0.02D, 0.3D
            );
        }
    }
}