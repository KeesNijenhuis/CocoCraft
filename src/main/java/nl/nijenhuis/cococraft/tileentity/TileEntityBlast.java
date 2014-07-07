package nl.nijenhuis.cococraft.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import nl.nijenhuis.cococraft.handler.recipes.BlastFurnaceRecipes;

public class TileEntityBlast extends TileEntity implements ISidedInventory {

    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{2, 1};
    private static final int[] slotsSides = new int[]{1};

    private ItemStack[] itemStacks = new ItemStack[3];

    public int blastBurnTime, currentCookTime, currentBurnTime;

    private String invName;


    @Override
    public int[] getAccessibleSlotsFromSide(int i) {
        return new int[1];
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return true;
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return 64;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return itemStacks[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (this.itemStacks[i] != null) {
            ItemStack itemstack;

            if (this.itemStacks[i].stackSize <= j) {
                itemstack = this.itemStacks[i];
                this.itemStacks[i] = null;
                return itemstack;
            } else {
                itemstack = this.itemStacks[i].splitStack(j);

                if (this.itemStacks[i].stackSize == 0) {
                    this.itemStacks[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.itemStacks[i] != null) {
            ItemStack itemstack = this.itemStacks[i];
            this.itemStacks[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack stack) {
        this.itemStacks[i] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return hasCustomInventoryName() ? invName : "container.blastFurnace";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return invName != null && invName.length() > 0;
    }

    public void func_145951_a(String invName) {
        this.invName = invName;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttag) {
        super.readFromNBT(nbttag);
        NBTTagList nbttaglist = nbttag.getTagList("Items", 10);
        this.itemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.itemStacks.length) {
                this.itemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.blastBurnTime = nbttag.getShort("BurnTime");
        this.currentCookTime = nbttag.getShort("CookTime");
        this.currentBurnTime = getItemBurnTime(this.itemStacks[1]);

        if (nbttag.hasKey("CustomName", 8)) {
            this.invName = nbttag.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttag) {
        super.writeToNBT(nbttag);
        nbttag.setShort("BurnTime", (short) this.blastBurnTime);
        nbttag.setShort("CookTime", (short) this.currentCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.itemStacks.length; ++i) {
            if (this.itemStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.itemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbttag.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName()) {
            nbttag.setString("CustomName", this.invName);
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int i) {
        return this.currentCookTime * i / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int i) {
        if (this.currentBurnTime == 0) {
            this.currentBurnTime = 200;
        }

        return this.blastBurnTime * i / this.currentBurnTime;
    }

    public boolean isBurning() {
        return this.blastBurnTime > 0;
    }

    @Override
    public void updateEntity() {
        boolean flag = this.blastBurnTime > 0;
        boolean flag1 = false;

        if (this.blastBurnTime > 0) {
            --this.blastBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.blastBurnTime == 0 && this.canSmelt()) {
                this.currentBurnTime = this.blastBurnTime = getItemBurnTime(this.itemStacks[1]);

                if (this.blastBurnTime > 0) {
                    flag1 = true;

                    if (this.itemStacks[1] != null) {
                        --this.itemStacks[1].stackSize;

                        if (this.itemStacks[1].stackSize == 0) {
                            this.itemStacks[1] = itemStacks[1].getItem().getContainerItem(itemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt()) {
                ++this.currentCookTime;

                if (this.currentCookTime == 200) {
                    this.currentCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.currentCookTime = 0;
            }

            if (flag != this.blastBurnTime > 0) {
                flag1 = true;
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canSmelt() {
        if (this.itemStacks[0] == null) {
            return false;
        } else {
            ItemStack itemstack = BlastFurnaceRecipes.smelting().getSmeltingResult(this.itemStacks[0]);
            if (itemstack == null)
                return false;
            if (this.itemStacks[2] == null)
                return true;
            if (!this.itemStacks[2].isItemEqual(itemstack))
                return false;
            int result = itemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.itemStacks[2].getMaxStackSize();

        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = BlastFurnaceRecipes.smelting().getSmeltingResult(this.itemStacks[0]);

            if (this.itemStacks[2] == null) {
                this.itemStacks[2] = itemstack.copy();
            } else if (this.itemStacks[2].getItem() == itemstack.getItem()) {
                this.itemStacks[2].stackSize += itemstack.stackSize;
            }

            --this.itemStacks[0].stackSize;

            if (this.itemStacks[0].stackSize <= 0) {
                this.itemStacks[0] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        } else {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab) {
                    return 40;
                }

                if (block.getMaterial() == Material.wood) {
                    return 80;
                }

            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD"))
                return 20;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
                return 20;
            if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD"))
                return 20;
            if (item == Items.stick)
                return 40;
            if (item == Item.getItemFromBlock(Blocks.sapling))
                return 40;
            return GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemFuel(ItemStack stack) {

        return getItemBurnTime(stack) > 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }
}
