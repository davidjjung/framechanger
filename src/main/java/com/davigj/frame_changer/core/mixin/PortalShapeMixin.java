package com.davigj.frame_changer.core.mixin;

import com.davigj.frame_changer.core.FCConfig;
import com.davigj.frame_changer.core.other.FCBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;

@Mixin(PortalShape.class)
public abstract class PortalShapeMixin {

    @Mutable
    @Final
    @Shadow
    private static BlockBehaviour.StatePredicate FRAME;

    static {
        FRAME = (state, world, pos) -> {
            return FCConfig.COMMON.portalFrameTag.get()
                    ? state.is(FCBlockTags.PORTAL_FRAMES)
                    : state.isPortalFrame(world, pos);
        };
    }

    @Shadow @Final private Direction rightDir;
    @Shadow @Final private Direction.Axis axis;
    @Shadow private net.minecraft.world.level.LevelAccessor level;

    @Unique private boolean fc$valid = false;
    @Unique private final HashSet<BlockPos> fc$portalPositions = new HashSet<>();
    @Unique private int fc$portalBlockCount = 0;
    @Unique private BlockPos fc$origin;

    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void fc$isValid(CallbackInfoReturnable<Boolean> cir) {
        if (!FCConfig.COMMON.wildShape.get()) return;
        this.fc$checkArea();
        cir.setReturnValue(fc$valid);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void fc$initOrigin(LevelAccessor level, BlockPos pos, Direction.Axis axis, CallbackInfo ci) {
        this.fc$origin = pos;
    }

    @Inject(method = "createPortalBlocks", at = @At("HEAD"), cancellable = true)
    private void fc$createPortal(CallbackInfo ci) {
        if (!FCConfig.COMMON.wildShape.get()) return;
        BlockState portalState = Blocks.NETHER_PORTAL.defaultBlockState().setValue(NetherPortalBlock.AXIS, this.axis);
        for (BlockPos pos : fc$portalPositions) {
            level.setBlock(pos, portalState, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
        }
        ci.cancel();
    }

    @Inject(method = "isComplete", at = @At("HEAD"), cancellable = true)
    private void fc$isComplete(CallbackInfoReturnable<Boolean> cir) {
        if (!FCConfig.COMMON.wildShape.get()) return;
        cir.setReturnValue(fc$valid && fc$portalPositions.size() == fc$portalBlockCount);
    }

    @Unique
    private void fc$checkArea() {
        fc$portalPositions.clear();
        fc$portalBlockCount = 0;
        fc$valid = false;

        if (fc$origin == null) return;

        HashSet<BlockPos> visitedFrame = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(fc$origin);

        List<Direction> directions = List.of(Direction.UP, Direction.DOWN, rightDir, rightDir.getOpposite());
        while (!queue.isEmpty()) {
            BlockPos pos = queue.poll();
            if (fc$portalPositions.contains(pos) || visitedFrame.contains(pos)) continue;
            BlockState state = level.getBlockState(pos);
            boolean isPortal = fc$isValidInterior(state, pos);
            boolean isFrame = FRAME.test(state, level, pos);
            if (!isPortal && !isFrame) {
                fc$valid = false;
                return;
            }
            if (isPortal) {
                fc$portalPositions.add(pos);
                if (fc$portalPositions.size() > FCConfig.COMMON.maxSize.get()) {
                    fc$valid = false;
                    return;
                }
                if (state.is(Blocks.NETHER_PORTAL)) {
                    fc$portalBlockCount++;
                }
                for (Direction dir : directions) {
                    queue.add(pos.relative(dir));
                }
            } else {
                visitedFrame.add(pos);
            }
        }
        int size = fc$portalPositions.size();
        fc$valid = size >= FCConfig.COMMON.minSize.get();
    }

    @Unique
    private boolean fc$isValidInterior(BlockState state, BlockPos pos) {
        return (state.isAir() || state.is(Blocks.FIRE) || state.is(Blocks.NETHER_PORTAL))
                && !level.isOutsideBuildHeight(pos);
    }
}