package com.davigj.frame_changer.core.other;

import com.davigj.frame_changer.core.FrameChanger;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class FCBlockTags {
    public static final TagKey<Block> PORTAL_FRAMES = blockTag("portal_frames");

    private static TagKey<Block> blockTag(String name) {
        return TagUtil.blockTag(FrameChanger.MOD_ID, name);
    }
}
