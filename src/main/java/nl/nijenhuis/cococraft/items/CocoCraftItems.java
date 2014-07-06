package nl.nijenhuis.cococraft.items;

import net.minecraft.item.Item;
import nl.nijenhuis.cococraft.reference.Reference;
import nl.nijenhuis.cococraft.utility.RegisterHelper;

public class CocoCraftItems {

    public static Item grindedCoco, grindedMithril, grindedSilver, grindedAdamant, grindedRunite, grindedIron, grindedGold;

    public static void init() {

        grindedCoco = new ModItem().setUnlocalizedName("grindedCoco").setTextureName(Reference.RESOURCE + "grindedCoco");
        grindedMithril = new ModItem().setUnlocalizedName("grindedMithril").setTextureName(Reference.RESOURCE + "grindedMithril");
        grindedSilver = new ModItem().setUnlocalizedName("grindedSilver").setTextureName(Reference.RESOURCE + "grindedSilver");
        grindedAdamant = new ModItem().setUnlocalizedName("grindedAdamant").setTextureName(Reference.RESOURCE + "grindedAdamant");
        grindedRunite = new ModItem().setUnlocalizedName("grindedRunite").setTextureName(Reference.RESOURCE + "grindedRunite");
        grindedIron = new ModItem().setUnlocalizedName("grindedIron").setTextureName(Reference.RESOURCE + "grindedIron");
        grindedGold = new ModItem().setUnlocalizedName("grindedGold").setTextureName(Reference.RESOURCE + "grindedGold");


        registerItems();
    }

    public static void registerItems() {

        RegisterHelper.registerItem(grindedCoco);
        RegisterHelper.registerItem(grindedMithril);
        RegisterHelper.registerItem(grindedSilver);
        RegisterHelper.registerItem(grindedAdamant);
        RegisterHelper.registerItem(grindedRunite);
        RegisterHelper.registerItem(grindedIron);
        RegisterHelper.registerItem(grindedGold);


    }
}
