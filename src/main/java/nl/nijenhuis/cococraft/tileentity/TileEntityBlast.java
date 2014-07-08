package nl.nijenhuis.cococraft.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import nl.nijenhuis.cococraft.handler.recipes.BlastRecipes;

public class TileEntityBlast extends TileEntity implements ISidedInventory {

    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{2, 1};
    private static final int[] slotsSides = new int[]{1};

    private ItemStack[] blastStacks = new ItemStack[3];

    /**
     * the number of tick the furnace will keep burning
     */
    public int blastBurnTime;
    /**
     * number of ticks that a fresh copt of the currently burning item would keep the furnace burning for
     */
    public int currentItemBurnTime;
    /**
     * the number of tucks that the current item has been cooking for
     */
    public int blastCookTime;

    private String invName;

    public int getSizeInventory() {
        return this.blastStacks.length;
    }

    public ItemStack getStackInSlot(int i) {
        return blastStacks[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (blastStacks[i] != null) {
            ItemStack itemStack;

            if (blastStacks[i].stackSize <= j) {
                itemStack = blastStacks[i];
                blastStacks[i] = null;
                return itemStack;
            } else {
                itemStack = blastStacks[i].splitStack(j);

                if (blastStacks[i].stackSize == 0) {
                    blastStacks[i] = null;
                }
                return itemStack;
            }
        } else {
            return null;
        }

    }

    public ItemStack getStackInSlotOnClosing(int i) {
        if (blastStacks[i] != null) {
            ItemStack itemStack = blastStacks[i];
            blastStacks[i] = null;
            return itemStack;
        } else {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemStack) {
        blastStacks[i] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    public String getInventoryName() {
        return hasCustomInventoryName() ? invName : "container.blastFurnace";
    }

    public boolean hasCustomInventoryName() {
        return invName != null && invName.length() > 0;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbtTagList = nbtTagCompound.getTagList("Items", 10);
        blastStacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound nbtTagCompound1 = nbtTagList.getCompoundTagAt(i);
            byte b0 = nbtTagCompound1.getByte("Slot");

            if (b0 >= 0 && b0 < blastStacks.length) {
                blastStacks[b0] = ItemStack.loadItemStackFromNBT(nbtTagCompound1);
            }
        }
        blastBurnTime = nbtTagCompound.getShort("BurnTime");
        blastCookTime = nbtTagCompound.getShort("CookTime");
        currentItemBurnTime = getItemBurnTime(blastStacks[1]);

        if (nbtTagCompound.hasKey("CustomName", 8)) {
            invName = nbtTagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("BurnTime", (short) blastBurnTime);
        nbtTagCompound.setShort("CookTime", (short) blastCookTime);
        NBTTagList nbtTagList = new NBTTagList();

        for (int i = 0; i < blastStacks.length; ++i) {
            if (blastStacks[i] != null) {
                NBTTagCompound nbtTagCompound1 = new NBTTagCompound();
                nbtTagCompound1.setByte("Slot", (byte) i);
                blastStacks[i].writeToNBT(nbtTagCompound1);
                nbtTagList.appendTag(nbtTagCompound1);
            }
        }
        nbtTagCompound.setTag("Items", nbtTagList);

        if (hasCustomInventoryName()) {
            nbtTagCompound.setString("CustomName", invName);
        }
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int i) {
        return blastCookTime * i / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int i) {
        if (currentItemBurnTime == 0) {
            currentItemBurnTime = 200;
        }
        return blastBurnTime * i / currentItemBurnTime;
    }

    public boolean isBurning() {
        return blastBurnTime > 0;
    }

    public void updateEntity() {
        boolean flag = blastBurnTime > 0;
        boolean flag1 = false;

        if (blastBurnTime > 0) {
            --blastBurnTime;
        }

        if (!worldObj.isRemote) {
            if (blastBurnTime != 0 || blastStacks[1] != null && blastStacks[0] != null) {
                if (blastBurnTime == 0 && canSmelt()) {
                    currentItemBurnTime = blastBurnTime = getItemBurnTime(blastStacks[1]);

                    if (blastBurnTime > 0) {
                        flag1 = true;

                        if (blastStacks[1] != null) {
                            --blastStacks[1].stackSize;

                            if (blastStacks[1].stackSize == 0) {
                                blastStacks[1] = blastStacks[1].getItem().getContainerItem(blastStacks[1]);
                            }
                        }
                    }
                }

                if (isBurning() && canSmelt()) {
                    ++blastCookTime;

                    if (blastCookTime == 200) {
                        blastCookTime = 0;
                        smeltItem();
                        flag1 = true;
                    }
                } else {
                    blastCookTime = 0;
                }
            }

            if (flag != blastCookTime > 0) {
                flag1 = true;
                //BlockFurnace.updateFurnaceBlockState(blastBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
            }
        }
        if (flag1) {
            markDirty();
        }
    }

    private boolean canSmelt() {
        if (blastStacks[0] == null) {
            return false;
        } else {
            ItemStack itemStack = BlastRecipes.smelting().getSmeltingResult(blastStacks[0]);
            if (itemStack == null)
                return false;
            if (blastStacks[2] == null)
                return true;
            if (!blastStacks[2].isItemEqual(itemStack))
                return false;
            int result = blastStacks[2].stackSize + itemStack.stackSize;
            return result <= getInventoryStackLimit() && result <= blastStacks[2].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (canSmelt()) {
            ItemStack itemStack = BlastRecipes.smelting().getSmeltingResult(blastStacks[0]);

            if (blastStacks[2] == null)
                blastStacks[2] = itemStack.copy();
            else if (blastStacks[2].getItem() == itemStack.getItem())
                blastStacks[2].stackSize += itemStack.stackSize;

            --blastStacks[0].stackSize;

            if (blastStacks[0].stackSize <= 0)
                blastStacks[0] = null;
        }
    }

    public static int getItemBurnTime(ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        } else {
            Item item = itemStack.getItem();

            if (item == Items.coal) return 80;


            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.coal_block) return 720;



            }
            return GameRegistry.getFuelValue(itemStack);
        }
    }

    public static boolean isItemFuel(ItemStack itemStack) {
        return getItemBurnTime(itemStack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : entityPlayer.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return i == 2 ? false : (i == 1 ? isItemFuel(itemStack) : true);
    }

    public int[] getAccessibleSlotsFromSide(int i) {
        return i == 0 ? slotsBottom : (i == 1 ? slotsTop : slotsSides);
    }

    public boolean canInsertItem(int i, ItemStack itemStack, int j) {
        return isItemValidForSlot(i, itemStack);
    }

    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return j != 0 || i != 1 || itemStack.getItem() == Items.bucket;
    }

}
