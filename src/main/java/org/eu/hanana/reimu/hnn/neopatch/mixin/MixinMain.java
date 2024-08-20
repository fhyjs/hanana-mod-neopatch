package org.eu.hanana.reimu.hnn.neopatch.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import org.eu.hanana.reimu.hnn.neopatch.ModFabric;
import org.eu.hanana.reimu.hnnapp.FabricMain;
import org.eu.hanana.reimu.hnnapp.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.URL;

@Mixin(Main.class)
public class MixinMain {
    @Inject(method = "run",remap = false,at=@At("HEAD"))
    public void run(CallbackInfo ci){
        Log.info(ModFabric.logCategory,"Launching HANANA App with NeoLoader!!");
    }
}
