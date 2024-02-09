package com.davigj.frame_changer.core.data.server;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class FCRecipeProvider extends RecipeProvider {
    public FCRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }



    public static void stonecutterResultFromBase(@NotNull Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike input) {
        stonecutterResultFromBase(consumer, output, input, 1);
    }
}
