package org.eu.hanana.reimu.hnn.neopatch;

import com.google.common.eventbus.EventBus;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.fabricmc.loader.impl.launch.FabricLauncherBase;
import org.eu.hanana.reimu.hnn.neopatch.config.ExampleCfgUi;
import org.eu.hanana.reimu.hnn.neopatch.config.NeoCfgCoreExample;
import org.eu.hanana.reimu.hnn.neopatch.schemes.SchemeTencent;
import org.eu.hanana.reimu.hnnapp.ModLoader;
import org.eu.hanana.reimu.hnnapp.mods.Event;
import org.eu.hanana.reimu.hnnapp.mods.ModEntry;
import org.eu.hanana.reimu.hnnapp.mods.events.AppEvent;
import org.eu.hanana.reimu.hnnapp.mods.events.PostInitModsEvent;
import org.eu.hanana.reimu.hnnapp.mods.events.RegNativesEvent;

import static org.eu.hanana.reimu.hnn.neopatch.ModMain.MOD_ID;

@ModEntry(id=MOD_ID,name = "旧加载器补丁")
public class ModMain {
    public final static String MOD_ID = "patcher_legacy";
    public ModMain(){
        ModLoader.getLoader().regEventBuses(this);
    }
    @Event
    public void onPostInitModsEvent(PostInitModsEvent event) {

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
