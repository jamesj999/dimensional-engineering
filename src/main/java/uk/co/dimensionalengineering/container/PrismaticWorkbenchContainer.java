package uk.co.dimensionalengineering.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
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
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                // TODO - Retrieve Container Inventory from tileEntity and render here.
                addSlot(new SlotItemHandler(h, 0, 0, 0));
            });
        }
        layoutPlayerInventorySlots(7, 84);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(world, tileEntity.getPos()), player, RegistryHelper.PRISMATIC_WORKBENCH_BLOCK.get());
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
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


}
