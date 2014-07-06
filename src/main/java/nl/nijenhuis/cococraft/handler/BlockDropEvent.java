package nl.nijenhuis.cococraft.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import nl.nijenhuis.cococraft.blocks.CocoCraftBlocks;

public class BlockDropEvent {

    @SubscribeEvent
    //TODO change all ore drops to CC item.
    public void onBlockHarvest(BlockEvent.HarvestDropsEvent event) {
        if(event.block == CocoCraftBlocks.oreCoco) {
            event.drops.clear(); // removes the drop from iron ore.
            event.drops.add(new ItemStack(Items.diamond_axe)); // adds a new itemstack for the drop
        }
    }
}
