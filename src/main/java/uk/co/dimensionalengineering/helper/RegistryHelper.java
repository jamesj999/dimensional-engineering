package uk.co.dimensionalengineering.helper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.co.dimensionalengineering.DimensionalEngineering;
import uk.co.dimensionalengineering.block.PrismaticBlock;
import uk.co.dimensionalengineering.block.PrismaticWorkbenchBlock;
import uk.co.dimensionalengineering.factory.BlockAndItemFactory;
import uk.co.dimensionalengineering.item.PrismaticBlockItem;
import uk.co.dimensionalengineering.item.PrismaticWorkbenchItem;
import uk.co.dimensionalengineering.tile.PrismaticWorkbenchTileEntity;

public class RegistryHelper {

    private static final DeferredRegister<Block> BLOCKS;
    private static final DeferredRegister<Item> ITEMS;

    public static final RegistryObject<Block> PRISMATIC_BLOCK;
    public static final RegistryObject<Block> PRISMATIC_WORKBENCH_BLOCK;

    public static final RegistryObject<Item> PRISMATIC_ITEM;
    public static final RegistryObject<Item> PRISMATIC_WORKBENCH_ITEM;

    static {
        //Get Register Singletons
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DimensionalEngineering.MOD_ID);
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DimensionalEngineering.MOD_ID);

        //Register blocks
        PRISMATIC_BLOCK = BLOCKS.register(PrismaticBlock.ID, BlockAndItemFactory::createPrismaticBlock);
        PRISMATIC_WORKBENCH_BLOCK = BLOCKS.register(PrismaticWorkbenchBlock.ID, BlockAndItemFactory::createPrismaticWorkbenchBlock);

        //Register items
        PRISMATIC_ITEM = ITEMS.register(PrismaticBlockItem.ID, BlockAndItemFactory::createPrismaticBlockItem);
        PRISMATIC_WORKBENCH_ITEM = ITEMS.register(PrismaticWorkbenchItem.ID, BlockAndItemFactory::createPrismaticWorkbenchItem);

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

    @SubscribeEvent
    public static void registerPrismaticWorkbenchTileEntity(RegistryEvent.Register<TileEntityType<?>> evt) {
        Block[] validBlocks = new Block[1];
        validBlocks[0] = PRISMATIC_BLOCK.get();
        TileEntityType<?> type = TileEntityType.Builder.create(BlockAndItemFactory::createPrismaticWorkbenchTile, validBlocks).build(null);
        type.setRegistryName(DimensionalEngineering.MOD_ID, PrismaticWorkbenchTileEntity.ID);
        evt.getRegistry().register(type);
    }

}
