package nl.nijenhuis.cococraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCooker extends Slot {



    public SlotCooker(IInventory inv, int x, int y, int z) {
        super(inv, x, y, z);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }

    int integer;

    @Override
    public ItemStack decrStackSize(int i) {
        if (getHasStack()) {
            this.integer += Math.min(i, getStack().stackSize);
        }

        return super.decrStackSize(i);
    }

    public void onPickUpFromSlot(ItemStack itemstack, EntityPlayer player) {
        this.onCrafting(itemstack);
        super.onPickupFromSlot(player, itemstack);
    }

    @Override
    protected void onCrafting(ItemStack itemstack, int i) {
        this.integer += i;
        this.onCrafting(itemstack);
    }
}
