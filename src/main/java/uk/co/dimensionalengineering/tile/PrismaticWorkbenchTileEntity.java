package uk.co.dimensionalengineering.tile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandler;
import uk.co.dimensionalengineering.container.PrismaticWorkbenchContainer;
import uk.co.dimensionalengineering.helper.RegistryHelper;
import uk.co.dimensionalengineering.item.DimensionalDisketteItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PrismaticWorkbenchTileEntity extends TileEntity implements INamedContainerProvider, ICapabilityProvider, IItemHandler, ITickableTileEntity {
    public static final String ID = "prismatic-workbench";
    private static final String COUNTER_ID = "counter";
    private static final String DISPLAY_NAME = "Prismatic Workbench";
    private static final int SLOTS = 2;

    private int counter = 0;
    private int tickTimer = 0;

    public ItemStack[] contents;

    public PrismaticWorkbenchTileEntity() {
        super(RegistryHelper.PRISMATIC_WORKBENCH_TILE_ENTITY.get());
        contents = new ItemStack[getSlots()];
    }

    public int getCounter() {
        return counter;
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
        return new PrismaticWorkbenchContainer(windowId, world, playerInventory, this);
    }

    @Override
    public int getSlots() {
        return SLOTS;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot <= getSlots()) {
            ItemStack itemStack = contents[slot];
            //itemStack.
        }
        return null;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return null;
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot <= getSlots()) {
            return contents[slot].getMaxStackSize();
        } else {
            //TODO: Find out if this is the correct way of saying get stuffed.
            return -1;
        }
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return false;
    }

    @Override
    public void tick() {
        if (world != null && !world.isRemote()) {
            if (tickTimer == 100) {
                PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false);
                if (player != null && player.getHeldEquipment().iterator().next().getItem() instanceof DimensionalDisketteItem) {
                    player.sendStatusMessage(new StringTextComponent("Pop that disk in, " + player.getScoreboardName() + "..."), true);
                }
                tickTimer = 0;
            }
            tickTimer++;
        }
    }
}
