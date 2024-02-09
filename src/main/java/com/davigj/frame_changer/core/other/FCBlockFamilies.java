package com.davigj.frame_changer.core.other;

import com.davigj.frame_changer.core.registry.FCBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

public class FCBlockFamilies {
    public static final BlockFamily OBSIDIAN_BRICKS_FAMILY;
    public static final BlockFamily CRYING_OBSIDIAN_BRICKS_FAMILY;
    public static final BlockFamily POLISHED_OBSIDIAN_FAMILY;
    public static final BlockFamily CRYING_POLISHED_OBSIDIAN_FAMILY;
    public FCBlockFamilies() {
    }

    static {
        OBSIDIAN_BRICKS_FAMILY = (new BlockFamily.Builder((Block) FCBlocks.OBSIDIAN_BRICKS.get()))
                .wall((Block) FCBlocks.OBSIDIAN_BRICK_WALL.get())
                .stairs((Block) FCBlocks.OBSIDIAN_BRICK_STAIRS.get())
                .slab((Block) FCBlocks.OBSIDIAN_BRICK_SLAB.get())
                .chiseled((Block) FCBlocks.CHISELED_OBSIDIAN.get())
                .getFamily();

        CRYING_OBSIDIAN_BRICKS_FAMILY = (new BlockFamily.Builder((Block) FCBlocks.CRYING_OBSIDIAN_BRICKS.get()))
                .wall((Block) FCBlocks.CRYING_OBSIDIAN_BRICK_WALL.get())
                .stairs((Block) FCBlocks.CRYING_OBSIDIAN_BRICK_STAIRS.get())
                .slab((Block) FCBlocks.CRYING_OBSIDIAN_BRICK_SLAB.get())
                .chiseled((Block) FCBlocks.CRYING_CHISELED_OBSIDIAN.get())
                .getFamily();

        POLISHED_OBSIDIAN_FAMILY = (new BlockFamily.Builder((Block) FCBlocks.POLISHED_OBSIDIAN.get()))
                .wall((Block) FCBlocks.POLISHED_OBSIDIAN_WALL.get())
                .stairs((Block) FCBlocks.POLISHED_OBSIDIAN_STAIRS.get())
                .slab((Block) FCBlocks.POLISHED_OBSIDIAN_SLAB.get())
                .getFamily();

        CRYING_POLISHED_OBSIDIAN_FAMILY = (new BlockFamily.Builder((Block) FCBlocks.CRYING_POLISHED_OBSIDIAN.get()))
                .wall((Block) FCBlocks.CRYING_POLISHED_OBSIDIAN_WALL.get())
                .stairs((Block) FCBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS.get())
                .slab((Block) FCBlocks.CRYING_POLISHED_OBSIDIAN_SLAB.get())
                .getFamily();

    }
}
