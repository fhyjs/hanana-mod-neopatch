package org.eu.hanana.reimu.hnn.neopatch.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import org.eu.hanana.reimu.hnn.neopatch.ModFabric;
import org.eu.hanana.reimu.hnnapp.Datas;
import org.eu.hanana.reimu.hnnapp.mods.Mod;
import org.eu.hanana.reimu.hnnapp.mods.events.UpdateEvent;
import org.eu.hanana.reimu.hnnapp.mods.interal.ICanRun;
import org.eu.hanana.reimu.hnnrunmanmod.MainDialog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

@Mixin(MainDialog.class)
public abstract class MixinRunManModDialog extends JDialog{
    @Shadow private JList list1;
    @Inject(at=@At("RETURN"),method = {"<init>"})
    private void init(CallbackInfo ci){
        this.setTitle(getTitle()+"--漏洞已修复(NeoPatch)");
    }
    @Inject(at=@At("HEAD"),cancellable = true,method = {"runn"})
    private void runn(CallbackInfo ci) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object selectedValue = list1.getSelectedValue();
        if (selectedValue==null) {
            ci.cancel();
            return;
        }
        Class<?> aClass = Class.forName("org.eu.hanana.reimu.hnnrunmanmod.MainDialog$ModW");
        Mod mod = (Mod) aClass.getMethod("mod").invoke(selectedValue);
        new Thread(()->{
            Log.info(ModFabric.logCategory,"Running mod %s by NeoPatch...",mod.id);
            ((ICanRun) mod.INSTANCE).onlyRunThis();
        }).start();
        ci.cancel();
    }
}
