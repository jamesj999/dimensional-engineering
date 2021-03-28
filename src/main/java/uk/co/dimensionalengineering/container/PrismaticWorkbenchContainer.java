package uk.co.dimensionalengineering.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import uk.co.dimensionalengineering.helper.RegistryHelper;
import uk.co.dimensionalengineering.tile.PrismaticWorkbenchTileEntity;

public class PrismaticWorkbenchContainer extends Container {

    public static final String ID = "prismatic-workbench";
    public TileEntity tileEntity;
    public IItemHandler playerInventory;
    public World world;

    public PrismaticWorkbenchContainer(int windowId, World world, PlayerInventory playerInventory, TileEntity tileEntity) {
        super(RegistryHelper.PRISMATIC_WORKBENCH_CONTAINER.get(), windowId);
        this.playerInventory = new InvWrapper(playerInventory);
        this.world = world;
        this.tileEntity = tileEntity;

        if (tileEntity instanceof PrismaticWorkbenchTileEntity) {
            SlotItemHandler upgradeHandler = new SlotItemHandler((IItemHandler) tileEntity, 0, 13, 8);
            SlotItemHandler disketteHandler = new SlotItemHandler((IItemHandler) tileEntity, 1, 13, 50);

            addSlot(upgradeHandler);
            addSlot(disketteHandler);
        }
        layoutPlayerInventorySlots(8, 94);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(world, tileEntity.getPos()), player, RegistryHelper.PRISMATIC_WORKBENCH_BLOCK.get());
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Hotbar
        int hotbarTopRow = topRow + 58;
        addSlotRange(playerInventory, 0, leftCol, hotbarTopRow, 9, 18);

        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount,
                           int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    public int getCounter() {
        if (tileEntity instanceof PrismaticWorkbenchTileEntity) {
            return ((PrismaticWorkbenchTileEntity) tileEntity).getCounter();
        }
        return 0;
    }

    /**
     * This method is responsible for handling shift clicks on an item which is either held in our Tile or in the
     * Player's inventory.
     * <p>
     * An assumption is made about the order - our Tile's inventory should ALWAYS be BEFORE the Player's inventory in
     * {@link #inventorySlots}.
     *
     * @param playerIn
     * @param index
     * @return
     */
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        if (!(inventorySlots.get(index) instanceof SlotItemHandler)) {
            // Can't deal with this, shouldn't happen?
            return inventorySlots.get(index).getStack();
        }
        SlotItemHandler sourceSlot = ((SlotItemHandler) inventorySlots.get(index));
        boolean fromContainer = sourceSlot.getItemHandler().getClass().equals(tileEntity.getClass());

        IItemHandler sourceHandler;
        IItemHandler targetHandler;
        if (fromContainer) {
            targetHandler = playerInventory;
            sourceHandler = (IItemHandler) tileEntity;
        } else {
            targetHandler = (IItemHandler) tileEntity;
            sourceHandler = playerInventory;
        }

        ItemStack sourceItem = sourceSlot.getStack();

        // This loop searches to see if there are any stacks matching the type of item we are trying to transfer and transfers what it can
        for (int i = 0; i < targetHandler.getSlots(); i++) {
            ItemStack stackInSlot = targetHandler.getStackInSlot(i);
            // Check the stack is not empty and it has our type of item already in it:
            if (!ItemStack.EMPTY.equals(stackInSlot) && targetHandler.isItemValid(sourceSlot.getSlotIndex(), sourceItem) && sourceItem.getItem().getClass().equals(stackInSlot.getItem())) {
                if (stackInSlot.getItem().getClass().equals(sourceItem.getItem().getClass()) && stackInSlot.getCount() <= stackInSlot.getMaxStackSize()) {
                    return targetHandler.insertItem(i, sourceHandler.extractItem(sourceSlot.getSlotIndex(), sourceItem.getCount(), false), false);
                }
            }
        }

        // This loop searches for the first available empty slot and transfers there.
        for (int i = 0; i < targetHandler.getSlots(); i++) {
            if (targetHandler.isItemValid(i, sourceItem)) {
                ItemStack stackInSlot = targetHandler.getStackInSlot(i);
                // The ItemStack we're checking is empty, so can transfer immediately.
                if (ItemStack.EMPTY.equals(stackInSlot)) {
                    return targetHandler.insertItem(i, sourceHandler.extractItem(sourceSlot.getSlotIndex(), sourceItem.getCount(), false), false);
                }
            }
        }
        // I assume the return value should be what's left that can't be transferred?! Document your shit, Forge.
        return sourceItem;
    }
}
