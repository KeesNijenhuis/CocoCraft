package nl.nijenhuis.cococraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.nijenhuis.cococraft.CocoCraft;
import nl.nijenhuis.cococraft.tileentity.TileEntityBlast;

import java.util.Random;

public class BlockBlastFurnace extends Block implements ITileEntityProvider{

    private final Random rand = new Random();
    private static boolean isActive;

    public BlockBlastFurnace(boolean active) {
        super(Material.iron);
        isActive = active;
        this.setHardness(3F);
        this.setCreativeTab(CocoCraft.tabCocoCraft);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityBlast();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int faceHit, float par7, float par8, float par9) {

        if(world.isRemote) {
            return true;
        } else if(!player.isSneaking()) {
            TileEntityBlast tile = (TileEntityBlast)world.getTileEntity(x, y, z);
            if(tile != null) {
                player.openGui(CocoCraft.instance, 1, world, x, y, z);
            }
            return true;
        } else {
            return false;
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int i) {
        if (!isActive) {
            TileEntityBlast tileentityblast = (TileEntityBlast) world.getTileEntity(x, y, z);

            if (tileentityblast != null) {
                for (int i1 = 0; i1 < tileentityblast.getSizeInventory(); ++i1) {
                    ItemStack itemstack = tileentityblast.getStackInSlot(i1);

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
