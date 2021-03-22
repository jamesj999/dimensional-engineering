package uk.co.dimensionalengineering.factory;

import uk.co.dimensionalengineering.block.PrismaticBlock;
import uk.co.dimensionalengineering.helper.RegistryHelper;
import uk.co.dimensionalengineering.item.PrismaticBlockItem;

/**
 * Ye Olde Designe Pattern
 */
public class BlockAndItemFactory {

    /**
     *
     * @return Prismatic Block instantiated with default Properties.
     */
    public static PrismaticBlock createPrismaticBlock() {
        return new PrismaticBlock(PrismaticBlock.generateProperties());
    }

    public static PrismaticBlockItem createPrismaticBlockItem() {
        return new PrismaticBlockItem(RegistryHelper.PRISMATIC_BLOCK.get(), PrismaticBlockItem.generateProperties());
    }
}
