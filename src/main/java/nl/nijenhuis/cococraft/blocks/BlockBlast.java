package nl.nijenhuis.cococraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import nl.nijenhuis.cococraft.CocoCraft;
import nl.nijenhuis.cococraft.reference.Reference;
import nl.nijenhuis.cococraft.tileentity.TileEntityBlast;

import java.util.Random;

public class BlockBlast extends BlockContainer {

    private final Random rand = new Random();
    public static boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iIcon1;
    @SideOnly(Side.CLIENT)
    private IIcon iIcon2;

    public BlockBlast(boolean active) {
        super(Material.iron);
        isActive = active;
        setCreativeTab(CocoCraft.tabCocoCraft);
        setHardness(5.0F);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int i, float f, float f1, float f2) {
        if(world.isRemote) {
            return true;
        } else if(!entityPlayer.isSneaking()) {
            TileEntityBlast tile = (TileEntityBlast)world.getTileEntity(x, y, z);
            if(tile != null) {
                entityPlayer.openGui(CocoCraft.instance, 1, world, x, y, z);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityBlast();
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return i == 1 ? iIcon1 : (i == 0 ? iIcon1 : (i != j ? blockIcon : iIcon2));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(Reference.RESOURCE + "blastFurnace_side");
        iIcon2 = iconRegister.registerIcon(Reference.RESOURCE + "blastFurnace_front");
        iIcon1 = iconRegister.registerIcon(Reference.RESOURCE + "blastFurnace_top");
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
        int l = MathHelper.floor_double((double) (entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemStack.hasDisplayName()) {
            ((TileEntityBlast) world.getTileEntity(x, y, z)).setInvName(itemStack.getDisplayName());
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int i) {
        if (!isActive) {
            TileEntityBlast tileEntityBlast = (TileEntityBlast) world.getTileEntity(x, y, z);

            if (tileEntityBlast != null) {
                for (int i1 = 0; i1 < tileEntityBlast.getSizeInventory(); ++i1) {
                    ItemStack itemstack = tileEntityBlast.getStackInSlot(i1);

                    if (itemstack != null) {
                        float f = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0) {
                            int j1 = this.rand.nextInt(21) + 10;

                            if (j1 > itemstack.stackSize) {
                                j1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double) ((float) this.rand.nextGaussian() * f3);
                            entityitem.motionY = (double) ((float) this.rand.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double) ((float) this.rand.nextGaussian() * f3);
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                world.func_147453_f(x, y, z, block);
            }
        }

        super.breakBlock(world, x, y, z, block, i);
    }
}
