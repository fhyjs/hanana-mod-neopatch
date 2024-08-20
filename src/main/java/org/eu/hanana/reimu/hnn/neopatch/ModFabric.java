package org.eu.hanana.reimu.hnn.neopatch;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.Person;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import org.eu.hanana.reimu.hnnapp.mods.Mod;
import org.eu.hanana.reimu.hnnapp.mods.interal.mod.ModData;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModFabric implements ModInitializer {
    public static final List<Mod> neoMods = new ArrayList<>();
    public static final LogCategory logCategory = new LogCategory("App Patcher");
    public static Unsafe theUnsafe;
    @Override
    public void onInitialize() {
        Log.info(logCategory,"Patcher is loaded!");
        Field theUnsafe1 = null;
        try {
            theUnsafe1 = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe1.setAccessible(true);
            theUnsafe = (Unsafe) theUnsafe1.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        for (ModContainer allMod : FabricLoader.getInstance().getAllMods()) {
            Mod mod = new Mod();
            mod.id = allMod.getMetadata().getId();
            mod.name = allMod.getMetadata().getName();
            ModData md = null;
            try {
                md = (ModData) theUnsafe.allocateInstance(ModData.class);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
            md.id= mod.id;
            md.description=allMod.getMetadata().getDescription();
            var authors = allMod.getMetadata().getAuthors();
            var authorsStrList = new ArrayList<String>();
            for (Person author : authors) {
                authorsStrList.add(author.getName());
            }
            md.author=authorsStrList;
            try {
                md.version=Integer.parseInt(allMod.getMetadata().getVersion().getFriendlyString().substring(0,0));
            }catch (NumberFormatException ignored){
                md.version=-1;
            }
            if (md.author.isEmpty()){
                md.author=null;
            }
            mod.modData = md;

            neoMods.add(mod);
        }

    }
    public static boolean isNeoMod(Mod mod){
        return neoMods.contains(mod);
    }
}
