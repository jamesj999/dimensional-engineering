package uk.co.dimensionalengineering.tile;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import uk.co.dimensionalengineering.DimensionalEngineering;

public class PrismaticWorkbenchTileEntity extends TileEntity {
    public static final String ID = "prismatic-workbench";
    private static final String COUNTER_ID = "counter";

    private int counter = 0;

    public PrismaticWorkbenchTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
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
}
