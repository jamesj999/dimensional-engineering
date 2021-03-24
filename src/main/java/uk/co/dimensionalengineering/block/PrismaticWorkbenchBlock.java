package uk.co.dimensionalengineering.block;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import uk.co.dimensionalengineering.container.PrismaticWorkbenchContainer;
import uk.co.dimensionalengineering.tile.PrismaticWorkbenchTileEntity;

import javax.annotation.Nullable;

public class PrismaticWorkbenchBlock extends AbstractFurnaceBlock {
    public static final String ID = "prismatic-workbench";

    public PrismaticWorkbenchBlock() {
        super(Properties.create(Material.IRON).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(3.5f, 10.0f).harvestLevel(2));
    }

    @Override
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
        PrismaticWorkbenchTileEntity tileEntity = (PrismaticWorkbenchTileEntity) worldIn.getTileEntity(pos);
        int counter = tileEntity.increase();
        player.sendStatusMessage(new StringTextComponent("Increased to " + counter), false);

        if (!worldIn.isRemote) {
            if (tileEntity instanceof PrismaticWorkbenchTileEntity) {

                INamedContainerProvider containerProvider = new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new StringTextComponent("Prismatic Workbench Block");
                    }

                    @Nullable
                    @Override
                    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                        return new PrismaticWorkbenchContainer(i, worldIn, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
            }
        }

    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PrismaticWorkbenchTileEntity();
    }

    /**
     * No idea what this does... the docs say to use {@link #createTileEntity(BlockState, IBlockReader)}
     *
     * @param worldIn
     * @return
     */
    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return null;
    }
}
