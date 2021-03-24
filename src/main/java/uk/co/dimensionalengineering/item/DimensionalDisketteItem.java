package uk.co.dimensionalengineering.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class DimensionalDisketteItem extends Item {
    public static final String ID = "dimensional-diskette";

    public DimensionalDisketteItem() {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));
    }
}
