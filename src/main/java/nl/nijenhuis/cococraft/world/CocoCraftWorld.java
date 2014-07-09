package nl.nijenhuis.cococraft.world;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class CocoCraftWorld {

    public static void mainRegistry() {
        initWorldGen();
    }

    public static void initWorldGen() {
        registerWorldGen(new CocoCraftWorldGenerator(), 1);
    }

    public static void registerWorldGen(IWorldGenerator iWorldGenerator, int i) {
        GameRegistry.registerWorldGenerator(iWorldGenerator, i);
    }

}
