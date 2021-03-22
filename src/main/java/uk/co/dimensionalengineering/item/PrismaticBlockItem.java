package uk.co.dimensionalengineering.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 *
 */
public class PrismaticBlockItem extends BlockItem {

    public static final String ID = "prismaticblock";

    /**
     *
     * @param block Block item in registry
     * @param properties Properties of the item
     */
    public PrismaticBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    /**
     *
     * @return default properties for item
     */
    public static Item.Properties generateProperties() {
        Item.Properties properties = new Item.Properties();
        properties.group(ItemGroup.MISC);
        properties.maxStackSize(64);
        return properties;
    }

}
