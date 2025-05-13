package com.davigj.frame_changer.core.data.client;

import com.davigj.frame_changer.core.FrameChanger;
import com.davigj.frame_changer.core.registry.FCBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class FCBlockStateProvider extends BlockStateProvider {
    public FCBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FrameChanger.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.pillarBlock(FCBlocks.OBSIDIAN_PILLAR.get());
        this.pillarBlock(FCBlocks.CRYING_OBSIDIAN_PILLAR.get());
        this.block(FCBlocks.CHISELED_OBSIDIAN.get());
        this.block(FCBlocks.CRYING_CHISELED_OBSIDIAN.get());

        this.groupSSW(FCBlocks.OBSIDIAN_BRICKS.get(), FCBlocks.OBSIDIAN_BRICK_STAIRS.get(),
                FCBlocks.OBSIDIAN_BRICK_SLAB.get(), FCBlocks.OBSIDIAN_BRICK_WALL.get());
        this.groupSSW(FCBlocks.CRYING_OBSIDIAN_BRICKS.get(), FCBlocks.CRYING_OBSIDIAN_BRICK_STAIRS.get(),
                FCBlocks.CRYING_OBSIDIAN_BRICK_SLAB.get(), FCBlocks.CRYING_OBSIDIAN_BRICK_WALL.get());

        this.groupSSW(FCBlocks.POLISHED_OBSIDIAN.get(), FCBlocks.POLISHED_OBSIDIAN_STAIRS.get(),
                FCBlocks.POLISHED_OBSIDIAN_SLAB.get(), FCBlocks.POLISHED_OBSIDIAN_WALL.get());
        this.groupSSW(FCBlocks.CRYING_POLISHED_OBSIDIAN.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS.get(),
                FCBlocks.CRYING_POLISHED_OBSIDIAN_SLAB.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN_WALL.get());
    }

    public void groupSSW(Block parent, Block stairs, Block slab, Block wall) {
        this.block(parent);
        this.stairs(stairs, parent);
        this.slab(slab, parent);
        this.wall(wall, parent);
    }
    public void block(Block block) {
        // this uses vanilla datagen to make the blockstate and block model files
        this.simpleBlock(block, this.cubeAll(block));
        // this generates the item model
        this.blockItem(block);
    }

    public void pillarBlock(Block pillar) {
        this.logBlock((RotatedPillarBlock) pillar);
        this.blockItem(pillar);
    }

    public void stairs(Block stairs, Block parent) {
        this.stairsBlock((StairBlock) stairs, blockTexture(parent));
        this.blockItem(stairs);
    }
    public void slab(Block slab, Block parent) {
        this.slabBlock((SlabBlock) slab, blockTexture(parent), blockTexture(parent));
        this.blockItem(slab);
    }
    public void wall(Block wall, Block parent) {
        this.wallBlock((WallBlock) wall, blockTexture(parent));
        this.itemModels().getBuilder(name(wall)).parent(this.models().wallInventory(name(wall) + "_inventory", blockTexture(parent)));
    }

    public void blockItem(Block block) {
        this.simpleBlockItem(block, new ModelFile.ExistingModelFile(this.blockTexture(block), this.models().existingFileHelper));
    }

    private String name(Block block) {
        return Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block)).getPath();
    }
}
