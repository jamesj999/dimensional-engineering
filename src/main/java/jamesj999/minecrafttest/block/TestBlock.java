package jamesj999.minecrafttest.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TestBlock extends Block {
    public TestBlock(Properties properties) {
        super(properties);
    }

    public TestBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK));
    }
}
