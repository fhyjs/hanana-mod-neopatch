package org.eu.hanana.reimu.hnn.neopatch.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import org.eu.hanana.reimu.hnn.neopatch.ModFabric;
import org.eu.hanana.reimu.hnnapp.Datas;
import org.eu.hanana.reimu.hnnapp.core.JNA;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JNA.class)
public class MixinJNA {
    @Inject(at=@At("HEAD"),cancellable = true,method = {"init"})
    private static void init(CallbackInfo ci){
        if (Datas.args.contains("/no-jna")) {
            Log.info(ModFabric.logCategory,"Canceling JNA loading.");
            ci.cancel();
        }
    }
}
