package nl.nijenhuis.cococraft.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import nl.nijenhuis.cococraft.blocks.CocoCraftBlocks;
import nl.nijenhuis.cococraft.items.CocoCraftItems;

public class BlockDropEvent {

    @SubscribeEvent
    public void onBlockHarvest(BlockEvent.HarvestDropsEvent event) {
        if(event.block == CocoCraftBlocks.oreCoco) {
            event.drops.clear(); // removes the drop from iron ore.
            event.drops.add(new ItemStack(CocoCraftItems.grindedCoco)); // adds a new itemstack for the drop
        }
        if(event.block == CocoCraftBlocks.oreMithril) {
            event.drops.clear(); // removes the drop from iron ore.
            event.drops.add(new ItemStack(CocoCraftItems.grindedMithril)); // adds a new itemstack for the drop
        }
        if(event.block == CocoCraftBlocks.oreSilver) {
            event.drops.clear(); // removes the drop from iron ore.
            event.drops.add(new ItemStack(CocoCraftItems.grindedSilver)); // adds a new itemstack for the drop
        }
        if(event.block == CocoCraftBlocks.oreAdamant) {
            event.drops.clear(); // removes the drop from iron ore.
            event.drops.add(new ItemStack(CocoCraftItems.grindedAdamant)); // adds a new itemstack for the drop
        }
        if(event.block == CocoCraftBlocks.oreRunite) {
            event.drops.clear(); // removes the drop from iron ore.
            event.drops.add(new ItemStack(CocoCraftItems.grindedRunite)); // adds a new itemstack for the drop
        }
        if(event.block == Blocks.iron_ore) {
            event.drops.clear(); // removes the drop from iron ore.
            event.drops.add(new ItemStack(CocoCraftItems.grindedIron)); // adds a new itemstack for the drop
        }
        if(event.block == Blocks.gold_ore) {
            event.drops.clear(); // removes the drop from iron ore.
            event.drops.add(new ItemStack(CocoCraftItems.grindedGold)); // adds a new itemstack for the drop
        }
    }
}
