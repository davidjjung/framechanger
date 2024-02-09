package com.davigj.frame_changer.core.data.server;

import com.davigj.frame_changer.core.other.FCBlockFamilies;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FCRecipeProvider extends RecipeProvider {
    public FCRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    public void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        generateRecipes(consumer, FCBlockFamilies.OBSIDIAN_BRICKS_FAMILY);
        generateRecipes(consumer, FCBlockFamilies.CRYING_OBSIDIAN_BRICKS_FAMILY);
        generateRecipes(consumer, FCBlockFamilies.POLISHED_OBSIDIAN_FAMILY);
        generateRecipes(consumer, FCBlockFamilies.CRYING_POLISHED_OBSIDIAN_FAMILY);
    }
}
