package nl.nijenhuis.cococraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import nl.nijenhuis.cococraft.inventory.ContainerBlast;
import nl.nijenhuis.cococraft.reference.Reference;
import nl.nijenhuis.cococraft.tileentity.TileEntityBlast;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiBlast extends GuiContainer {

    private static final ResourceLocation blastGuiTextures = new ResourceLocation(Reference.RESOURCE + "textures/gui/blastFurnace.png");
    private TileEntityBlast tileBlast;

    public GuiBlast(InventoryPlayer invPlayer, TileEntityBlast tile) {
        super(new ContainerBlast(invPlayer, tile));
        tileBlast = tile;
    }


    protected void drawGuiContainerForegroundLayer(int i, int j) {

        String s = tileBlast.hasCustomInventoryName() ? tileBlast.getInventoryName() : I18n.format(tileBlast.getInventoryName(), new Object[0]);
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(blastGuiTextures);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

        if (tileBlast.isBurning()) {
            int i1 = tileBlast.getBurnTimeRemainingScaled(13);
            drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
            i1 = tileBlast.getCookProgressScaled(24);
            drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        }

    }
}
