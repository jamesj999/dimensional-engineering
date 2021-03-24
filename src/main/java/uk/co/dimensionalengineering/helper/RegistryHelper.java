package uk.co.dimensionalengineering.helper;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.co.dimensionalengineering.block.PrismaticBlock;
import uk.co.dimensionalengineering.block.PrismaticWorkbenchBlock;
import uk.co.dimensionalengineering.container.PrismaticWorkbenchContainer;
import uk.co.dimensionalengineering.item.PrismaticBlockItem;
import uk.co.dimensionalengineering.item.PrismaticWorkbenchItem;
import uk.co.dimensionalengineering.tile.PrismaticWorkbenchTileEntity;

import static uk.co.dimensionalengineering.DimensionalEngineering.MOD_ID;

public class RegistryHelper {

    private static final DeferredRegister<Block> BLOCKS;
    private static final DeferredRegister<Item> ITEMS;

    private static final DeferredRegister<TileEntityType<?>> TILES;
    private static final DeferredRegister<ContainerType<?>> CONTAINERS;


    public static final RegistryObject<Block> PRISMATIC_BLOCK;
    public static final RegistryObject<Block> PRISMATIC_WORKBENCH_BLOCK;

    public static final RegistryObject<Item> PRISMATIC_ITEM;
    public static final RegistryObject<Item> PRISMATIC_WORKBENCH_ITEM;

    public static final RegistryObject<ContainerType<PrismaticWorkbenchContainer>> PRISMATIC_WORKBENCH_CONTAINER;

    public static final RegistryObject<TileEntityType<PrismaticWorkbenchTileEntity>> PRISMATIC_WORKBENCH_TILE_ENTITY;

    static {
        //Get Register Singletons
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
        CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);
        TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

        //Register blocks
        PRISMATIC_BLOCK = BLOCKS.register(PrismaticBlock.ID, PrismaticBlock::new);
        PRISMATIC_WORKBENCH_BLOCK = BLOCKS.register(PrismaticWorkbenchBlock.ID, PrismaticWorkbenchBlock::new);

        //Register items
        PRISMATIC_ITEM = ITEMS.register(PrismaticBlockItem.ID, PrismaticBlockItem::new);
        PRISMATIC_WORKBENCH_ITEM = ITEMS.register(PrismaticWorkbenchItem.ID, PrismaticWorkbenchItem::new);

        //Register tiles
        PRISMATIC_WORKBENCH_TILE_ENTITY = TILES.register(PrismaticWorkbenchTileEntity.ID, () -> TileEntityType.Builder.create(PrismaticWorkbenchTileEntity::new, PRISMATIC_BLOCK.get()).build(null));

        //Register containers
        PRISMATIC_WORKBENCH_CONTAINER = CONTAINERS.register(PrismaticWorkbenchContainer.ID, () -> IForgeContainerType.create(((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            World world = inv.player.getEntityWorld();
            return new PrismaticWorkbenchContainer(windowId,world,pos,inv, inv.player);
        })));

    }

    public static DeferredRegister<Block> getBlocksRegistry() {
        return BLOCKS;
    }

    public static DeferredRegister<Item> getItemsRegistry() {
        return ITEMS;
    }

    public static DeferredRegister<TileEntityType<?>> getTileEntityRegistry() {
        return TILES;
    }

    public static DeferredRegister<ContainerType<?>> getContainerRegistry() {
        return CONTAINERS;
    }

    public static void addRegistriesToLoader() {
        getBlocksRegistry().register(FMLJavaModLoadingContext.get().getModEventBus());
        getItemsRegistry().register(FMLJavaModLoadingContext.get().getModEventBus());
        getContainerRegistry().register(FMLJavaModLoadingContext.get().getModEventBus());
        getTileEntityRegistry().register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
