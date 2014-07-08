package nl.nijenhuis.cococraft.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.nijenhuis.cococraft.client.gui.GuiBlast;
import nl.nijenhuis.cococraft.inventory.ContainerBlast;
import nl.nijenhuis.cococraft.tileentity.TileEntityBlast;

public class MyGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tile = world.getTileEntity(x, y, z);

        if(tile != null) {
            switch(ID) {
                case 1 :
                    return new ContainerBlast(player.inventory, (TileEntityBlast)tile);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tile = world.getTileEntity(x, y, z);

        if(tile != null) {
            switch(ID) {
                case 1 :
                    return new GuiBlast(player.inventory, (TileEntityBlast)tile);
            }
        }

        return null;
    }
}
