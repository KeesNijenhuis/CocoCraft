package nl.nijenhuis.cococraft.handler.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import nl.nijenhuis.cococraft.blocks.CocoCraftBlocks;
import nl.nijenhuis.cococraft.handler.ConfigurationHandler;
import nl.nijenhuis.cococraft.items.CocoCraftItems;

public class SmeltingHandler {

    public static void recipes() {

        if(ConfigurationHandler.hardRecipes) {
            GameRegistry.addSmelting(CocoCraftItems.grindedCoco, new ItemStack(CocoCraftItems.nuggetCoco, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftItems.grindedMithril, new ItemStack(CocoCraftItems.nuggetMithril, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftItems.grindedSilver, new ItemStack(CocoCraftItems.nuggetSilver, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftItems.grindedAdamant, new ItemStack(CocoCraftItems.nuggetAdamant, 1), 0.1F);
            GameRegistry.addSmelting(CocoCraftItems.grindedRunite, new ItemStack(CocoCraftItems.nuggetRunite, 1), 0.1F);

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
}
