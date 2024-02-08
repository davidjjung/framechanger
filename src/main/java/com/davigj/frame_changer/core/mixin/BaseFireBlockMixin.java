package com.davigj.frame_changer.core.mixin;

import com.davigj.frame_changer.core.FCConfig;
import com.davigj.frame_changer.core.other.FCBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.BaseFireBlock.inPortalDimension;

@Mixin(BaseFireBlock.class)
public class BaseFireBlockMixin {
    @Inject(method = "isPortal", at = @At("HEAD"), cancellable = true)
    private static void cooking(Level level, BlockPos pos, Direction dir, CallbackInfoReturnable<Boolean> cir) {
        // I wonder if I should work on making head-inject mixins that don't just copy and paste the entire method :sob:
        if (FCConfig.COMMON.portalFrameTag.get()) {
            if (!inPortalDimension(level)) {
                cir.setReturnValue(false);
            } else {
                BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
                boolean flag = false;
                for(Direction direction : Direction.values()) {
                    if (level.getBlockState(blockpos$mutableblockpos.set(pos).move(direction)).is(FCBlockTags.PORTAL_FRAMES)) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    cir.setReturnValue(false);
                } else {
                    Direction.Axis direction$axis = dir.getAxis().isHorizontal() ? dir.getCounterClockWise().getAxis() : Direction.Plane.HORIZONTAL.getRandomAxis(level.random);
                    cir.setReturnValue(PortalShape.findEmptyPortalShape(level, pos, direction$axis).isPresent());
                }
            }
        }
    }

}
