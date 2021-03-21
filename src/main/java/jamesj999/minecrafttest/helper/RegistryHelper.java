package jamesj999.minecrafttest.helper;

import jamesj999.minecrafttest.MinecraftTest;
import jamesj999.minecrafttest.block.TestBlock;
import jamesj999.minecrafttest.factory.TestFactory;
import jamesj999.minecrafttest.item.TestItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHelper {

    private static final DeferredRegister<Block> BLOCKS;
    private static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Block> TEST_BLOCK;
    public static final RegistryObject<Item> TEST_ITEM;

    static {
        //Get Register Singletons
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MinecraftTest.MOD_ID);
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MinecraftTest.MOD_ID);

        //Register blocks
        TEST_BLOCK = BLOCKS.register(TestBlock.ID, TestFactory::createBlock);
        TEST_ITEM = ITEMS.register(TestItem.ID, TestFactory::createItem);

    }

    public static DeferredRegister<Block> getBlocksRegistry() {
        return BLOCKS;
    }

    public static DeferredRegister<Item> getItemsRegistry() {
        return ITEMS;
    }

    public static void addRegistriesToLoader() {
        getBlocksRegistry().register(FMLJavaModLoadingContext.get().getModEventBus());
        getItemsRegistry().register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
