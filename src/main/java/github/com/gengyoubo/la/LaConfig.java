package github.com.gengyoubo.la;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class LaConfig {

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> notifications;
        public final ForgeConfigSpec.ConfigValue<Boolean> nowVersion;
        public Common(ForgeConfigSpec.Builder builder){
            builder.comment("If turned off, no messages will be displayed when entering the game");
            notifications = builder.define("notifications", false);
            builder.comment("When turned on, it will automatically get whether there is a latest version or not");
            nowVersion = builder.define("nowVersion", true);
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