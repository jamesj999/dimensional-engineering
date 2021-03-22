package uk.co.dimensionalengineering.helper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.co.dimensionalengineering.DimensionalEngineering;
import uk.co.dimensionalengineering.block.PrismaticBlock;
import uk.co.dimensionalengineering.factory.BlockAndItemFactory;
import uk.co.dimensionalengineering.item.PrismaticBlockItem;

public class RegistryHelper {

    private static final DeferredRegister<Block> BLOCKS;
    private static final DeferredRegister<Item> ITEMS;

    public static final RegistryObject<Block> PRISMATIC_BLOCK;

    public static final RegistryObject<Item> PRISMATIC_ITEM;


    static {
        //Get Register Singletons
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DimensionalEngineering.MOD_ID);
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DimensionalEngineering.MOD_ID);

        //Register blocks
        PRISMATIC_BLOCK = BLOCKS.register(PrismaticBlock.ID, BlockAndItemFactory::createPrismaticBlock);
        PRISMATIC_ITEM = ITEMS.register(PrismaticBlockItem.ID, BlockAndItemFactory::createPrismaticBlockItem);
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
