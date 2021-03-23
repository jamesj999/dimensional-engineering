package uk.co.dimensionalengineering.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import uk.co.dimensionalengineering.helper.RegistryHelper;

public class PrismaticWorkbenchItem extends BlockItem {
    public static final String ID = "prismatic-workbench";

    public PrismaticWorkbenchItem() {
        super(RegistryHelper.PRISMATIC_WORKBENCH_BLOCK.get(), new Item.Properties().group(ItemGroup.MISC).maxStackSize(64));
    }

}
