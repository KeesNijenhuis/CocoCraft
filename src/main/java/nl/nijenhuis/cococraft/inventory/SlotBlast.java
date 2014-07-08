package nl.nijenhuis.cococraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBlast extends Slot {
    /**
     * The player that is using the GUI where this slot resides.
     */
    private EntityPlayer thePlayer;
    private int field_75228_b;
    private static final String __OBFID = "CL_00001749";

    public SlotBlast(EntityPlayer p_i1813_1_, IInventory p_i1813_2_, int p_i1813_3_, int p_i1813_4_, int p_i1813_5_) {
        super(p_i1813_2_, p_i1813_3_, p_i1813_4_, p_i1813_5_);
        this.thePlayer = p_i1813_1_;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack p_75214_1_) {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int p_75209_1_) {
        if (this.getHasStack()) {
            this.field_75228_b += Math.min(p_75209_1_, this.getStack().stackSize);
        }

        return super.decrStackSize(p_75209_1_);
    }

    public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
        this.onCrafting(p_82870_2_);
        super.onPickupFromSlot(p_82870_1_, p_82870_2_);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack p_75210_1_, int p_75210_2_) {
        this.field_75228_b += p_75210_2_;
        this.onCrafting(p_75210_1_);
    }


}
