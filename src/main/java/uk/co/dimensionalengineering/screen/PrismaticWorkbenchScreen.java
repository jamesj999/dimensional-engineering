package uk.co.dimensionalengineering.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import uk.co.dimensionalengineering.DimensionalEngineering;
import uk.co.dimensionalengineering.container.PrismaticWorkbenchContainer;
import uk.co.dimensionalengineering.tile.PrismaticWorkbenchTileEntity;

public class PrismaticWorkbenchScreen extends ContainerScreen<PrismaticWorkbenchContainer> {
    protected int xSize = 190;
    protected int ySize = 210;

    private final ResourceLocation GUI = new ResourceLocation(DimensionalEngineering.MOD_ID, "textures/gui/prismatic_workbench_gui.png");

    public PrismaticWorkbenchScreen(PrismaticWorkbenchContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        drawString(matrixStack, Minecraft.getInstance().fontRenderer, "Weed! x " + container.getCounter(), 0xFFFFFF, x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        //TODO wat is, wat do
        RenderSystem.color4f(1F,1F,1F,1F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
    }

}
