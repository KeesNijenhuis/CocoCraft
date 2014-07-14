package nl.nijenhuis.cococraft.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nl.nijenhuis.cococraft.blocks.CocoCraftBlocks;
import nl.nijenhuis.cococraft.handler.recipes.BlastRecipes;
import nl.nijenhuis.cococraft.items.CocoCraftItems;

public class MachineRecipeHandler {

    public static void init() {
        blastFurnaceRecipes();
    }

    public static void blastFurnaceRecipes() {
        if (ConfigurationHandler.hardRecipes) {
            addSmelting(CocoCraftItems.grindedCoco, new ItemStack(CocoCraftItems.nuggetCoco));
            addSmelting(CocoCraftItems.grindedMithril, new ItemStack(CocoCraftItems.nuggetMithril));
            addSmelting(CocoCraftItems.grindedSilver, new ItemStack(CocoCraftItems.nuggetSilver));
            addSmelting(CocoCraftItems.grindedAdamant, new ItemStack(CocoCraftItems.nuggetAdamant));
            addSmelting(CocoCraftItems.grindedRunite, new ItemStack(CocoCraftItems.nuggetRunite));
            addSmelting(CocoCraftItems.grindedGold, new ItemStack(Items.gold_nugget));
            addSmelting(CocoCraftItems.grindedIron, new ItemStack(CocoCraftItems.nuggetIron));
        } else {
            GameRegistry.addSmelting(CocoCraftItems.grindedCoco, new ItemStack(CocoCraftItems.ingotCoco, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftItems.grindedMithril, new ItemStack(CocoCraftItems.ingotMithril, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftItems.grindedSilver, new ItemStack(CocoCraftItems.ingotSilver, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftItems.grindedAdamant, new ItemStack(CocoCraftItems.ingotAdamant, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftItems.grindedRunite, new ItemStack(CocoCraftItems.ingotRunite, 1), 0.1F);

            GameRegistry.addSmelting(CocoCraftBlocks.oreCoco, new ItemStack(CocoCraftItems.ingotCoco, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftBlocks.oreMithril, new ItemStack(CocoCraftItems.ingotMithril, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftBlocks.oreSilver, new ItemStack(CocoCraftItems.ingotSilver, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftBlocks.oreAdamant, new ItemStack(CocoCraftItems.ingotAdamant, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftBlocks.oreRunite, new ItemStack(CocoCraftItems.ingotRunite, 1), 0.1F);
        }

    }

    public static void addSmelting(Block input, ItemStack output) {
        BlastRecipes.smelting().addSmelting(input, output);
    }

    public static void addSmelting(Item input, ItemStack output) {
        BlastRecipes.smelting().addSmelting(input, output);
    }

    public static void addSmelting(ItemStack input, ItemStack output) {
        BlastRecipes.smelting().addSmelting(input, output);
    }
}
