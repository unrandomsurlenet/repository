package com.dimproject.util.teleportation;

import com.dimproject.DimProjectMod;
import com.dimproject.content.block.LibrairyPortalBlock;
import com.dimproject.registries.DimProjectBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dimproject", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PortalEntityHandler {

    @SubscribeEvent
    public static void onEntityTravelToDimension(EntityTravelToDimensionEvent event) {
        // Interception si besoin de logique custom au voyage
    }

    public static void teleportEntity(Entity entity) {
        if (!(entity.level() instanceof ServerLevel serverLevel)) return;

        ResourceKey<Level> current  = entity.level().dimension();
        ResourceKey<Level> dimKey   = DimProjectMod.LIBRAIRY;
        ResourceKey<Level> overworld = Level.OVERWORLD;

        ResourceKey<Level> dest = current.equals(dimKey) ? overworld : dimKey;
        ServerLevel destLevel   = serverLevel.getServer().getLevel(dest);

        if (destLevel == null) return;

        double destX = entity.getX();
        double destZ = entity.getZ();
        double destY = entity.getY();

        entity.changeDimension(destLevel, new ITeleporter() {
            @Override
            public Entity placeEntity(Entity entity, ServerLevel currentLevel,
                                      ServerLevel destLevel, float yaw,
                                      java.util.function.Function<Boolean, Entity> repositionEntity) {

                // Cherche un portail existant proche
                BlockPos destPos = new BlockPos((int)destX, (int)destY, (int)destZ);
                BlockPos portalPos = findNearbyPortal(destLevel, destPos);

                if (portalPos != null) {
                    // Téléporte à l'entrée du portail trouvé
                    entity.setPos(portalPos.getX() + 0.5, portalPos.getY(), portalPos.getZ() + 0.5);
                } else {
                    // Génère un portail à destination
                	BlockPos realdestpos = generatePortalAt(destLevel, new BlockPos((int)destX, (int)destY, (int)destZ));
                    entity.setPos(realdestpos.getX(), realdestpos.getY(), realdestpos.getZ());
                }

                entity.setPortalCooldown();
                return repositionEntity.apply(false);
            }

            @Override
            public boolean isVanilla() {
                return false;
            }
        });
    }

    // Cherche un portail dans un rayon de 16 blocs
    private static BlockPos findNearbyPortal(ServerLevel level, BlockPos center) {
        int radius = 32;
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos check = center.offset(x, y, z);
                    if (level.getBlockState(check).is(DimProjectBlocks.LIBRAIRY_PORTAL_BLOCK.get())) {
                        return check;
                    }
                }
            }
        }
        return null;
    }

    // Génère un portail 2x3 à destination
    private static BlockPos generatePortalAt(ServerLevel level, BlockPos origin) {

        BlockPos pos = findValidPortalPosition(level, origin, 16);

        if (pos == null) {
            return null;
        }

        // Cadre
        for (int x = -1; x <= 2; x++) {
            level.setBlock(pos.offset(x, -1, 0),
                    DimProjectBlocks.PORTAL_FRAME.get().defaultBlockState(), 3);

            level.setBlock(pos.offset(x, 3, 0),
                    DimProjectBlocks.PORTAL_FRAME.get().defaultBlockState(), 3);
        }

        for (int y = 0; y <= 2; y++) {

            level.setBlock(pos.offset(-1, y, 0),
                    DimProjectBlocks.PORTAL_FRAME.get().defaultBlockState(), 3);

            level.setBlock(pos.offset(2, y, 0),
                    DimProjectBlocks.PORTAL_FRAME.get().defaultBlockState(), 3);

            level.setBlock(pos.offset(0, y, 0),
                    DimProjectBlocks.LIBRAIRY_PORTAL_BLOCK.get()
                            .defaultBlockState()
                            .setValue(LibrairyPortalBlock.AXIS, Direction.Axis.X), 3);

            level.setBlock(pos.offset(1, y, 0),
                    DimProjectBlocks.LIBRAIRY_PORTAL_BLOCK.get()
                            .defaultBlockState()
                            .setValue(LibrairyPortalBlock.AXIS, Direction.Axis.X), 3);
        }

        return pos;
    }
    private static BlockPos findValidPortalPosition(ServerLevel level, BlockPos center, int radius) {

        for (int r = 0; r <= radius; r++) {

            for (int x = -r; x <= r; x++) {
                for (int z = -r; z <= r; z++) {

                    BlockPos pos = center.offset(x, 0, z);

                    if (canPlacePortal(level, pos)) {
                        return pos;
                    }
                }
            }
        }

        return null;
    }
    private static boolean canPlacePortal(ServerLevel level, BlockPos pos) {

        for (int x = -1; x <= 2; x++) {
            for (int y = -1; y <= 3; y++) {

                BlockPos check = pos.offset(x, y, 0);

                if (!level.getBlockState(check).canBeReplaced()) {
                    return false;
                }
            }
        }

        return true;
    }
    
    
}