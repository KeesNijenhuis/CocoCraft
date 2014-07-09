package nl.nijenhuis.cococraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import nl.nijenhuis.cococraft.blocks.CocoCraftBlocks;
import nl.nijenhuis.cococraft.handler.BlockDropEvent;
import nl.nijenhuis.cococraft.handler.ConfigurationHandler;
import nl.nijenhuis.cococraft.handler.MachineRecipeHandler;
import nl.nijenhuis.cococraft.handler.MyGuiHandler;
import nl.nijenhuis.cococraft.handler.recipes.RecipeRemover;
import nl.nijenhuis.cococraft.items.CocoCraftItems;
import nl.nijenhuis.cococraft.proxy.IProxy;
import nl.nijenhuis.cococraft.reference.Reference;
import nl.nijenhuis.cococraft.tileentity.TileEntityBlast;
import nl.nijenhuis.cococraft.utility.LogHelper;
import nl.nijenhuis.cococraft.world.CocoCraftWorldGenerator;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class CocoCraft {

    @Mod.Instance(Reference.MOD_NAME)
    public static CocoCraft instance;

    public static MyGuiHandler guiHandler = new MyGuiHandler();

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        CocoCraftBlocks.init();
        CocoCraftItems.init();
        MachineRecipeHandler.init();


        GameRegistry.registerTileEntity(TileEntityBlast.class, "blastFurnace");

        RecipeRemover.removeRecipes(new ItemStack(Blocks.furnace), "blockFurnace");

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        MinecraftForge.EVENT_BUS.register(new BlockDropEvent());

        GameRegistry.registerWorldGenerator(new CocoCraftWorldGenerator(), 0);

        LogHelper.info("Pre Initialization Complete");

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);


        LogHelper.info("Initialization Complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        LogHelper.info("Post Initialization Complete");
    }

    public static CreativeTabs tabCocoCraft = new CreativeTabs("tabCocoCraft") {
        @Override
        public Item getTabIconItem() {
            return CocoCraftItems.dustCoco;
        }
    };
}
