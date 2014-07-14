package nl.nijenhuis.cococraft.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import nl.nijenhuis.cococraft.handler.recipes.CrusherRecipes;
import nl.nijenhuis.cococraft.tileentity.TileEntityCrusher;

public class ContainerCrusher extends Container {
    private TileEntityCrusher tileCrusher;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerCrusher(InventoryPlayer invPlayer, TileEntityCrusher tileEntityCrusher) {
        this.tileCrusher = tileEntityCrusher;
        this.addSlotToContainer(new Slot(tileEntityCrusher, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileEntityCrusher, 1, 56, 53));
        this.addSlotToContainer(new SlotCrusher(invPlayer.player, tileEntityCrusher, 2, 116, 35));
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
        iCrafting.sendProgressBarUpdate(this, 0, this.tileCrusher.crusherCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileCrusher.crusherBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileCrusher.currentItemBurnTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.tileCrusher.crusherCookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileCrusher.crusherCookTime);
            }

            if (this.lastBurnTime != this.tileCrusher.crusherBurnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileCrusher.crusherBurnTime);
            }

            if (this.lastItemBurnTime != this.tileCrusher.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.tileCrusher.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.tileCrusher.crusherCookTime;
        this.lastBurnTime = this.tileCrusher.crusherBurnTime;
        this.lastItemBurnTime = this.tileCrusher.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j) {
        if (i == 0) {
            this.tileCrusher.crusherCookTime = j;
        }

        if (i == 1) {
            this.tileCrusher.crusherBurnTime = j;
        }

        if (i == 2) {
            this.tileCrusher.currentItemBurnTime = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileCrusher.isUseableByPlayer(entityPlayer);
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
                if (CrusherRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityCrusher.isItemFuel(itemstack1)) {
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