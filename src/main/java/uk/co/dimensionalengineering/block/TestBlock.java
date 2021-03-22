package uk.co.dimensionalengineering.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestBlock extends Block {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String ID = "testblock";

    public TestBlock(Properties properties) {
        super(properties);
        MinecraftForge.EVENT_BUS.register(new TestBlockBrokenEventHandler());
    }

    public static Properties generateProperties() {
        Properties properties = Properties.create(Material.ROCK, MaterialColor.STONE);
        properties.harvestTool(ToolType.PICKAXE);
        properties.hardnessAndResistance(1.0f, 6.0f);
        properties.harvestLevel(2);
        return properties;
    }

    public class TestBlockBrokenEventHandler {
        @SubscribeEvent
        public void breakBlock(BlockEvent.BreakEvent breakEvent) {
            if (breakEvent.getState().getBlock() instanceof TestBlock) {
                breakEvent.getPlayer().sendMessage(new StringTextComponent("Test Block broken by " + breakEvent.getPlayer().getScoreboardName() + " at " + breakEvent.getPos()), breakEvent.getPlayer().getUniqueID());
            }
        }
    }
}
