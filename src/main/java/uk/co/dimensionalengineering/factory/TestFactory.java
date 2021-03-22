package uk.co.dimensionalengineering.factory;

import uk.co.dimensionalengineering.block.TestBlock;
import uk.co.dimensionalengineering.helper.RegistryHelper;
import uk.co.dimensionalengineering.item.TestItem;

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
        return new TestItem(RegistryHelper.TEST_BLOCK.get(), TestItem.generateProperties());
    }
}
