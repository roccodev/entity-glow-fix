package dev.rocco.mods.laby.glowfix;


public class GlowEntityRegistry {
  public static final GlowEntityRegistry INSTANCE = new GlowEntityRegistry();

  private boolean glowingBusy;

  public boolean isGlowingEnabled() {
    return glowingBusy && GlowFixAddon.INSTANCE != null && GlowFixAddon.INSTANCE.configuration().enabled().get();
  }

  public boolean getGlowSelf() {
    return GlowFixAddon.INSTANCE != null && GlowFixAddon.INSTANCE.configuration().getGlowSelf();
  }

  public void setGlowingBusy(boolean glowingBusy) {
    this.glowingBusy = glowingBusy;
  }
}
