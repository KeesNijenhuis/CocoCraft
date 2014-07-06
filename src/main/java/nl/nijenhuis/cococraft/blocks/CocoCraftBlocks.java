package nl.nijenhuis.cococraft.blocks;

import net.minecraft.block.Block;
import nl.nijenhuis.cococraft.reference.Reference;
import nl.nijenhuis.cococraft.utility.RegisterHelper;

public class CocoCraftBlocks {

    public static Block oreCoco, oreMithril, oreSilver, oreAdamant, oreRunite;

    public static void init() {

        oreCoco = new BlockOre().setBlockName("oreCoco").setBlockTextureName(Reference.RESOURCE + "oreCoco");
        oreMithril = new BlockOre().setBlockName("oreMithril").setBlockTextureName(Reference.RESOURCE + "oreMithril");
        oreSilver = new BlockOre().setBlockName("oreSilver").setBlockTextureName(Reference.RESOURCE + "oreSilver");
        oreAdamant = new BlockOre().setBlockName("oreAdamant").setBlockTextureName(Reference.RESOURCE + "oreAdamant");
        oreRunite = new BlockOre().setBlockName("oreRunite").setBlockTextureName(Reference.RESOURCE + "oreRunite");


        registerBlocks();
    }

    public static void registerBlocks() {

        RegisterHelper.registerBlock(oreCoco);
        RegisterHelper.registerBlock(oreMithril);
        RegisterHelper.registerBlock(oreSilver);
        RegisterHelper.registerBlock(oreAdamant);
        RegisterHelper.registerBlock(oreRunite);
    }
}
