package jamesj999.minecrafttest.item;

import jamesj999.minecrafttest.MinecraftTest;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 *
 */
public class TestItem extends BlockItem {

    /**
     *
     * @param block Block item in registry
     * @param properties Properties of the item
     */
    public TestItem(Block block, Properties properties) {
        super(block, properties);

    }

    /**
     *
     * @return default properties for item
     */
    public static Item.Properties generateProperties() {
        Item.Properties properties = new Item.Properties();
        properties.group(ItemGroup.DECORATIONS);
        properties.maxStackSize(64);
        return properties;
    }

}
