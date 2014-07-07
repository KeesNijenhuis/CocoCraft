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
public class GuiBlastFurnace extends GuiContainer {

    private static final ResourceLocation guiTexture = new ResourceLocation(Reference.RESOURCE + "textures/gui/blastFurnace.png");
    private TileEntityBlast tileBlast;

    public GuiBlastFurnace(InventoryPlayer player, TileEntityBlast tile) {
        super(new ContainerBlast(player, tile));
        this.tileBlast = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String s = this.tileBlast.hasCustomInventoryName() ? this.tileBlast.getInventoryName() : I18n.format(this.tileBlast.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiTexture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (this.tileBlast.isBurning()) {
            i1 = this.tileBlast.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.tileBlast.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
}
