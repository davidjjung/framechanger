package com.davigj.frame_changer.core.data.server;

import com.davigj.frame_changer.core.FrameChanger;
import com.davigj.frame_changer.core.other.FCBlockTags;
import com.davigj.frame_changer.core.registry.FCBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class FCBlockTagsProvider extends BlockTagsProvider {

    public FCBlockTagsProvider(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, FrameChanger.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        this.tag(FCBlockTags.PORTAL_FRAMES).add(
                FCBlocks.OBSIDIAN_BRICKS.get(),
                FCBlocks.OBSIDIAN_PILLAR.get(),
                FCBlocks.POLISHED_OBSIDIAN.get(),
                FCBlocks.CHISELED_OBSIDIAN.get(),
                Blocks.OBSIDIAN
        );
        this.tag(BlockTags.WALLS).add(
                FCBlocks.CRYING_OBSIDIAN_BRICK_WALL.get(),
                FCBlocks.POLISHED_OBSIDIAN_WALL.get(),
                FCBlocks.CRYING_POLISHED_OBSIDIAN_WALL.get(),
                FCBlocks.OBSIDIAN_BRICK_WALL.get()
        );
    }
}
