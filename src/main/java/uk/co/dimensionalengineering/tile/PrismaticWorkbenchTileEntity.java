package uk.co.dimensionalengineering.tile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import uk.co.dimensionalengineering.container.PrismaticWorkbenchContainer;
import uk.co.dimensionalengineering.helper.RegistryHelper;
import uk.co.dimensionalengineering.item.DimensionalDisketteItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class PrismaticWorkbenchTileEntity extends TileEntity implements INamedContainerProvider, ICapabilityProvider, IItemHandler, IItemHandlerModifiable, ITickableTileEntity {
    public static final String ID = "prismatic-workbench";
    private static final String COUNTER_ID = "counter";
    private static final String DISPLAY_NAME = "Prismatic Workbench";
    private static final int SLOTS = 2;

    private int counter = 0;
    private int tickTimer = 0;
    private List<String> allowedItemIdPerSlot;

    private NonNullList<ItemStack> contents = NonNullList.withSize(SLOTS, ItemStack.EMPTY);

    public PrismaticWorkbenchTileEntity() {
        super(RegistryHelper.PRISMATIC_WORKBENCH_TILE_ENTITY.get());
        allowedItemIdPerSlot = Arrays.asList(RegistryHelper.PRISMATIC_ITEM.getId().toString(), RegistryHelper.DIMENSIONAL_DISKETTE_ITEM.getId().toString());
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt(COUNTER_ID, counter);
        ItemStackHelper.saveAllItems(compound, contents);
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        counter = nbt.getInt(COUNTER_ID);
        ItemStackHelper.loadAllItems(nbt, contents);
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
        if (slot <= getSlots()) {
            return contents.get(slot);
        }
        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stackIn, boolean simulate) {
        if (slot <= getSlots()) {
            ItemStack currentStack = contents.get(slot);
            if (ItemStack.EMPTY.equals(currentStack)) {
                int quantityOut = stackIn.getCount() - 1;
                ItemStack stackOut = ItemHandlerHelper.copyStackWithSize(stackIn, quantityOut);
                if (!simulate) {
                    contents.set(slot, ItemHandlerHelper.copyStackWithSize(stackIn, 1));
                }
                return stackOut;
            } else {
                //TODO: 'Swap' the items.
            }
        }
        return stackIn;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount != 0 && slot <= contents.size()) {
            ItemStack itemStack = contents.get(slot);
            int toExtract = Math.min(amount, itemStack.getMaxStackSize());
            if (itemStack.getCount() <= toExtract) {
                if (!simulate) {
                    contents.set(slot, ItemStack.EMPTY);
                }
                return itemStack;
            } else {
                if (!simulate) {
                    contents.set(slot, ItemHandlerHelper.copyStackWithSize(itemStack, itemStack.getCount() - toExtract));
                }
                return ItemHandlerHelper.copyStackWithSize(itemStack, toExtract);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot <= contents.size()) {
            return 1;
            // Don't want this as it just returns the Item's max stack size:
            //return contents.get(slot).getMaxStackSize();
        } else {
            //TODO: Find out if this is the correct way of saying get stuffed.
            return -1;
        }
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        //TODO: Abstract / make nice framework for this
        String requestedId = stack.getItem().getRegistryName().toString();
        if (allowedItemIdPerSlot.get(slot).equals(requestedId)) {
            return true;
        }
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

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        if (slot <= getSlots()) {
            contents.set(slot, stack);
        }
    }
}
