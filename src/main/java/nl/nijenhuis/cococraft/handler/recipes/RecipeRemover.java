package nl.nijenhuis.cococraft.handler.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import nl.nijenhuis.cococraft.utility.LogHelper;

import java.util.ArrayList;

public class RecipeRemover {

    public static void removeRecipes(ItemStack resultItem, Object object) {

        ItemStack recipeResult = null;

        ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();

        for(int scan = 0; scan < recipes.size(); scan++) {
            IRecipe irecipe = (IRecipe) recipes.get(scan);
            recipeResult = irecipe.getRecipeOutput();

            if(recipeResult != null) {
                if(recipeResult.getItem() == resultItem.getItem() && recipeResult.getItemDamage() == resultItem.getItemDamage()) {
                    LogHelper.info("Removed Recipe: " + object);
                    recipes.remove(scan);
                    scan--;
                }
            }
        }

    }
}
