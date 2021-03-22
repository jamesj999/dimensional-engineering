package uk.co.dimensionalengineering.factory;

import net.minecraft.tileentity.TileEntityType;
import uk.co.dimensionalengineering.block.PrismaticBlock;
import uk.co.dimensionalengineering.block.PrismaticWorkbenchBlock;
import uk.co.dimensionalengineering.helper.RegistryHelper;
import uk.co.dimensionalengineering.item.PrismaticBlockItem;
import uk.co.dimensionalengineering.item.PrismaticWorkbenchItem;
import uk.co.dimensionalengineering.tile.PrismaticWorkbenchTileEntity;

/**
 * Ye Olde Designe Pattern
 */
public class BlockAndItemFactory {

    /**
     * @return Prismatic Block instantiated with default Properties.
     */
    public static PrismaticBlock createPrismaticBlock() {
        return new PrismaticBlock(PrismaticBlock.generateProperties());
    }

    public static PrismaticWorkbenchBlock createPrismaticWorkbenchBlock() {
        return new PrismaticWorkbenchBlock(PrismaticWorkbenchBlock.generateProperties());
    }

    public static PrismaticBlockItem createPrismaticBlockItem() {
        return new PrismaticBlockItem(RegistryHelper.PRISMATIC_BLOCK.get(), PrismaticBlockItem.generateProperties());
    }

    public static PrismaticWorkbenchItem createPrismaticWorkbenchItem() {
        return new PrismaticWorkbenchItem(RegistryHelper.PRISMATIC_WORKBENCH_BLOCK.get(), PrismaticWorkbenchItem.generateProperties());
    }

    public static PrismaticWorkbenchTileEntity createPrismaticWorkbenchTile() {
        return new PrismaticWorkbenchTileEntity(TileEntityType.FURNACE);
    }
}
