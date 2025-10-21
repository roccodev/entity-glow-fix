package dev.rocco.mods.laby.glowfix;

import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.Exclude;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.util.Color;

public class GlowColorConfig extends Config {
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> black = new ConfigProperty<>(Color.BLACK);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> darkBlue = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> darkGreen = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> darkAqua = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> darkRed = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> darkPurple = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> gold = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> gray = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> darkGray = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> blue = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> green = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> aqua = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> red = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> lightPurple = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> yellow = new ConfigProperty<>(Color.WHITE);
  @ColorPickerWidget.ColorPickerSetting
  private final ConfigProperty<Color> white = new ConfigProperty<>(Color.WHITE);

  @Exclude
  private transient final ConfigProperty[] ALL_COLORS = {
          black,
          darkBlue,
          darkGreen,
          darkAqua,
          darkRed,
          darkPurple,
          gold,
          gray,
          darkGray,
          blue,
          green,
          aqua,
          red,
          lightPurple,
          yellow,
          white
  };

  GlowColorConfig() {
    // Init default colors
    for(int colorCode = 0; colorCode < ALL_COLORS.length; colorCode++) {
      int x = (colorCode >> 3 & 1) * 85;
      int r = (colorCode >> 2 & 1) * 170 + x;
      int g = (colorCode >> 1 & 1) * 170 + x;
      int b = (colorCode & 1) * 170 + x;
      if (colorCode == 6) {
        r += 85;
      }
      Color color = Color.ofRGB(r & 255, g & 255, b & 255);
      ConfigProperty<Color> property = ALL_COLORS[colorCode];
      property.updateDefaultValue(color);
      property.set(color);
    }
  }

  public Color getColor(char colorCode) {
    int index = "0123456789abcdef".indexOf(colorCode);
    return index >= 0 ? (Color) ALL_COLORS[index].get() : null;
  }
}
