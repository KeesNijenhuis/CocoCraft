package nl.nijenhuis.cococraft.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import nl.nijenhuis.cococraft.handler.recipes.BlastRecipes;
import nl.nijenhuis.cococraft.tileentity.TileEntityBlast;

public class ContainerBlast extends Container {
    private TileEntityBlast tileBlast;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerBlast(InventoryPlayer invPlayer, TileEntityBlast tileEntityBlast) {
        this.tileBlast = tileEntityBlast;
        this.addSlotToContainer(new Slot(tileEntityBlast, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileEntityBlast, 1, 56, 53));
        this.addSlotToContainer(new SlotBlast(invPlayer.player, tileEntityBlast, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileBlast.blastCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileBlast.blastBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileBlast.currentItemBurnTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.tileBlast.blastCookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileBlast.blastCookTime);
            }

            if (this.lastBurnTime != this.tileBlast.blastBurnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileBlast.blastBurnTime);
            }

            if (this.lastItemBurnTime != this.tileBlast.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.tileBlast.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.tileBlast.blastCookTime;
        this.lastBurnTime = this.tileBlast.blastBurnTime;
        this.lastItemBurnTime = this.tileBlast.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j) {
        if (i == 0) {
            this.tileBlast.blastCookTime = j;
        }

        if (i == 1) {
            this.tileBlast.blastBurnTime = j;
        }

        if (i == 2) {
            this.tileBlast.currentItemBurnTime = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileBlast.isUseableByPlayer(entityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (i != 1 && i != 0) {
                if (BlastRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityBlast.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (i >= 3 && i < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (i >= 30 && i < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, itemstack1);
        }

        return itemstack;
    }
}