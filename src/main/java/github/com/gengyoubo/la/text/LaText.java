package github.com.gengyoubo.la.text;

import net.minecraft.Util;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("la")
@Mod.EventBusSubscriber
public class LaText {

    @SubscribeEvent
    public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event){
        Player player = event.getPlayer();
        TranslatableComponent message = new TranslatableComponent("la.text1");
        message.setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.mcmod.cn/modpack/818.html")));
        player.sendMessage(message, Util.NIL_UUID);
        player.sendMessage(new TranslatableComponent("la,text2"), Util.NIL_UUID);
        player.sendMessage(new TranslatableComponent("la.text3"), Util.NIL_UUID);
        player.sendMessage(new TranslatableComponent("la.text4"), Util.NIL_UUID);
    }
}
