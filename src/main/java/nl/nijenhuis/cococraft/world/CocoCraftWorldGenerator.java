package nl.nijenhuis.cococraft.world;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import nl.nijenhuis.cococraft.blocks.CocoCraftBlocks;

import java.util.Random;

public class CocoCraftWorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

    }

    private void addOre(Block block, Block blockSpawn, Random rand, World world, int posX, int posZ, int minY, int maxY, int minVeinSize, int maxVeinSize, int spawnChance) {
        for(int i = 0; i < spawnChance; i++) {
            int defaultChunkSize = 16;

            int xPos = posX + rand.nextInt(defaultChunkSize);
            int yPos = minY + rand.nextInt(maxY - minY);
            int zPos = posZ + rand.nextInt(defaultChunkSize);

            new WorldGenMinable(block, (minVeinSize + rand.nextInt(maxVeinSize - minVeinSize)), blockSpawn).generate(world, rand, xPos, yPos, zPos);
        }
    }

    private void generateSurface(Random rand, int chunkX, int chunkZ, World world) {
        addOre(CocoCraftBlocks.oreCoco, Blocks.stone, rand, world, chunkX, chunkZ, 5, 25, 1, 6, 50);
        addOre(CocoCraftBlocks.oreMithril, Blocks.stone, rand, world, chunkX, chunkZ, 29, 40, 3, 6, 50);
        addOre(CocoCraftBlocks.oreSilver, Blocks.stone, rand, world, chunkX, chunkZ, 10, 30, 2, 5, 50);
        addOre(CocoCraftBlocks.oreAdamant, Blocks.stone, rand, world, chunkX, chunkZ, 10, 30, 2, 3, 50);
        addOre(CocoCraftBlocks.oreRunite, Blocks.stone, rand, world, chunkX, chunkZ, 10, 30, 2, 3, 50);

    }

    private void generateEnd(Random rand, int chunkX, int chunkZ, World world) {

    }

    private void generateNether(Random rand, int chunkX, int chunkZ, World world) {

    }
}
