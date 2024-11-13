package com.examplemixin;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExampleMixin.MOD_ID)
public class ExampleMixin {
    // Mod Info
    public static final String MOD_ID = "examplemixin";
    public static final String MOD_NAME = "ExampleMixin";
    public static final String MOD_VERSION = "0.1f";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent init){}

    @Mod.EventHandler
    public void init(FMLInitializationEvent init){}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent init){}
}