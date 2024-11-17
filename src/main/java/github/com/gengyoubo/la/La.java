package github.com.gengyoubo.la;

import com.mojang.logging.LogUtils;
import github.com.gengyoubo.la.init.Blocks;
import github.com.gengyoubo.la.init.BurnTime;
import github.com.gengyoubo.la.init.Items;
import github.com.gengyoubo.la.network.commands.ReloadEmcCMD;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("la")
public class La {
    public static LaConfig config;

    private static final Logger LOGGER = LogUtils.getLogger();
    public La(){
        config = new LaConfig(ModLoadingContext.get());
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Items.REGISTRY.register(bus);
        Blocks.REGISTRY.register(bus);
        MinecraftForge.EVENT_BUS.register(new BurnTime());
    }

    @SubscribeEvent
    public void onServerStarting(RegisterCommandsEvent event) {
        ReloadEmcCMD.register(event.getDispatcher());
    }

}
