package github.com.gengyoubo.la.mixin;

import com.mojang.brigadier.context.CommandContext;
import github.com.gengyoubo.la.La;
import moze_intel.projecte.config.CustomEMCParser;
import moze_intel.projecte.emc.EMCMappingHandler;
import moze_intel.projecte.network.PacketHandler;
import moze_intel.projecte.network.commands.RemoveEmcCMD;
import moze_intel.projecte.network.commands.ResetEmcCMD;
import moze_intel.projecte.network.commands.SetEmcCMD;
import moze_intel.projecte.network.commands.parser.NSSItemParser;
import moze_intel.projecte.utils.text.PELang;
import net.minecraft.commands.CommandRuntimeException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static moze_intel.projecte.emc.EMCMappingHandler.clearEmcMap;


@Mixin(SetEmcCMD.class)
class RewriteSetEmcCMD{
    @Unique
    private static ReloadableServerResources serverResources;
    @Unique
    private static ResourceManager resourceManager;
    @Inject(method = "setEmc", at = @At("HEAD"), cancellable = true, remap = false)
    private static void RewriteSetEmc(CommandContext<CommandSourceStack> ctx, NSSItemParser.NSSItemResult stack, long emc, CallbackInfoReturnable<Integer> cir) {
        String toSet = stack.getStringRepresentation();
        CustomEMCParser.addToFile(toSet, emc);
        ((CommandSourceStack) ctx.getSource()).sendSuccess(PELang.COMMAND_SET_SUCCESS.translate(new Object[]{toSet, emc}), true);

        // 将 `newCommand` 逻辑直接放到 Mixin 中
        // 调用新的命令逻辑
        if (La.config.server.loadEMC.get() == 1) {
            ((CommandSourceStack) ctx.getSource()).sendSuccess(new TranslatableComponent("pe.command.reload.notice.new"), true);
        }else if(La.config.server.loadEMC.get() == 2){
            CommandSourceStack source = ctx.getSource();
            source.sendSuccess(new TranslatableComponent("pe.command.reload.started"), true);

            // 执行指令逻辑
            clearEmcMap();
            CustomEMCParser.init();
            EMCMappingHandler.map(serverResources,resourceManager);

            source.sendSuccess(new TranslatableComponent("pe.command.reload.success"), true);

            // 发送更新包
            PacketHandler.sendFragmentedEmcPacketToAll();

        } else {
            ((CommandSourceStack) ctx.getSource()).sendSuccess(PELang.RELOAD_NOTICE.translate(new Object[0]), true);
        }

        cir.setReturnValue(0); // 防止继续执行原方法
    }
}

@Mixin(ResetEmcCMD.class)
class RewriteResetEmcCMD {
    @Unique
    private static ReloadableServerResources serverResources;
    @Unique
    private static ResourceManager resourceManager;

    @Inject(method = "resetEmc", at = @At("HEAD"), cancellable = true, remap = false)
    private static void RewriteResetEmc(CommandContext<CommandSourceStack> ctx, NSSItemParser.NSSItemResult stack, CallbackInfoReturnable<Integer> cir) {
        String toReset = stack.getStringRepresentation();

        if (CustomEMCParser.removeFromFile(toReset)) {
            ((CommandSourceStack) ctx.getSource()).sendSuccess(PELang.COMMAND_RESET_SUCCESS.translate(new Object[]{toReset}), true);

            // 调用新的命令逻辑
            if (La.config.server.loadEMC.get() == 1) {
                ((CommandSourceStack) ctx.getSource()).sendSuccess(new TranslatableComponent("pe.command.reload.notice.new"), true);
            }else if(La.config.server.loadEMC.get() == 2){
                CommandSourceStack source = ctx.getSource();
                source.sendSuccess(new TranslatableComponent("pe.command.reload.started"), true);

                // 执行指令逻辑
                clearEmcMap();
                CustomEMCParser.init();
                EMCMappingHandler.map(serverResources,resourceManager);

                source.sendSuccess(new TranslatableComponent("pe.command.reload.success"), true);

                // 发送更新包
                PacketHandler.sendFragmentedEmcPacketToAll();

            } else {
                ((CommandSourceStack) ctx.getSource()).sendSuccess(PELang.RELOAD_NOTICE.translate(new Object[0]), true);
            }
        } else {
            throw new CommandRuntimeException(PELang.COMMAND_INVALID_ITEM.translate(new Object[]{toReset}));
        }
        cir.setReturnValue(0); // 防止继续执行原方法
    }
}

@Mixin(RemoveEmcCMD.class)
class RewriteRemoveEmcCMD {
    @Unique
    private static ReloadableServerResources serverResources;
    @Unique
    private static ResourceManager resourceManager;
    @Inject(method = "removeEmc", at = @At("HEAD"), cancellable = true, remap = false)
    private static void RewriteRemoveEmc(CommandContext<CommandSourceStack> ctx, NSSItemParser.NSSItemResult stack, CallbackInfoReturnable<Integer> cir) {
        String toRemove = stack.getStringRepresentation();
        CustomEMCParser.addToFile(toRemove, 0L);
        ((CommandSourceStack) ctx.getSource()).sendSuccess(PELang.COMMAND_REMOVE_SUCCESS.translate(new Object[]{toRemove}), true);

        // 调用新的命令逻辑
        if (La.config.server.loadEMC.get() == 1) {
            ((CommandSourceStack) ctx.getSource()).sendSuccess(new TranslatableComponent("pe.command.reload.notice.new"), true);
        }else if(La.config.server.loadEMC.get() == 2){
            CommandSourceStack source = ctx.getSource();
            source.sendSuccess(new TranslatableComponent("pe.command.reload.started"), true);

            // 执行指令逻辑
            clearEmcMap();
            CustomEMCParser.init();
            EMCMappingHandler.map(serverResources,resourceManager);

            source.sendSuccess(new TranslatableComponent("pe.command.reload.success"), true);

            // 发送更新包
            PacketHandler.sendFragmentedEmcPacketToAll();

        } else {
            ((CommandSourceStack) ctx.getSource()).sendSuccess(PELang.RELOAD_NOTICE.translate(new Object[0]), true);
        }

        cir.setReturnValue(0); // 防止继续执行原方法
    }
}