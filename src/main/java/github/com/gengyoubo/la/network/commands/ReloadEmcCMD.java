package github.com.gengyoubo.la.network.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import github.com.gengyoubo.la.La;
import moze_intel.projecte.config.CustomEMCParser;
import moze_intel.projecte.emc.EMCMappingHandler;
import moze_intel.projecte.network.PacketHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.ResourceManager;

import static moze_intel.projecte.emc.EMCMappingHandler.clearEmcMap;


public class ReloadEmcCMD implements Command<CommandSourceStack> {

    public ReloadableServerResources serverResources;
    public ResourceManager resourceManager;

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        if(!(La.config.server.loadEMC.get()==2)){
            CommandSourceStack source = context.getSource();
            source.sendSuccess(new TranslatableComponent("pe.command.reload.started"), true);

            // 执行指令逻辑
            clearEmcMap();
            CustomEMCParser.init();
            EMCMappingHandler.map(serverResources, resourceManager);

            source.sendSuccess(new TranslatableComponent("pe.command.reload.success"), true);

            // 发送更新包
            PacketHandler.sendFragmentedEmcPacketToAll();

        }
        return 1;
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        if(!(La.config.server.loadEMC.get()==2)) {
            dispatcher.register(
                    Commands.literal("projecte")  // 添加 /projecte 路径
                            .then(Commands.literal("reloadEMC")  // 然后添加 reloadEMC 子命令
                                    .requires(source -> source.hasPermission(4))  // 设置权限等级为4
                                    .executes(new ReloadEmcCMD()))  // 执行命令的逻辑
            );
        }
    }
}
