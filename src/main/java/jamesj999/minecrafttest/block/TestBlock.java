package jamesj999.minecrafttest.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class TestBlock extends Block {
    public TestBlock(Properties properties) {
        super(properties);
    }

    public static Properties generateProperties() {
        Properties properties = Properties.create(Material.ROCK, MaterialColor.STONE);
        return properties;
    }
}
