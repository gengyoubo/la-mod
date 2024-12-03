package github.com.gengyoubo.la.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.ltxprogrammer.changed.Changed;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.ltxprogrammer.changed.util.PatreonBenefits;
import net.ltxprogrammer.changed.util.UniversalDist;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(PatreonBenefits.class)
public class benefactor {
    /*
    @Shadow private static final Logger LOGGER;
    @Unique private static final List<String> repoBases;
    static {
        List<String> tempList = new ArrayList<>();
        tempList.add("https://raw.githubusercontent.com/LtxProgrammer/patreon-benefits/main/");
        tempList.add("https://raw.githubusercontent.com/gengyoubo/LA/main/benefactor/");
        repoBases = Collections.unmodifiableList(tempList);
        UPDATE_FLAG = new HashMap();
        CACHED_LEVELS = new HashMap();
        CACHED_SPECIAL_FORMS = new HashMap();
        LOGGER = LogManager.getLogger(Changed.class);
    }
    @Unique private static List<String> getRepoBases() {
        return repoBases;
    }
    @Shadow private static final Map<Dist, AtomicBoolean> UPDATE_FLAG;
    @Shadow private static void updatePathStrings() {
        REPO_BASE = "https://" + (String) Changed.config.common.githubDomain.get() + "/LtxProgrammer/patreon-benefits/main/";
        LINKS_DOCUMENT = REPO_BASE + "listing.json";
        VERSION_DOCUMENT = REPO_BASE + "version.txt";
        FORMS_DOCUMENT = REPO_BASE + "forms/index.json";
        FORMS_BASE = REPO_BASE + "forms/";
    }
    @Shadow private static String REPO_BASE = "https://raw.githubusercontent.com/LtxProgrammer/patreon-benefits/main/";
    @Shadow private static String LINKS_DOCUMENT;
    @Shadow private static String VERSION_DOCUMENT;
    @Shadow private static String FORMS_DOCUMENT;
    @Shadow private static String FORMS_BASE;
    @Shadow private static final Map<UUID, PatreonBenefits.SpecialForm> CACHED_SPECIAL_FORMS;
    @Shadow private static final Map<UUID, PatreonBenefits.Tier> CACHED_LEVELS;

    @Shadow
    public static void loadSpecialForms(HttpClient client) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(FORMS_DOCUMENT)).GET().build();
        JsonElement json = JsonParser.parseString((String)client.send(request, HttpResponse.BodyHandlers.ofString()).body());
        JsonArray formLocations = json.getAsJsonObject().get("forms").getAsJsonArray();
        AtomicInteger count = new AtomicInteger(0);
        ChangedRegistry.TRANSFUR_VARIANT.get().unfreeze();
        formLocations.forEach((element) -> {
            JsonObject object = element.getAsJsonObject();
            if (GsonHelper.getAsInt(object, "version", 1) <= 3) {
                PatreonBenefits.SpecialForm form = PatreonBenefits.SpecialForm.fromJSON((str) -> {
                    try {
                        return JsonParser.parseString((String)client.send(HttpRequest.newBuilder(URI.create(FORMS_BASE + str)).GET().build(), HttpResponse.BodyHandlers.ofString()).body()).getAsJsonObject();
                    } catch (Exception var3) {
                        Exception e = var3;
                        e.printStackTrace();
                        return new JsonObject();
                    }
                }, object);
                CACHED_SPECIAL_FORMS.put(form.playerUUID(), form);
                TransfurVariant.registerSpecial(form.variant());
                ChangedRegistry.TRANSFUR_VARIANT.get().register(form.variant());
                count.getAndIncrement();
            }
        });
        ChangedRegistry.TRANSFUR_VARIANT.get().freeze();
        LOGGER.info("Updated {} patreon special forms", count.get());
    }
    @Inject(method = "checkForUpdates", at = @At(value = "HEAD"),cancellable = true)
    private static void checkForUpdatesRedirect(CallbackInfoReturnable<Boolean> cir) throws IOException,InterruptedException{
        if (((AtomicBoolean)UPDATE_FLAG.computeIfAbsent(FMLEnvironment.dist, (dist) -> {
            return new AtomicBoolean(false);
        })).get()) {
            ((AtomicBoolean)UPDATE_FLAG.get(FMLEnvironment.dist)).set(false);
            updatePathStrings();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(LINKS_DOCUMENT)).GET().build();
            JsonElement json = JsonParser.parseString((String)client.send(request, HttpResponse.BodyHandlers.ofString()).body());
            JsonArray links = json.getAsJsonObject().get("players").getAsJsonArray();
            CACHED_LEVELS.clear();
            links.forEach((element) -> {
                JsonObject object = element.getAsJsonObject();
                CACHED_LEVELS.put(UUID.fromString(object.get("uuid").getAsString()), PatreonBenefits.Tier.ofValue(object.get("tier").getAsInt()));
            });
            loadSpecialForms(client);
            UniversalDist.displayClientMessage(new TextComponent("Updated Patreon Data."), false);
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(false);
        }
        cir.setReturnValue(false);
    }
    @Shadow public static int currentVersion;

    @Inject(method = "loadBenefits",at=@At(value = "HEAD"))
    private static void loadBenefits(CallbackInfo ci) {
        updatePathStrings();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(LINKS_DOCUMENT)).GET().build();
        try {
            JsonElement json = JsonParser.parseString((String)client.send(request, HttpResponse.BodyHandlers.ofString()).body());
            JsonArray links = json.getAsJsonObject().get("players").getAsJsonArray();
            links.forEach((element) -> {
                JsonObject object = element.getAsJsonObject();
                CACHED_LEVELS.put(UUID.fromString(object.get("uuid").getAsString()), PatreonBenefits.Tier.ofValue(object.get("tier").getAsInt()));
            });
        } catch (Exception var6) {
            LOGGER.error("Encountered error while fetching patronage levels");
        }
        try {
            loadSpecialForms(client);
        } catch (Exception var5) {
            LOGGER.error("Encountered error while loading special forms");
        }
        try {
            request = HttpRequest.newBuilder(URI.create(VERSION_DOCUMENT)).GET().build();
            currentVersion = Integer.parseInt(((String)client.send(request, HttpResponse.BodyHandlers.ofString()).body()).replace("\n", ""));
        } catch (Exception var4) {
            LOGGER.error("Encountered error while fetching patron data version");
        }
        ci.cancel();
    }
    */
}