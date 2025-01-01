package com.davigj.frame_changer.core.mixin;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

import static com.davigj.frame_changer.core.other.FCConstants.CHISEL_MAP;

@Mixin(targets = "com.ordana.spelunkery.items.HammerAndChiselItem")
public class HammerAndChiselItemMixin {
    @Inject(method = "getChiseled", at = @At("HEAD"), cancellable = true, remap = false)
    private static void dodgyProblemsRequireDodgierSolutions(BlockState state, CallbackInfoReturnable<Optional<BlockState>> cir) {
        if (CHISEL_MAP.containsKey(state.getBlock())) {
            BlockState convertedState = BlockUtil.transferAllBlockStates(state, CHISEL_MAP.get(state.getBlock()).defaultBlockState());
            cir.setReturnValue(Optional.ofNullable(convertedState));
        }
    }
}
