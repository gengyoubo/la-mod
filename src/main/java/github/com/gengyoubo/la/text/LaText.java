package github.com.gengyoubo.la.text;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import github.com.gengyoubo.la.La;
import net.minecraft.Util;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mod("la")
@Mod.EventBusSubscriber
public class LaText {
    private static final Boolean betaversion=true;
    @SubscribeEvent
    public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event){
        if(La.config.common.notifications.get()) {
            Player player = event.getPlayer();
            TranslatableComponent message = new TranslatableComponent("la.text1");
            message.setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.mcmod.cn/modpack/818.html")));
            player.sendMessage(message, Util.NIL_UUID);
            player.sendMessage(new TranslatableComponent("la,text2"), Util.NIL_UUID);
            player.sendMessage(new TranslatableComponent("la.text3"), Util.NIL_UUID);
            player.sendMessage(new TranslatableComponent("la.text4"), Util.NIL_UUID);
        }
    }
    private static final String nowVersion ="2.0.0";
    private static String readFromUrl(String urlString) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }
    @SubscribeEvent
    public static void nowVersion(PlayerEvent.PlayerLoggedInEvent event) {
        if (La.config.common.nowVersion.get()) {
            Player player = event.getPlayer();
            if(betaversion) {
                player.sendMessage(new TranslatableComponent("la.version.text4"), Util.NIL_UUID);
            }
            String urlString = "https://raw.githubusercontent.com/gengyoubo/LA/main/version/version.json";
            try {
                // 从 URL 读取 JSON 数据
                String jsonContent = readFromUrl(urlString);
                // 使用 JsonParser 解析 JSON
                JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
                JsonArray versionsArray = jsonObject.getAsJsonArray("versions");
                // 将版本号提取为字符串列表
                List<String> versionList = new ArrayList<>();
                for (int i = 0; i < versionsArray.size(); i++) {
                    JsonObject versionObject = versionsArray.get(i).getAsJsonObject();
                    versionList.add(versionObject.get("version").getAsString());
                }
                // 去重、排序并获取最新版本
                versionList = versionList.stream()
                        .distinct()
                        .sorted(LaText::compareVersions)
                        .collect(Collectors.toList());
                String latestVersion = versionList.get(versionList.size() - 1);
                    player.sendMessage(new TranslatableComponent("la.version.text3", nowVersion),Util.NIL_UUID);
                // 比较版本号
                if(!betaversion) {
                    if (compareVersions(nowVersion, latestVersion) == 0) {
                        // 当前为最新版
                        player.sendMessage(new TranslatableComponent("la.version.text1"), Util.NIL_UUID);
                    } else {
                        // 提示玩家更新版本，并显示最新版本号
                        TranslatableComponent message = new TranslatableComponent("la.version.text2", latestVersion);
                        player.sendMessage(message, Util.NIL_UUID);
                    }
                }
            } catch (Exception e) {
                // 记录错误并向玩家发送错误信息
                La.LOGGER.error("Error reading JSON: {}", e.getMessage(), e);
            }
        }
    }

    private static int compareVersions(String v1, String v2) {
        String[] parts1 = v1.split("\\.");
        String[] parts2 = v2.split("\\.");
        for (int i = 0; i < Math.max(parts1.length, parts2.length); i++) {
            int num1 = i < parts1.length ? Integer.parseInt(parts1[i]) : 0;
            int num2 = i < parts2.length ? Integer.parseInt(parts2[i]) : 0;
            if (num1 != num2) {
                return Integer.compare(num1, num2);
            }
        }
        return 0;
    }
}
