package uk.co.dimensionalengineering.tile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;
import uk.co.dimensionalengineering.container.PrismaticWorkbenchContainer;
import uk.co.dimensionalengineering.helper.RegistryHelper;

import javax.annotation.Nullable;

public class PrismaticWorkbenchTileEntity extends TileEntity implements INamedContainerProvider {
    public static final String ID = "prismatic-workbench";
    private static final String COUNTER_ID = "counter";
    private static final String DISPLAY_NAME = "Prismatic Workbench";

    public int getCounter() {
        return counter;
    }

    private int counter = 0;
    public ItemStack[] workbenchContents;

    public PrismaticWorkbenchTileEntity() {
        super(RegistryHelper.PRISMATIC_WORKBENCH_TILE_ENTITY.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt(COUNTER_ID, counter);
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        counter = nbt.getInt(COUNTER_ID);
        super.read(state, nbt);
    }

    public int increase() {
        counter++;
        markDirty();
        return counter;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(DISPLAY_NAME);
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new PrismaticWorkbenchContainer(windowId, world, pos, playerInventory, playerEntity);
    }
}
