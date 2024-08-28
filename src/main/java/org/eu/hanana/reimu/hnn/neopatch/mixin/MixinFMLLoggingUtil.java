package org.eu.hanana.reimu.hnn.neopatch.mixin;

import net.fabricmc.loader.impl.util.LoaderUtil;
import net.fabricmc.loader.impl.util.log.Log;
import org.eu.hanana.reimu.hnn.neopatch.ModFabric;
import org.eu.hanana.reimu.hnnapp.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoaderUtil.class)
public class MixinFMLLoggingUtil {
}
