package dev.rocco.mods.laby.glowfix;


import net.labymod.api.util.Color;

public class GlowEntityRegistry {
  public static final GlowEntityRegistry INSTANCE = new GlowEntityRegistry();

  private boolean glowingBusy;

  public boolean isGlowingEnabled() {
    return glowingBusy && GlowFixAddon.INSTANCE != null && GlowFixAddon.INSTANCE.configuration().enabled().get();
  }

  public boolean getGlowSelf() {
    return GlowFixAddon.INSTANCE != null && GlowFixAddon.INSTANCE.configuration().getGlowSelf();
  }

  public boolean getAlwaysGlowSelf() {
    return GlowFixAddon.INSTANCE != null && GlowFixAddon.INSTANCE.configuration().getAlwaysGlowSelf();
  }

  public Color getGlowColor(char colorCode) {
    return GlowFixAddon.INSTANCE != null ? GlowFixAddon.INSTANCE.configuration().getGlowColor(colorCode) : null;
  }

  public void setGlowingBusy(boolean glowingBusy) {
    this.glowingBusy = glowingBusy;
  }
}
