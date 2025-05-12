package com.davigj.frame_changer.core.data.server;

import com.davigj.frame_changer.core.FrameChanger;
import com.davigj.frame_changer.core.other.FCBlockTags;
import com.davigj.frame_changer.core.registry.FCBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FCBlockTagsProvider extends BlockTagsProvider {
    public FCBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, FrameChanger.MOD_ID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
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
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                FCBlocks.OBSIDIAN_BRICKS.get(),
                FCBlocks.OBSIDIAN_BRICK_SLAB.get(),
                FCBlocks.OBSIDIAN_BRICK_STAIRS.get(),
                FCBlocks.OBSIDIAN_BRICK_WALL.get(),
                FCBlocks.CRYING_OBSIDIAN_BRICKS.get(),
                FCBlocks.CRYING_OBSIDIAN_BRICK_SLAB.get(),
                FCBlocks.CRYING_OBSIDIAN_BRICK_STAIRS.get(),
                FCBlocks.CRYING_OBSIDIAN_BRICK_WALL.get(),
                FCBlocks.POLISHED_OBSIDIAN.get(),
                FCBlocks.POLISHED_OBSIDIAN_SLAB.get(),
                FCBlocks.POLISHED_OBSIDIAN_STAIRS.get(),
                FCBlocks.POLISHED_OBSIDIAN_WALL.get(),
                FCBlocks.CRYING_POLISHED_OBSIDIAN.get(),
                FCBlocks.CRYING_POLISHED_OBSIDIAN_SLAB.get(),
                FCBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS.get(),
                FCBlocks.CRYING_POLISHED_OBSIDIAN_WALL.get(),
                FCBlocks.OBSIDIAN_PILLAR.get(),
                FCBlocks.CRYING_OBSIDIAN_PILLAR.get(),
                FCBlocks.CHISELED_OBSIDIAN.get(),
                FCBlocks.CRYING_CHISELED_OBSIDIAN.get()
        );

    }
}
