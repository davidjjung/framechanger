package com.davigj.frame_changer.core.mixin;

import com.davigj.frame_changer.core.FCConfig;
import com.davigj.frame_changer.core.FrameChanger;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalForcer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(PortalForcer.class)
public class PortalForcerMixin {
    // Bad code! Bad code !! :sob:
    @Unique
    private final static Logger framechanger$LOGGER = LogManager.getLogger(FrameChanger.MOD_ID);

    @Mutable
    @Final
    @Shadow
    protected final ServerLevel level;

    public PortalForcerMixin(ServerLevel level) {
        this.level = level;
    }


    @Inject(method = "createPortal", at = @At("HEAD"), cancellable = true)
    private void onCreatePortal(BlockPos p_77667_, Direction.Axis p_77668_, CallbackInfoReturnable<Optional<BlockUtil.FoundRectangle>> cir) {
        if (FCConfig.COMMON.customExitPortals.get()) {
            cir.setReturnValue(this.framechanger$createCustomPortal(p_77667_, p_77668_));
        }
    }

    @Unique
    private static Supplier<Block> framechanger$getCompatBlock(String fullId) {
        String[] parts = fullId.split(":");
        if (parts.length != 2) {
            framechanger$LOGGER.warn("Improperly formatted config for generated portal frames. String should be formatted 'modid:blockID'. Defaulting to minecraft:obsidian");
        }

        String modid = parts[0];
        String blockID = parts[1];

        if (!ModList.get().isLoaded(modid) && modid != null) {
            framechanger$LOGGER.warn("Mod '" + modid + "' not loaded, invalid configured exit portal frame blockID. String should be formatted 'modID:blockID'. Defaulting to minecraft:obsidian");
            return () -> Blocks.OBSIDIAN;
        }
        assert modid != null;
        ResourceLocation block = new ResourceLocation(modid, blockID);
        if (ForgeRegistries.BLOCKS.getValue(block) == Blocks.AIR) {
            framechanger$LOGGER.warn("Invalid configured exit portal frame blockID. String should be formatted 'modID:blockID'. Defaulting to minecraft:obsidian");
            return () -> Blocks.OBSIDIAN;
        }
        return (ModList.get().isLoaded(modid) ? () -> ForgeRegistries.BLOCKS.getValue(block) : () -> null);
    }

    @Unique
    private Optional<BlockUtil.FoundRectangle> framechanger$createCustomPortal(BlockPos pos, Direction.Axis axis) {
        PortalForcer forcer = (PortalForcer) (Object) this;
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0D;
        BlockPos blockpos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        WorldBorder worldborder = this.level.getWorldBorder();
        int i = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

        for(BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int j = Math.min(i, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getZ()));
            int k = 1;
            if (worldborder.isWithinBounds(blockpos$mutableblockpos1) && worldborder.isWithinBounds(blockpos$mutableblockpos1.move(direction, 1))) {
                blockpos$mutableblockpos1.move(direction.getOpposite(), 1);

                for(int l = j; l >= this.level.getMinBuildHeight(); --l) {
                    blockpos$mutableblockpos1.setY(l);
                    if (this.level.isEmptyBlock(blockpos$mutableblockpos1)) {
                        int i1;
                        for(i1 = l; l > this.level.getMinBuildHeight() && this.level.isEmptyBlock(blockpos$mutableblockpos1.move(Direction.DOWN)); --l) {
                        }

                        if (l + 4 <= i) {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3) {
                                blockpos$mutableblockpos1.setY(l);
                                if (forcer.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, 0)) {
                                    double d2 = pos.distSqr(blockpos$mutableblockpos1);
                                    if (forcer.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, -1) && forcer.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, 1) && (d0 == -1.0D || d0 > d2)) {
                                        d0 = d2;
                                        blockpos = blockpos$mutableblockpos1.immutable();
                                    }

                                    if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
                                        d1 = d2;
                                        blockpos1 = blockpos$mutableblockpos1.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d0 == -1.0D && d1 != -1.0D) {
            blockpos = blockpos1;
            d0 = d1;
        }

        if (d0 == -1.0D) {
            int k1 = Math.max(this.level.getMinBuildHeight() - -1, 70);
            int i2 = i - 9;
            if (i2 < k1) {
                return Optional.empty();
            }

            blockpos = (new BlockPos(pos.getX(), Mth.clamp(pos.getY(), k1, i2), pos.getZ())).immutable();
            Direction direction1 = direction.getClockWise();
            if (!worldborder.isWithinBounds(blockpos)) {
                return Optional.empty();
            }

            for(int i3 = -1; i3 < 2; ++i3) {
                for(int j3 = 0; j3 < 2; ++j3) {
                    for(int k3 = -1; k3 < 3; ++k3) {
                        BlockState blockstate1 = k3 < 0 ? framechanger$getCompatBlock(FCConfig.COMMON.exitPortalFrame.get()).get().defaultBlockState() : Blocks.AIR.defaultBlockState();
                        blockpos$mutableblockpos.setWithOffset(blockpos, j3 * direction.getStepX() + i3 * direction1.getStepX(), k3, j3 * direction.getStepZ() + i3 * direction1.getStepZ());
                        this.level.setBlockAndUpdate(blockpos$mutableblockpos, blockstate1);
                    }
                }
            }
        }

        for(int l1 = -1; l1 < 3; ++l1) {
            for(int j2 = -1; j2 < 4; ++j2) {
                if (l1 == -1 || l1 == 2 || j2 == -1 || j2 == 3) {
                    blockpos$mutableblockpos.setWithOffset(blockpos, l1 * direction.getStepX(), j2, l1 * direction.getStepZ());
                    this.level.setBlock(blockpos$mutableblockpos, framechanger$getCompatBlock(FCConfig.COMMON.exitPortalFrame.get()).get().defaultBlockState(), 3);
                }
            }
        }

        BlockState blockstate = Blocks.NETHER_PORTAL.defaultBlockState().setValue(NetherPortalBlock.AXIS, axis);

        for(int k2 = 0; k2 < 2; ++k2) {
            for(int l2 = 0; l2 < 3; ++l2) {
                blockpos$mutableblockpos.setWithOffset(blockpos, k2 * direction.getStepX(), l2, k2 * direction.getStepZ());
                this.level.setBlock(blockpos$mutableblockpos, blockstate, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(blockpos.immutable(), 2, 3));
    }
}
