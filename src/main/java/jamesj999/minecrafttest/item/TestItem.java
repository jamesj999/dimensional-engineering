package jamesj999.minecrafttest.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class TestItem extends BlockItem {
    public TestItem(Block blockIn, Properties properties) {
        super(blockIn, properties);

    }

    public static Item.Properties generateProperties() {
        Item.Properties properties = new Item.Properties();
        properties.group(ItemGroup.DECORATIONS);
        properties.maxStackSize(64);
        return properties;
    }

}
