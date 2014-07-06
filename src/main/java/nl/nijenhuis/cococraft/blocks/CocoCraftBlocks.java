package nl.nijenhuis.cococraft.blocks;

import net.minecraft.block.Block;
import nl.nijenhuis.cococraft.reference.Reference;
import nl.nijenhuis.cococraft.utility.RegisterHelper;

public class CocoCraftBlocks {

    public static Block oreCoco, oreMithril, oreSilver, oreAdamant, oreRunite;

    public static void init() {

        oreCoco = new BlockOre().setBlockName("oreCoco").setBlockTextureName(Reference.RESOURCE + "oreCoco");

        registerBlocks();
    }

    public static void registerBlocks() {

        RegisterHelper.registerBlock(oreCoco);
    }
}
