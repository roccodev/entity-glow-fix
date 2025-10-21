package dev.rocco.mods.laby.glowfix.v1_8_9;

import dev.rocco.mods.laby.glowfix.GlowEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class GlowFilter {
  public static boolean isGlowing(Entity entity) {
    boolean self = entity == Minecraft.getMinecraft().getRenderViewEntity();
    if (self && !GlowEntityRegistry.INSTANCE.getGlowSelf()) {
      return false;
    }
    if (self && GlowEntityRegistry.INSTANCE.getAlwaysGlowSelf()) {
      return true;
    }
    // Glowing data watcher flag from 1.9+
    return (entity.getDataWatcher().getWatchableObjectByte(0) & (1 << 6)) != 0;
  }
}
