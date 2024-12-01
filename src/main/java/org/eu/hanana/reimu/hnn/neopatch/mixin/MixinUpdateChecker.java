package org.eu.hanana.reimu.hnn.neopatch.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import org.eu.hanana.reimu.hnn.neopatch.ModFabric;
import org.eu.hanana.reimu.hnnapp.Datas;
import org.eu.hanana.reimu.hnnapp.mods.events.UpdateEvent;
import org.eu.hanana.reimu.hnnapp.mods.interal.UpdateChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(UpdateChecker.class)
public class MixinUpdateChecker {
    @Inject(at=@At("HEAD"),cancellable = true,method = {"onCheckUpdate"})
    private void onCheckUpdate(UpdateEvent.CheckUpdate event,CallbackInfo ci){
        if (Datas.args.contains("/no-auto-update")&&event.auto) {
            Log.info(ModFabric.logCategory,"Canceling auto update checking.");
            ci.cancel();
        }
    }
}
