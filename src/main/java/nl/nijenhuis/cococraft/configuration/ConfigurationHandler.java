package nl.nijenhuis.cococraft.configuration;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {

    public static void init(File configFile) {

        //create config file
        Configuration configuration = new Configuration(configFile);

        try {
            //load config file
            configuration.load();
            //add properties
            boolean configValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "A Value! Quick throw a PokéBall").getBoolean(true);

        } catch (Exception e) {
            //Log exception

        } finally {
            //save config file
            configuration.save();
        }
    }
}
