package com.davigj.frame_changer.core.data.client;

import com.davigj.frame_changer.core.FrameChanger;
import com.davigj.frame_changer.core.registry.FCBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FCBlockStateProvider extends BlockStateProvider {
    public FCBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, FrameChanger.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.pillarBlock(FCBlocks.OBSIDIAN_PILLAR.get());
        this.pillarBlock(FCBlocks.CRYING_OBSIDIAN_PILLAR.get());
        this.block(FCBlocks.CHISELED_OBSIDIAN.get());
        this.block(FCBlocks.CRYING_CHISELED_OBSIDIAN.get());
        this.block(FCBlocks.OBSIDIAN_BRICKS.get());
        this.block(FCBlocks.CRYING_OBSIDIAN_BRICKS.get());
        this.block(FCBlocks.POLISHED_OBSIDIAN.get());
        this.block(FCBlocks.CRYING_POLISHED_OBSIDIAN.get());
    }

    public void block(Block block) {
        this.simpleBlock(block, this.cubeAll(block));
        this.blockItem(block);
    }

    public void pillarBlock(Block pillar) {
        this.logBlock((RotatedPillarBlock) pillar);
        this.blockItem(pillar);
    }

    public void blockItem(Block block) {
        this.simpleBlockItem(block, new ModelFile.ExistingModelFile(this.blockTexture(block), this.models().existingFileHelper));
    }
}
