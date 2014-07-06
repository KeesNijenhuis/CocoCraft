package nl.nijenhuis.cococraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nl.nijenhuis.cococraft.proxy.IProxy;

@Mod(modid = "CocoCraft", name = "CocoCraft", version = "1.7.10-1.0")
public class CocoCraft {

    @Mod.Instance("CocoCraft")
    public static CocoCraft instance;

    @SidedProxy(clientSide = "nl.nijenhuis.cococraft.ClientProxy", serverSide = "nl.nijenhuis.cococraft.ServerProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
