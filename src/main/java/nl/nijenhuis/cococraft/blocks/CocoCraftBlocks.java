package nl.nijenhuis.cococraft.blocks;

import net.minecraft.block.Block;
import nl.nijenhuis.cococraft.reference.Reference;
import nl.nijenhuis.cococraft.utility.RegisterHelper;

public class CocoCraftBlocks {

    public static Block oreCoco, oreMithril, oreSilver, oreAdamant, oreRunite;
    public static Block storageCoco, storageMithril, storageSilver, storageAdamant, storageRunite;
    public static Block blockBlastFurnace, blockCrusher;

    public static void init() {

        oreCoco = new BlockOre().setBlockName("oreCoco").setBlockTextureName(Reference.RESOURCE + "oreCoco");
        oreMithril = new BlockOre().setBlockName("oreMithril").setBlockTextureName(Reference.RESOURCE + "oreMithril");
        oreSilver = new BlockOre().setBlockName("oreSilver").setBlockTextureName(Reference.RESOURCE + "oreSilver");
        oreAdamant = new BlockOre().setBlockName("oreAdamant").setBlockTextureName(Reference.RESOURCE + "oreAdamant");
        oreRunite = new BlockOre().setBlockName("oreRunite").setBlockTextureName(Reference.RESOURCE + "oreRunite");

        storageCoco = new BlockOre().setBlockName("storageCoco").setBlockTextureName(Reference.RESOURCE + "storageCoco");
        storageMithril = new BlockOre().setBlockName("storageMithril").setBlockTextureName(Reference.RESOURCE + "storageMithril");
        storageSilver = new BlockOre().setBlockName("storageSilver").setBlockTextureName(Reference.RESOURCE + "storageSilver");
        storageAdamant = new BlockOre().setBlockName("storageAdamant").setBlockTextureName(Reference.RESOURCE + "storageAdamant");
        storageRunite = new BlockOre().setBlockName("storageRunite").setBlockTextureName(Reference.RESOURCE + "storageRunite");


        blockBlastFurnace = new BlockBlast(false).setBlockName("blockBlastFurnace");
        blockCrusher = new BlockCrusher(false).setBlockName("blockCrusher");


        registerBlocks();
    }

    public static void registerBlocks() {

        RegisterHelper.registerBlock(oreCoco);
        RegisterHelper.registerBlock(oreMithril);
        RegisterHelper.registerBlock(oreSilver);
        RegisterHelper.registerBlock(oreAdamant);
        RegisterHelper.registerBlock(oreRunite);


        RegisterHelper.registerBlock(storageCoco);
        RegisterHelper.registerBlock(storageMithril);
        RegisterHelper.registerBlock(storageSilver);
        RegisterHelper.registerBlock(storageAdamant);
        RegisterHelper.registerBlock(storageRunite);


        RegisterHelper.registerBlock(blockBlastFurnace);
        RegisterHelper.registerBlock(blockCrusher);

    }
}
