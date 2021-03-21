package jamesj999.minecrafttest.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestBlock extends Block {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String ID = "test";

    public TestBlock(Properties properties) {
        super(properties);
        MinecraftForge.EVENT_BUS.register(new TestBlockBrokenEventHandler());
    }

    public static Properties generateProperties() {
        Properties properties = Properties.create(Material.ROCK, MaterialColor.STONE);
        properties.setRequiresTool();
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

    public class TestBlockInteractEventHandler {
        @SubscribeEvent
        public void blockInteract(BlockEvent.BlockToolInteractEvent interactEvent) {
            if (interactEvent.getState().getBlock() instanceof TestBlock) {
                interactEvent.getPlayer().sendMessage(new StringTextComponent(ID), interactEvent.getPlayer().getUniqueID());
                if (ToolType.HOE.equals(interactEvent.getToolType())) {
                    interactEvent.getPlayer().sendMessage(new StringTextComponent("Don't HOE me bro"), interactEvent.getPlayer().getUniqueID());
                    interactEvent.setResult(Event.Result.ALLOW);
                } else {
                    interactEvent.getPlayer().applyKnockback(10f, 10d, 10d);
                    interactEvent.setResult(Event.Result.DENY);
                }
            }
        }
    }
}
