package org.eu.hanana.reimu.hnn.neopatch.config;

import org.eu.hanana.reimu.hnnapp.mods.CfgCoreBase;

public class NeoCfgCoreExample extends CfgCoreBase {
    @Override
    public void init() {
        addCfgClass(ExampleCfg1.class);
    }
    public static class ExampleCfg1{
        public static int anInt = 1234567890;
        public static boolean aBoolean = true;
        public static String aString = "Example String";
        public static byte aByte = 127;
        public static float aFloat = 1234.5678f;
        public static double aDouble = 1234.5678d;
        public static long aLong = 1234567890;
    }
}
