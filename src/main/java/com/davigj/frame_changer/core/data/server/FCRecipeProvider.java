package com.davigj.frame_changer.core.data.server;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FCRecipeProvider extends RecipeProvider {
    private static final Map<String, Boolean> stonecuttingMap = new HashMap();
    public FCRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    private static void quickStonecuttingRecipe(Consumer<FinishedRecipe> consumer, ItemLike from, ItemLike result) {
        quickStonecuttingRecipe(consumer, result, from, 1);
    }

    private static void quickStonecuttingRecipe(Consumer<FinishedRecipe> consumer, ItemLike from, ItemLike result, int amount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(new ItemLike[]{from}), result, amount).unlockedBy(getHasName(from), has(from)).save(consumer, cuttingName(result, from));
    }

    private static ResourceLocation cuttingName(ItemLike item, ItemLike from) {
        String string = getItemName(item);
        if (stonecuttingMap.put(string, true) != null) {
            string = string + "_from_" + getItemName(from);
        }

        return new ResourceLocation("architects_palette", "stonecutting/" + string);
    }
}
