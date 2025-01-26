package org.eu.hanana.reimu.hnn.neopatch.mixin;

import org.checkerframework.checker.units.qual.A;
import org.eu.hanana.reimu.hnnapp.window.AboutHananaApp;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.*;
import java.awt.*;

@Mixin(AboutHananaApp.class)
public class MixinAboutApp {
    @Shadow private JTextArea textInfo;

    @Inject(method = {"<init>"},at = @At("RETURN"))
    public void init(Window owner, CallbackInfo ci){
        this.textInfo.append("\n  FabricLoader\n  NeoPatch");
    }
}
