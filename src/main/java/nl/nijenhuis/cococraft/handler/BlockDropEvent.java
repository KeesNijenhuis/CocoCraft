package nl.nijenhuis.cococraft.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import nl.nijenhuis.cococraft.blocks.CocoCraftBlocks;
import nl.nijenhuis.cococraft.items.CocoCraftItems;

public class BlockDropEvent {

    @SubscribeEvent
    public void onBlockHarvest(BlockEvent.HarvestDropsEvent event) {
        if (ConfigurationHandler.customOreDrops) {
            if (event.block == CocoCraftBlocks.oreCoco) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftItems.grindedCoco));
            }
            if (event.block == CocoCraftBlocks.oreMithril) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftItems.grindedMithril));
            }
            if (event.block == CocoCraftBlocks.oreSilver) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftItems.grindedSilver));
            }
            if (event.block == CocoCraftBlocks.oreAdamant) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftItems.grindedAdamant));
            }
            if (event.block == CocoCraftBlocks.oreRunite) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftItems.grindedRunite));
            }
            if (event.block == Blocks.iron_ore) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftItems.grindedIron));
            }
            if (event.block == Blocks.gold_ore) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftItems.grindedGold));
            }
        } else {
            if (event.block == CocoCraftBlocks.oreCoco) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftBlocks.oreCoco));
            }
            if (event.block == CocoCraftBlocks.oreMithril) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftBlocks.oreMithril));
            }
            if (event.block == CocoCraftBlocks.oreSilver) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftBlocks.oreSilver));
            }
            if (event.block == CocoCraftBlocks.oreAdamant) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftBlocks.oreAdamant));
            }
            if (event.block == CocoCraftBlocks.oreRunite) {
                event.drops.clear();
                event.drops.add(new ItemStack(CocoCraftBlocks.oreRunite));
            }
        }
    }


}
