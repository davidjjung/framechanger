package com.davigj.frame_changer.core.data.server;

import com.davigj.frame_changer.core.other.FCBlockFamilies;
import com.davigj.frame_changer.core.registry.FCBlocks;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

import static com.davigj.frame_changer.core.FrameChanger.MOD_ID;

public class FCRecipeProvider extends BlueprintRecipeProvider {
    public FCRecipeProvider(PackOutput result, CompletableFuture<HolderLookup.Provider> provider) {
        super(MOD_ID, result, provider);
    }

    protected void buildRecipes(RecipeOutput consumer, HolderLookup.Provider holderLookup) {
        generateRecipes(consumer, FCBlockFamilies.OBSIDIAN_BRICKS_FAMILY, FeatureFlags.DEFAULT_FLAGS);
        generateRecipes(consumer, FCBlockFamilies.CRYING_OBSIDIAN_BRICKS_FAMILY, FeatureFlags.DEFAULT_FLAGS);
        generateRecipes(consumer, FCBlockFamilies.POLISHED_OBSIDIAN_FAMILY, FeatureFlags.DEFAULT_FLAGS);
        generateRecipes(consumer, FCBlockFamilies.CRYING_POLISHED_OBSIDIAN_FAMILY, FeatureFlags.DEFAULT_FLAGS);

        stoneCutSlabStairsWall(consumer, FCBlocks.OBSIDIAN_BRICKS.get(), FCBlocks.OBSIDIAN_BRICK_SLAB.get(),
                FCBlocks.OBSIDIAN_BRICK_STAIRS.get(), FCBlocks.OBSIDIAN_BRICK_WALL.get());
        stoneCutSlabStairsWall(consumer, FCBlocks.CRYING_OBSIDIAN_BRICKS.get(), FCBlocks.CRYING_OBSIDIAN_BRICK_SLAB.get(),
                FCBlocks.CRYING_OBSIDIAN_BRICK_STAIRS.get(), FCBlocks.CRYING_OBSIDIAN_BRICK_WALL.get());
        stoneCutSlabStairsWall(consumer, FCBlocks.POLISHED_OBSIDIAN.get(), FCBlocks.POLISHED_OBSIDIAN_SLAB.get(),
                FCBlocks.POLISHED_OBSIDIAN_STAIRS.get(), FCBlocks.POLISHED_OBSIDIAN_WALL.get());
        stoneCutSlabStairsWall(consumer, FCBlocks.CRYING_POLISHED_OBSIDIAN.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN_SLAB.get(),
                FCBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN_WALL.get());

        stonecutterResultFromBase(consumer, FCBlocks.POLISHED_OBSIDIAN.get(), Blocks.OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.CHISELED_OBSIDIAN.get(), Blocks.OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.CHISELED_OBSIDIAN.get(), FCBlocks.POLISHED_OBSIDIAN.get());
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_PILLAR.get(), Blocks.OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_PILLAR.get(), FCBlocks.OBSIDIAN_BRICKS.get());
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_BRICKS.get(), Blocks.OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_BRICKS.get(), FCBlocks.POLISHED_OBSIDIAN.get());

        // Turning obsidian into bricks/polished SSW
        stonecutterResultFromBase(consumer, FCBlocks.POLISHED_OBSIDIAN_SLAB.get(), Blocks.OBSIDIAN, 2);
        stonecutterResultFromBase(consumer, FCBlocks.POLISHED_OBSIDIAN_STAIRS.get(), Blocks.OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.POLISHED_OBSIDIAN_WALL.get(), Blocks.OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_BRICK_SLAB.get(), Blocks.OBSIDIAN, 2);
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_BRICK_STAIRS.get(), Blocks.OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_BRICK_WALL.get(), Blocks.OBSIDIAN);
        // Turning polished obsidian into brick SSW
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_BRICK_SLAB.get(), FCBlocks.POLISHED_OBSIDIAN.get(), 2);
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_BRICK_STAIRS.get(), FCBlocks.POLISHED_OBSIDIAN.get());
        stonecutterResultFromBase(consumer, FCBlocks.OBSIDIAN_BRICK_WALL.get(), FCBlocks.POLISHED_OBSIDIAN.get());

        stonecutterResultFromBase(consumer, FCBlocks.CRYING_POLISHED_OBSIDIAN.get(), Blocks.CRYING_OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_CHISELED_OBSIDIAN.get(), Blocks.CRYING_OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_CHISELED_OBSIDIAN.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN.get());
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_PILLAR.get(), Blocks.CRYING_OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_PILLAR.get(), FCBlocks.CRYING_OBSIDIAN_BRICKS.get());
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_BRICKS.get(), Blocks.CRYING_OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_BRICKS.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN.get());

        stonecutterResultFromBase(consumer, FCBlocks.CRYING_POLISHED_OBSIDIAN_SLAB.get(), Blocks.CRYING_OBSIDIAN, 2);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS.get(), Blocks.CRYING_OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_POLISHED_OBSIDIAN_WALL.get(), Blocks.CRYING_OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_BRICK_SLAB.get(), Blocks.CRYING_OBSIDIAN, 2);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_BRICK_STAIRS.get(), Blocks.CRYING_OBSIDIAN);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_BRICK_WALL.get(), Blocks.CRYING_OBSIDIAN);

        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_BRICK_SLAB.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN.get(), 2);
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_BRICK_STAIRS.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN.get());
        stonecutterResultFromBase(consumer, FCBlocks.CRYING_OBSIDIAN_BRICK_WALL.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN.get());
        /*
        ShapedRecipeBuilder.shaped(FCBlocks.OBSIDIAN_PILLAR.get(), 2).define(
                '#', Blocks.OBSIDIAN).pattern("#").pattern("#")
                .unlockedBy("has_obsidian_block", has(Blocks.OBSIDIAN)).save(consumer);
        ShapedRecipeBuilder.shaped(FCBlocks.CRYING_OBSIDIAN_PILLAR.get(), 2).define(
                        '#', Blocks.CRYING_OBSIDIAN).pattern("#").pattern("#")
                .unlockedBy("has_crying_obsidian_block", has(Blocks.CRYING_OBSIDIAN)).save(consumer);

        ShapedRecipeBuilder.shaped(FCBlocks.POLISHED_OBSIDIAN.get(), 4).define('#', Blocks.OBSIDIAN)
                .pattern("##").pattern("##")
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN)).save(consumer);
        ShapedRecipeBuilder.shaped(FCBlocks.CRYING_POLISHED_OBSIDIAN.get(), 4).define('#', Blocks.CRYING_OBSIDIAN)
                .pattern("##").pattern("##")
                .unlockedBy("has_crying_obsidian", has(Blocks.CRYING_OBSIDIAN)).save(consumer);

        ShapedRecipeBuilder.shaped(FCBlocks.OBSIDIAN_BRICKS.get(), 4).define('#', FCBlocks.POLISHED_OBSIDIAN.get())
                .pattern("##").pattern("##")
                .unlockedBy("has_polished_obsidian", has(FCBlocks.POLISHED_OBSIDIAN.get())).save(consumer);
        ShapedRecipeBuilder.shaped(FCBlocks.CRYING_OBSIDIAN_BRICKS.get(), 4).define('#', FCBlocks.CRYING_POLISHED_OBSIDIAN.get())
                .pattern("##").pattern("##")
                .unlockedBy("has_crying_polished_obsidian", has(FCBlocks.CRYING_POLISHED_OBSIDIAN.get())).save(consumer);
         */
        }

    public static void stoneCutSlabStairsWall(RecipeOutput consumer, Block parent, Block slab, Block stairs, Block wall) {
        stonecutterResultFromBase(consumer, (ItemLike)slab, (ItemLike)parent, 2);
        stonecutterResultFromBase(consumer, (ItemLike)stairs, (ItemLike)parent);
        stonecutterResultFromBase(consumer, (ItemLike)wall, (ItemLike)parent);
    }

    public static void stonecutterResultFromBase(RecipeOutput consumer, ItemLike output, ItemLike input) {
        stonecutterResultFromBase(consumer, output, input, 1);
    }

    public static void stonecutterResultFromBase(RecipeOutput consumer, ItemLike output, ItemLike input, int count) {
//        SingleItemRecipeBuilder var10000 = SingleItemRecipeBuilder.stonecutting(Ingredient.of(new ItemLike[]{input}), output, count).unlockedBy(getHasName(input), has(input));
//        ResourceLocation var10002 = getModConversionRecipeName(output, input);
//        var10000.save(consumer, "" + var10002 + "_stonecutting");
    }

    public ResourceLocation getModConversionRecipeName(ItemLike output, ItemLike input) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, getConversionRecipeName(output, input));
    }
}
