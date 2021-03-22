package uk.co.dimensionalengineering.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class PrismaticWorkbenchItem extends BlockItem {
    public static final String ID = "prismatic-workbench";

    public PrismaticWorkbenchItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    public static Item.Properties generateProperties() {
        Item.Properties properties = new Item.Properties();
        properties.group(ItemGroup.MISC);
        properties.maxStackSize(64);
        return properties;
    }
}
