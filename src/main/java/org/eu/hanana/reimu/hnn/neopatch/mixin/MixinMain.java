package org.eu.hanana.reimu.hnn.neoloader.mixin;

import org.eu.hanana.reimu.hnnapp.FabricMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FabricMain.class)
public class MixinMain {
    @Inject(method = "main",remap = false,at=@At("HEAD"))
    private static void main(String[] args, CallbackInfo callbackInfo){
        System.out.println("Launching HANANA App with NeoLoader!!");
    }
}
