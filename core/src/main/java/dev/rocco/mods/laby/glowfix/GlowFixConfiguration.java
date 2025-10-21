package dev.rocco.mods.laby.glowfix;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingRequires;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.Color;

@ConfigName("settings")
public class GlowFixConfiguration extends AddonConfig {
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> glowSelf = new ConfigProperty<>(true);
  @SwitchSetting
  @SettingRequires(value = "glowSelf")
  private final ConfigProperty<Boolean> alwaysGlowSelf = new ConfigProperty<>(false);

  @SwitchSetting
  @SettingSection("colors")
  private final ConfigProperty<Boolean> useVanillaColors = new ConfigProperty<>(true);
  @SettingRequires(value = "useVanillaColors", invert = true)
  private final GlowColorConfig customColors = new GlowColorConfig();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public boolean getGlowSelf() {
    return glowSelf.get();
  }

  public boolean getAlwaysGlowSelf() {
    return alwaysGlowSelf.get();
  }

  public Color getGlowColor(char colorCode) {
    if (useVanillaColors.get()) {
      return null;
    }
    return customColors.getColor(colorCode);
  }
}
