package uk.co.dimensionalengineering.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import uk.co.dimensionalengineering.tile.PrismaticWorkbenchTileEntity;

import javax.annotation.Nullable;

public class PrismaticWorkbenchBlock extends Block {
    public static final String ID = "prismatic-workbench";

    public PrismaticWorkbenchBlock() {
        super(Properties.create(Material.IRON).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(3.5f, 10.0f).harvestLevel(2));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (!(tileEntity instanceof PrismaticWorkbenchTileEntity)) {
                throw new RuntimeException("Expected PrismaticWorkbenchTileEntity at " + pos + " but found " + tileEntity.getClass());
            }
            PrismaticWorkbenchTileEntity prismaticTileEntity = (PrismaticWorkbenchTileEntity) tileEntity;
            int counter = prismaticTileEntity.increase();
            player.sendStatusMessage(new StringTextComponent("Increased to " + counter), false);

            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, prismaticTileEntity.getPos());
        }
        return ActionResultType.CONSUME;
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
}
