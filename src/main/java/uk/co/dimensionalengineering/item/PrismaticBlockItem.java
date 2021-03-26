package uk.co.dimensionalengineering.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import uk.co.dimensionalengineering.helper.RegistryHelper;

/**
 *
 */
public class PrismaticBlockItem extends BlockItem {

    public static final String ID = "prismaticblock";

    public PrismaticBlockItem() {
        super(RegistryHelper.PRISMATIC_BLOCK.get(), new Item.Properties().group(ItemGroup.MISC).maxStackSize(64));
    }
}
