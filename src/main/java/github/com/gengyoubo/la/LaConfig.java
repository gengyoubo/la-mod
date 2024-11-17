package github.com.gengyoubo.la;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class LaConfig {

    public static class Common {

        public Common(ForgeConfigSpec.Builder builder){

        }

    }
    public static class Client {
        public Client(ForgeConfigSpec.Builder builder){

        }
    }
    public static class Server {
        public final ForgeConfigSpec.ConfigValue<Integer> loadEMC;
        public Server(ForgeConfigSpec.Builder builder){
            builder.comment("Enable reloadEMC command (0 will use the original method, 1 will enable reloadEM, command, 2 will automatically reloadEMC when using moveEMC, setEMC, resetEMC)");
            loadEMC = builder.defineInRange("loadEMC", 1,0,2);
        }
    }
    private final Pair<Common, ForgeConfigSpec> commonPair;
    private final Pair<Client, ForgeConfigSpec> clientPair;
    private final Pair<Server, ForgeConfigSpec> serverPair;
    public final Common common;
    public final Client client;
    public final Server server;

    public LaConfig(ModLoadingContext context) {
        commonPair = new ForgeConfigSpec.Builder()
                .configure(Common::new);
        clientPair = new ForgeConfigSpec.Builder()
                .configure(Client::new);
        serverPair = new ForgeConfigSpec.Builder()
                .configure(Server::new);

        context.registerConfig(ModConfig.Type.COMMON, commonPair.getRight());
        context.registerConfig(ModConfig.Type.CLIENT, clientPair.getRight());
        context.registerConfig(ModConfig.Type.SERVER, serverPair.getRight());
        common = commonPair.getLeft();
        client = clientPair.getLeft();
        server = serverPair.getLeft();
    }
}