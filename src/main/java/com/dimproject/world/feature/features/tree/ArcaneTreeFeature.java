package com.dimproject.world.feature.features.tree;

import com.dimproject.registries.DimProjectBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ArcaneTreeFeature extends Feature<NoneFeatureConfiguration> {

    public ArcaneTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos origin = ctx.origin();
        RandomSource rand = ctx.random();

        // Vérifie les 4 blocs du sol (tronc 2x2)
        for (int x = 0; x <= 1; x++) {
            for (int z = 0; z <= 1; z++) {
                BlockState ground = level.getBlockState(origin.offset(x, -1, z));

                if (!ground.is(Blocks.GRASS_BLOCK)
                        && !ground.is(Blocks.DIRT)
                        && !ground.is(DimProjectBlocks.SAGE_SOIL.get())) {
                    return false;
                }
            }
        }

        // Hauteur du tronc entre 12 et 18 blocs
        int trunkHeight = 8 + rand.nextInt(5);

        generateTrunk(level, origin, trunkHeight, rand);
        generateBranches(level, origin, trunkHeight, rand);
        generateCanopy(level, origin, trunkHeight, rand);

        return true;
    }

    private void generateTrunk(WorldGenLevel level, BlockPos origin, int height, RandomSource rand) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int y = 0; y < height; y++) {
            // Tronc 2x2 comme la mangrove
            for (int x = 0; x <= 1; x++) {
                for (int z = 0; z <= 1; z++) {
                    mutable.set(origin).move(x, y, z);
                    setBlock(level, mutable, DimProjectBlocks.SAGE_LOG.get().defaultBlockState());
                }
            }
        }
    }

    private void generateBranches(WorldGenLevel level, BlockPos origin, int trunkHeight, RandomSource rand) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        // 4 à 7 branches
        int branchCount = 3 + rand.nextInt(3);

        for (int i = 0; i < branchCount; i++) {
            // Hauteur de départ de la branche (entre 60% et 90% du tronc)
            int branchY = (int)(trunkHeight * (0.6f + rand.nextFloat() * 0.3f));

            // Direction aléatoire
            int dirX = rand.nextInt(3) - 1; // -1, 0 ou 1
            int dirZ = rand.nextInt(3) - 1;
            // Evite les branches qui ne vont nulle part
            if (dirX == 0 && dirZ == 0) dirX = 1;

            // Longueur de la branche entre 4 et 8 blocs
            int branchLength = 4 + rand.nextInt(5);

            // Monte légèrement tout en partant sur le côté
            for (int step = 0; step < branchLength; step++) {
                int bx = origin.getX() + dirX * step;
                int by = origin.getY() + branchY + (step / 2); // monte progressivement
                int bz = origin.getZ() + dirZ * step;

                mutable.set(bx, by, bz);
                setBlock(level, mutable, DimProjectBlocks.SAGE_LOG.get().defaultBlockState());

                // Petit feuillage le long de la branche
                if (step > branchLength / 2) {
                    generateLeafCluster(level, mutable, 2, rand);
                }
            }

            // Gros feuillage au bout de la branche
            mutable.set(
                origin.getX() + dirX * branchLength,
                origin.getY() + branchY + branchLength / 2,
                origin.getZ() + dirZ * branchLength
            );
            generateLeafCluster(level, mutable, 3 + rand.nextInt(2), rand);
        }
    }

    private void generateCanopy(WorldGenLevel level, BlockPos origin, int trunkHeight, RandomSource rand) {
        // Centre du feuillage au sommet du tronc
        BlockPos top = origin.offset(0, trunkHeight, 0);

        // Plusieurs couches de feuillage
        int[] radii = {5, 6, 5, 4, 3, 2};
        int[] yOffsets = {-2, -1, 0, 1, 2, 3};

        for (int layer = 0; layer < radii.length; layer++) {
            int radius = radii[layer];
            int yOff = yOffsets[layer];

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    // Forme circulaire avec un peu d'irrégularité
                    float dist = (float) Math.sqrt(x * x + z * z);
                    float threshold = radius - rand.nextFloat() * 1.5f;

                    if (dist <= threshold) {
                        BlockPos leafPos = top.offset(x, yOff, z);
                        if (level.getBlockState(leafPos).isAir()) {
                            setBlock(level, leafPos, DimProjectBlocks.SAGE_LEAVES.get().defaultBlockState());
                        }
                    }
                }
            }
        }

        // Quelques feuilles qui tombent vers le bas (comme la mangrove)
        generateHangingLeaves(level, top, rand);
    }

    private void generateHangingLeaves(WorldGenLevel level, BlockPos top, RandomSource rand) {
        int radius = 5;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                float dist = (float) Math.sqrt(x * x + z * z);
                if (dist > radius) continue;

                // Cherche le bas du feuillage
                for (int y = -1; y >= -4; y--) {
                    mutable.set(top).move(x, y, z);
                    if (level.getBlockState(mutable).isAir()) {
                        // 30% de chance de feuilles qui pendent
                        if (rand.nextFloat() < 0.3f) {
                            setBlock(level, mutable, DimProjectBlocks.SAGE_LEAVES.get().defaultBlockState());
                        }
                        break;
                    }
                }
            }
        }
    }

    private void generateLeafCluster(WorldGenLevel level, BlockPos center, int radius, RandomSource rand) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int x = -radius; x <= radius; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -radius; z <= radius; z++) {
                    float dist = (float) Math.sqrt(x * x + z * z);
                    if (dist <= radius - rand.nextFloat()) {
                        mutable.set(center).move(x, y, z);
                        if (level.getBlockState(mutable).isAir()) {
                            setBlock(level, mutable, DimProjectBlocks.SAGE_LEAVES.get().defaultBlockState());
                        }
                    }
                }
            }
        }
    }

    private void setBlock(WorldGenLevel level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state, 2);
    }
}