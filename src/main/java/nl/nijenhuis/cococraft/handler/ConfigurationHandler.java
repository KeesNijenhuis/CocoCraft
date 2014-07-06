package nl.nijenhuis.cococraft.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import nl.nijenhuis.cococraft.reference.Reference;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;
    public static boolean testValue = false;

    public static void init(File configFile) {

        //create config file
        if(configuration == null) {
            configuration = new Configuration(configFile);
        }

        }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

        if(event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }

    public void loadConfiguration() {
        testValue = configuration.getBoolean("Config Value", Configuration.CATEGORY_GENERAL, false, "A Value, quick throw a PokéBall!");

        if(configuration.hasChanged()) {
            configuration.save();
        }
    }
}
