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
import org.eu.hanana.reimu.hnnapp.mods.events.BrowserLoadEvent;
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
    @Event
    public void onWebLoadEnd(BrowserLoadEvent.End event){
        // 创建 JavaScript 代码，向 <head> 添加 <script> 标签
        String jsCode =
                "var script = document.createElement('script');" +
                        "script.type = 'text/javascript';" +
                        "script.src = 'https://unpkg.com/@ruffle-rs/ruffle';" + // 外部脚本地址
                        "document.head.appendChild(script);";

        // 在主框架中执行 JavaScript
        event.cefBrowser.getMainFrame().executeJavaScript(jsCode, event.cefBrowser.getMainFrame().getURL(), 0);
    }
}
