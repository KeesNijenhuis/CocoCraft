package nl.nijenhuis.cococraft.utility;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import nl.nijenhuis.cococraft.reference.Reference;

public class RegisterHelper {

    public static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, Reference.MOD_ID + "_" + block.getUnlocalizedName().substring(5));
    }

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlock) {
        GameRegistry.registerBlock(block, itemBlock, Reference.MOD_ID + "_" + block.getUnlocalizedName().replace("tile.", ""));
    }

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlock, Object... constructorArgs) {
        GameRegistry.registerBlock(block,itemBlock, block.getUnlocalizedName().replace("tile.", ""), null, constructorArgs);
    }

    public static void registerItem(Item item) {
        GameRegistry.registerItem(item, Reference.MOD_ID + "_" + item.getUnlocalizedName().substring(5));
    }
}
