package org.eu.hanana.reimu.hnn.neopatch.mixin;

import org.eu.hanana.reimu.hnn.neopatch.ModFabric;
import org.eu.hanana.reimu.hnnapp.detailed.dialog.ModManDialog;
import org.eu.hanana.reimu.hnnapp.mods.Mod;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Mixin(ModManDialog.class)
public class MixinModManager extends JDialog {
    @Shadow
    DefaultListModel<ModManDialog.ListComponent> listModel = new DefaultListModel<>();
    @Shadow private JList list1;

    @Inject(remap = false,method = "<init>",at=@At("RETURN"))
    public void init(Window owner, CallbackInfo ci){
        this.setTitle(this.getTitle()+" | NeoLoader提供");
        var toRemove = new ArrayList<ModManDialog.ListComponent>();
        for (int i = 0; i < listModel.size(); i++) {
            var item = listModel.get(i);
            if (ModFabric.getParent(item.mod())!=null){
                toRemove.add(item);
            }
        }
        for (ModManDialog.ListComponent listComponent : toRemove) {
            listModel.removeElement(listComponent);
        }
    }
    @Mixin(ModManDialog.ListComponent.class)
    public static class ListComponent{
        @Shadow @Final private Mod mod;

        @Inject(method = "toString",at = @At("RETURN"),remap = false,cancellable = true)
        public void toString(CallbackInfoReturnable<String> callbackInfoReturnable){
            String returnValue = callbackInfoReturnable.getReturnValue();
            callbackInfoReturnable.setReturnValue((ModFabric.isNeoMod(mod)?"[N]":"[L]")+returnValue);
        }
        @Inject(method = "getScrText",at = @At("RETURN"),remap = false,cancellable = true)
        public void getScrText(CallbackInfoReturnable<String> callbackInfoReturnable){
            String returnValue = callbackInfoReturnable.getReturnValue();
            callbackInfoReturnable.setReturnValue(returnValue+ (ModFabric.isNeoMod(mod)?"""
                    ======
                    使用新加载器加载
                    """:
                    """
                    ======
                    使用旧加载器加载
                    """
            ));
        }
    }
}
