package nl.nijenhuis.cococraft.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nl.nijenhuis.cococraft.blocks.CocoCraftBlocks;
import nl.nijenhuis.cococraft.handler.recipes.BlastRecipes;
import nl.nijenhuis.cococraft.handler.recipes.CrusherRecipes;
import nl.nijenhuis.cococraft.items.CocoCraftItems;

public class MachineRecipeHandler {

    public static void init() {
        blastFurnaceRecipes();
        crusherRecipes();
    }

    private static void blastFurnaceRecipes() {
        if (ConfigurationHandler.hardRecipes) {
            addSmelting(CocoCraftItems.grindedCoco, new ItemStack(CocoCraftItems.nuggetCoco));
            addSmelting(CocoCraftItems.grindedMithril, new ItemStack(CocoCraftItems.nuggetMithril));
            addSmelting(CocoCraftItems.grindedSilver, new ItemStack(CocoCraftItems.nuggetSilver));
            addSmelting(CocoCraftItems.grindedAdamant, new ItemStack(CocoCraftItems.nuggetAdamant));
            addSmelting(CocoCraftItems.grindedRunite, new ItemStack(CocoCraftItems.nuggetRunite));
            addSmelting(CocoCraftItems.grindedGold, new ItemStack(Items.gold_nugget));
            addSmelting(CocoCraftItems.grindedIron, new ItemStack(CocoCraftItems.nuggetIron));

            addSmelting(CocoCraftItems.dustCoco, new ItemStack(CocoCraftItems.nuggetCoco));
            addSmelting(CocoCraftItems.dustMithril, new ItemStack(CocoCraftItems.nuggetMithril));
            addSmelting(CocoCraftItems.dustSilver, new ItemStack(CocoCraftItems.nuggetSilver));
            addSmelting(CocoCraftItems.dustAdamant, new ItemStack(CocoCraftItems.nuggetAdamant));
            addSmelting(CocoCraftItems.dustRunite, new ItemStack(CocoCraftItems.nuggetRunite));
            addSmelting(CocoCraftItems.dustGold, new ItemStack(Items.gold_nugget));
            addSmelting(CocoCraftItems.dustIron, new ItemStack(CocoCraftItems.nuggetIron));
        } else {
            addSmelting(CocoCraftItems.grindedCoco, new ItemStack(CocoCraftItems.ingotCoco, 1));
            addSmelting(CocoCraftItems.grindedMithril, new ItemStack(CocoCraftItems.ingotMithril, 1));
            addSmelting(CocoCraftItems.grindedSilver, new ItemStack(CocoCraftItems.ingotSilver, 1));
            addSmelting(CocoCraftItems.grindedAdamant, new ItemStack(CocoCraftItems.ingotAdamant, 1));
            addSmelting(CocoCraftItems.grindedRunite, new ItemStack(CocoCraftItems.ingotRunite, 1));

            addSmelting(CocoCraftItems.dustCoco, new ItemStack(CocoCraftItems.ingotCoco, 2));
            addSmelting(CocoCraftItems.dustMithril, new ItemStack(CocoCraftItems.ingotMithril, 2));
            addSmelting(CocoCraftItems.dustSilver, new ItemStack(CocoCraftItems.ingotSilver, 2));
            addSmelting(CocoCraftItems.dustAdamant, new ItemStack(CocoCraftItems.ingotAdamant, 2));
            addSmelting(CocoCraftItems.dustRunite, new ItemStack(CocoCraftItems.ingotRunite, 2));

            addSmelting(CocoCraftBlocks.oreCoco, new ItemStack(CocoCraftItems.ingotCoco, 1));
            addSmelting(CocoCraftBlocks.oreMithril, new ItemStack(CocoCraftItems.ingotMithril, 1));
            addSmelting(CocoCraftBlocks.oreSilver, new ItemStack(CocoCraftItems.ingotSilver, 1));
            addSmelting(CocoCraftBlocks.oreAdamant, new ItemStack(CocoCraftItems.ingotAdamant, 1));
            addSmelting(CocoCraftBlocks.oreRunite, new ItemStack(CocoCraftItems.ingotRunite, 1));
        }

    }

    private static void crusherRecipes() {
        if(ConfigurationHandler.hardRecipes) {
            addCrushing(CocoCraftItems.grindedCoco, new ItemStack(CocoCraftItems.dustCoco, 2));
            addCrushing(CocoCraftItems.grindedMithril, new ItemStack(CocoCraftItems.dustMithril, 2));
            addCrushing(CocoCraftItems.grindedSilver, new ItemStack(CocoCraftItems.dustSilver, 2));
            addCrushing(CocoCraftItems.grindedAdamant, new ItemStack(CocoCraftItems.dustAdamant, 2));
            addCrushing(CocoCraftItems.grindedRunite, new ItemStack(CocoCraftItems.dustRunite, 2));
            addCrushing(CocoCraftItems.grindedGold, new ItemStack(CocoCraftItems.dustGold, 2));
            addCrushing(CocoCraftItems.grindedIron, new ItemStack(CocoCraftItems.dustIron, 2));
        }
        else {
            addCrushing(CocoCraftBlocks.oreCoco, new ItemStack(CocoCraftItems.dustCoco, 2));
            addCrushing(CocoCraftBlocks.oreMithril, new ItemStack(CocoCraftItems.dustMithril, 2));
            addCrushing(CocoCraftBlocks.oreSilver, new ItemStack(CocoCraftItems.dustSilver, 2));
            addCrushing(CocoCraftBlocks.oreAdamant, new ItemStack(CocoCraftItems.dustAdamant, 2));
            addCrushing(CocoCraftBlocks.oreRunite, new ItemStack(CocoCraftItems.dustRunite, 2));
            addCrushing(Blocks.gold_ore, new ItemStack(CocoCraftItems.dustGold, 2));
            addCrushing(Blocks.iron_ore, new ItemStack(CocoCraftItems.dustIron, 2));
        }
    }

    private static void addSmelting(Block input, ItemStack output) {
        BlastRecipes.smelting().addSmelting(input, output);
    }

    private static void addSmelting(Item input, ItemStack output) {
        BlastRecipes.smelting().addSmelting(input, output);
    }

    private static void addSmelting(ItemStack input, ItemStack output) {
        BlastRecipes.smelting().addSmelting(input, output);
    }

    private static void addCrushing(Block input, ItemStack output) {
        CrusherRecipes.smelting().addSmelting(input, output);
    }

    private static void addCrushing(Item input, ItemStack output) {
        CrusherRecipes.smelting().addSmelting(input, output);
    }

    private static void addCrushing(ItemStack input, ItemStack output) {
        CrusherRecipes.smelting().addSmelting(input, output);
    }
}
