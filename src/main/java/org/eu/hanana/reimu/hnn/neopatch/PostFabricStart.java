package org.eu.hanana.reimu.hnn.neopatch;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.impl.util.log.Log;

public class PostFabricStart implements ModInitializer {
    @Override
    public void onInitialize() {
        Log.info(ModFabric.logCategory,"PreModInitializing");
    }
}
