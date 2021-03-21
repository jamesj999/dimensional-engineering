package jamesj999.minecrafttest.factory;

import jamesj999.minecrafttest.MinecraftTest;
import jamesj999.minecrafttest.block.TestBlock;
import jamesj999.minecrafttest.item.TestItem;

/**
 *  Ye Olde Designe Pattern
 */
public class TestFactory {

    /**
     *
     * @return new TestBlock with default config
     */
    public static TestBlock createBlock() {
        return new TestBlock(TestBlock.generateProperties());
    }

    /**
     *
     * @return new TestItem with default config
     */
    public static TestItem createItem() {
        return new TestItem(MinecraftTest.TEST_BLOCK.get(), TestItem.generateProperties());
    }
}
