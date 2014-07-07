package nl.nijenhuis.cococraft.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import nl.nijenhuis.cococraft.blocks.CocoCraftBlocks;
import nl.nijenhuis.cococraft.items.CocoCraftItems;

public class SmeltingHandler {

    public static void recipes() {

        if(ConfigurationHandler.hardRecipes == true) {
            GameRegistry.addSmelting(CocoCraftItems.grindedCoco, new ItemStack(CocoCraftItems.nuggetCoco, 1), 0.1F);
        } else {
            GameRegistry.addSmelting(CocoCraftItems.grindedCoco, new ItemStack(CocoCraftItems.ingotCoco, 1), 0.1F);
        }
    }
}
