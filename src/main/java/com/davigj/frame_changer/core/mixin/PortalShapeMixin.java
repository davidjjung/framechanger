package com.davigj.frame_changer.core.mixin;

import com.davigj.frame_changer.core.FCConfig;
import com.davigj.frame_changer.core.other.FCBlockTags;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PortalShape.class)
public class PortalShapeMixin {

    @Mutable
    @Final
    @Shadow
    private static final BlockBehaviour.StatePredicate FRAME;

    static {
        FRAME = (p_77720_, p_77721_, p_77722_) -> {
            return FCConfig.COMMON.portalFrameTag.get() ? p_77720_.is(FCBlockTags.PORTAL_FRAMES) : p_77720_.isPortalFrame(p_77721_, p_77722_);
        };
    }
}
