package nl.nijenhuis.cococraft.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import nl.nijenhuis.cococraft.reference.Reference;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;
    public static boolean hardRecipes = true;

    public static void init(File configFile) {

        //create config file
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }

    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        hardRecipes = configuration.getBoolean("Hard Recipes", Configuration.CATEGORY_GENERAL, true, "Determines if the mod uses hard recipes for smelting.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
