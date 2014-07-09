package nl.nijenhuis.cococraft.items;

import net.minecraft.item.Item;
import nl.nijenhuis.cococraft.reference.Reference;
import nl.nijenhuis.cococraft.utility.RegisterHelper;

public class CocoCraftItems {

    public static Item grindedCoco, grindedMithril, grindedSilver, grindedAdamant, grindedRunite, grindedIron, grindedGold;
    public static Item nuggetCoco, nuggetMithril, nuggetSilver, nuggetAdamant, nuggetRunite, nuggetIron;
    public static Item ingotCoco, ingotMithril, ingotSilver, ingotAdamant, ingotRunite;
    public static Item dustCoco, dustMithril, dustSilver, dustAdamant, dustRunite, dustIron, dustGold;

    public static void init() {

        grindedCoco = new ModItem().setUnlocalizedName("grindedCoco").setTextureName(Reference.RESOURCE + "grindedCoco");
        grindedMithril = new ModItem().setUnlocalizedName("grindedMithril").setTextureName(Reference.RESOURCE + "grindedMithril");
        grindedSilver = new ModItem().setUnlocalizedName("grindedSilver").setTextureName(Reference.RESOURCE + "grindedSilver");
        grindedAdamant = new ModItem().setUnlocalizedName("grindedAdamant").setTextureName(Reference.RESOURCE + "grindedAdamant");
        grindedRunite = new ModItem().setUnlocalizedName("grindedRunite").setTextureName(Reference.RESOURCE + "grindedRunite");
        grindedIron = new ModItem().setUnlocalizedName("grindedIron").setTextureName(Reference.RESOURCE + "grindedIron");
        grindedGold = new ModItem().setUnlocalizedName("grindedGold").setTextureName(Reference.RESOURCE + "grindedGold");

        nuggetCoco = new ModItem().setUnlocalizedName("nuggetCoco").setTextureName(Reference.RESOURCE + "nuggetCoco");
        nuggetMithril = new ModItem().setUnlocalizedName("nuggetMithril").setTextureName(Reference.RESOURCE + "nuggetMithril");
        nuggetSilver = new ModItem().setUnlocalizedName("nuggetSilver").setTextureName(Reference.RESOURCE + "nuggetSilver");
        nuggetAdamant = new ModItem().setUnlocalizedName("nuggetAdamant").setTextureName(Reference.RESOURCE + "nuggetAdamant");
        nuggetRunite = new ModItem().setUnlocalizedName("nuggetRunite").setTextureName(Reference.RESOURCE + "nuggetRunite");
        nuggetIron = new ModItem().setUnlocalizedName("nuggetIron").setTextureName(Reference.RESOURCE + "nuggetIron");

        ingotCoco = new ModItem().setUnlocalizedName("ingotCoco").setTextureName(Reference.RESOURCE + "ingotCoco");
        ingotMithril = new ModItem().setUnlocalizedName("ingotMithril").setTextureName(Reference.RESOURCE + "ingotMithril");
        ingotSilver = new ModItem().setUnlocalizedName("ingotSilver").setTextureName(Reference.RESOURCE + "ingotSilver");
        ingotAdamant = new ModItem().setUnlocalizedName("ingotAdamant").setTextureName(Reference.RESOURCE + "ingotAdamant");
        ingotRunite = new ModItem().setUnlocalizedName("ingotRunite").setTextureName(Reference.RESOURCE + "ingotRunite");

        dustCoco = new ModItem().setUnlocalizedName("dustCoco").setTextureName(Reference.RESOURCE + "dustCoco");
        dustMithril = new ModItem().setUnlocalizedName("dustMithril").setTextureName(Reference.RESOURCE + "dustMithril");
        dustSilver = new ModItem().setUnlocalizedName("dustSilver").setTextureName(Reference.RESOURCE + "dustSilver");
        dustAdamant = new ModItem().setUnlocalizedName("dustAdamant").setTextureName(Reference.RESOURCE + "dustAdamant");
        dustRunite = new ModItem().setUnlocalizedName("dustRunite").setTextureName(Reference.RESOURCE + "dustRunite");
        dustIron = new ModItem().setUnlocalizedName("dustIron").setTextureName(Reference.RESOURCE + "dustIron");
        dustGold = new ModItem().setUnlocalizedName("dustGold").setTextureName(Reference.RESOURCE + "dustGold");


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

        RegisterHelper.registerItem(nuggetCoco);
        RegisterHelper.registerItem(nuggetMithril);
        RegisterHelper.registerItem(nuggetSilver);
        RegisterHelper.registerItem(nuggetAdamant);
        RegisterHelper.registerItem(nuggetRunite);
        RegisterHelper.registerItem(nuggetIron);

        RegisterHelper.registerItem(ingotCoco);
        RegisterHelper.registerItem(ingotMithril);
        RegisterHelper.registerItem(ingotSilver);
        RegisterHelper.registerItem(ingotAdamant);
        RegisterHelper.registerItem(ingotRunite);

        RegisterHelper.registerItem(dustCoco);
        RegisterHelper.registerItem(dustMithril);
        RegisterHelper.registerItem(dustSilver);
        RegisterHelper.registerItem(dustAdamant);
        RegisterHelper.registerItem(dustRunite);
        RegisterHelper.registerItem(dustIron);
        RegisterHelper.registerItem(dustGold);


    }
}
