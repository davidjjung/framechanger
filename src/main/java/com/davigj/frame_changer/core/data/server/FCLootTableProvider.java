package com.davigj.frame_changer.core.data.server;

import com.davigj.frame_changer.core.FrameChanger;
import com.davigj.frame_changer.core.registry.FCBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FCLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> tables;
    public FCLootTableProvider(DataGenerator p_124437_) {
        super(p_124437_);
        this.tables = ImmutableList.of(Pair.of(FCLootTableProvider.FCBlockLoot::new, LootContextParamSets.BLOCK));
    }

    public List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return this.tables;
    }

    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {
    }

    private static class FCBlockLoot extends BlockLoot {
        private FCBlockLoot() {
        }
        public void addTables() {
            this.dropSelf((Block) FCBlocks.OBSIDIAN_BRICKS.get());
            this.dropSelf((Block) FCBlocks.OBSIDIAN_BRICK_STAIRS.get());
            this.dropSelf((Block) FCBlocks.OBSIDIAN_BRICK_WALL.get());
            this.dropSelf((Block) FCBlocks.CRYING_OBSIDIAN_BRICKS.get());
            this.dropSelf((Block) FCBlocks.CRYING_OBSIDIAN_BRICK_STAIRS.get());
            this.dropSelf((Block) FCBlocks.CRYING_OBSIDIAN_BRICK_WALL.get());
            this.dropSelf((Block) FCBlocks.POLISHED_OBSIDIAN.get());
            this.dropSelf((Block) FCBlocks.POLISHED_OBSIDIAN_STAIRS.get());
            this.dropSelf((Block) FCBlocks.POLISHED_OBSIDIAN_WALL.get());
            this.dropSelf((Block) FCBlocks.CRYING_POLISHED_OBSIDIAN.get());
            this.dropSelf((Block) FCBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS.get());
            this.dropSelf((Block) FCBlocks.CRYING_POLISHED_OBSIDIAN_WALL.get());
            this.dropSelf((Block) FCBlocks.OBSIDIAN_PILLAR.get());
            this.dropSelf((Block) FCBlocks.CRYING_OBSIDIAN_PILLAR.get());
            this.dropSelf((Block) FCBlocks.CHISELED_OBSIDIAN.get());
            this.dropSelf((Block) FCBlocks.CRYING_CHISELED_OBSIDIAN.get());

            this.add((Block)FCBlocks.OBSIDIAN_BRICK_SLAB.get(), (x$0) -> {
                return BlockLoot.createSlabItemTable(x$0);
            });
            this.add((Block)FCBlocks.CRYING_OBSIDIAN_BRICK_SLAB.get(), (x$0) -> {
                return BlockLoot.createSlabItemTable(x$0);
            });
            this.add((Block)FCBlocks.POLISHED_OBSIDIAN_SLAB.get(), (x$0) -> {
                return BlockLoot.createSlabItemTable(x$0);
            });
            this.add((Block)FCBlocks.CRYING_POLISHED_OBSIDIAN_SLAB.get(), (x$0) -> {
                return BlockLoot.createSlabItemTable(x$0);
            });
        }

        public Iterable<Block> getKnownBlocks() {
            return (Iterable) ForgeRegistries.BLOCKS.getValues().stream().filter((block) -> {
                return ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals("frame_changer");
            }).collect(Collectors.toSet());
        }
    }

}
