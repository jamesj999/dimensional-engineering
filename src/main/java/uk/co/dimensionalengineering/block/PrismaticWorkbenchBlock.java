package uk.co.dimensionalengineering.block;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
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
