package uk.co.dimensionalengineering.block;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import uk.co.dimensionalengineering.factory.BlockAndItemFactory;
import uk.co.dimensionalengineering.tile.PrismaticWorkbenchTileEntity;

import javax.annotation.Nullable;

public class PrismaticWorkbenchBlock extends AbstractFurnaceBlock {
    public static final String ID = "prismatic-workbench";

    public PrismaticWorkbenchBlock(Properties properties) {
        super(properties);
    }

    public static Properties generateProperties() {
        Properties properties = Properties.create(Material.ROCK);
        properties.harvestTool(ToolType.PICKAXE);
        properties.setRequiresTool();
        properties.hardnessAndResistance(3.5f, 10.0f);
        properties.harvestLevel(2);

        return properties;
    }

    @Override
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
        //do some stuff
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return null;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return BlockAndItemFactory.createPrismaticWorkbenchTile();
    }
}
