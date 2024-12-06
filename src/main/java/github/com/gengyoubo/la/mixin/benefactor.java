package github.com.gengyoubo.la.mixin;

import github.com.gengyoubo.la.La;
import net.ltxprogrammer.changed.util.PatreonBenefits;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PatreonBenefits.class)
public class benefactor {
    @Shadow(remap = false)
    private static String REPO_BASE = "https://raw.githubusercontent.com/LtxProgrammer/patreon-benefits/main/";
    @Shadow(remap = false)
    private static String LINKS_DOCUMENT;
    @Shadow(remap = false)
    private static String VERSION_DOCUMENT;
    @Shadow(remap = false)
    private static String FORMS_DOCUMENT;
    @Shadow(remap = false)
    private static String FORMS_BASE;
    @Inject(method = "updatePathStrings", at = @At("TAIL"),remap = false)
    private static void PatreonBenefits(CallbackInfo ci) {
        if(La.config.server.openModPackSpecialForm.get()) {
            REPO_BASE = "https://raw.githubusercontent.com/gengyoubo/LA/main/benefactor/";
            System.out.println(REPO_BASE);
            LINKS_DOCUMENT = REPO_BASE + "listing.json";
            System.out.println(LINKS_DOCUMENT);
            VERSION_DOCUMENT = REPO_BASE + "version.txt";
            System.out.println(VERSION_DOCUMENT);
            FORMS_DOCUMENT = REPO_BASE + "forms/index.json";
            System.out.println(FORMS_DOCUMENT);
            FORMS_BASE = REPO_BASE + "forms/";
            System.out.println(FORMS_BASE);
        }
    }
}
