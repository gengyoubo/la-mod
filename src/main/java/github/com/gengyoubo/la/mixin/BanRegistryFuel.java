package github.com.gengyoubo.la.mixin;

import cool.furry.mc.forge.projectexpansion.util.Fuel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Fuel.class)
public class BanRegistryFuel {
    @Inject(method = "register", at = @At("HEAD"), cancellable = true,remap = false)
    private void onRegister(CallbackInfo ci) {
        ci.cancel();
    }
}