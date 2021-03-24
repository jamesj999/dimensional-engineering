package uk.co.dimensionalengineering.helper;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import uk.co.dimensionalengineering.DimensionalEngineering;
import uk.co.dimensionalengineering.screen.PrismaticWorkbenchScreen;

@Mod.EventBusSubscriber(modid = DimensionalEngineering.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistryHelper {

    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(RegistryHelper.PRISMATIC_WORKBENCH_CONTAINER.get(), PrismaticWorkbenchScreen::new);
    }

}
