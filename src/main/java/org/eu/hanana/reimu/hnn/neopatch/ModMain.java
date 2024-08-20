package org.eu.hanana.reimu.hnn.neopatch;

import org.eu.hanana.reimu.hnn.neopatch.schemes.SchemeTencent;
import org.eu.hanana.reimu.hnnapp.ModLoader;
import org.eu.hanana.reimu.hnnapp.mods.Event;
import org.eu.hanana.reimu.hnnapp.mods.ModEntry;
import org.eu.hanana.reimu.hnnapp.mods.events.AppEvent;
import org.eu.hanana.reimu.hnnapp.mods.events.RegNativesEvent;

import static org.eu.hanana.reimu.hnn.neopatch.ModMain.MOD_ID;

@ModEntry(id=MOD_ID,name = "旧加载器补丁")
public class ModMain {
    public final static String MOD_ID = "patcher_legacy";
    public ModMain(){
        ModLoader.getLoader().regEventBuses(this);
    }
    @Event
    public void addUrl(AppEvent.RegSchemes event){
        event.schemesRegisterHandler.addScheme(
                SchemeTencent.scheme,
                SchemeTencent.domain,
                SchemeTencent.class,
                true,
                true,
                false,
                true,
                true,
                true,
                true
        );
    }
}
