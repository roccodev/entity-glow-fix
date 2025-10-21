package dev.rocco.mods.laby.glowfix;

import net.labymod.api.client.gui.lss.property.annotation.AutoWidget;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.Setting;
import net.labymod.api.configuration.settings.accessor.SettingAccessor;
import net.labymod.api.configuration.settings.annotation.SettingElement;
import net.labymod.api.configuration.settings.annotation.SettingFactory;
import net.labymod.api.configuration.settings.annotation.SettingWidget;
import net.labymod.api.configuration.settings.widget.WidgetFactory;
import net.labymod.api.util.Color;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@AutoWidget
@SettingWidget
public class ResettableColor extends HorizontalListWidget {
  private final ConfigProperty<Color> property;

  public ResettableColor(ConfigProperty<Color> property) {
    this.property = property;
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    spaceBetweenEntries().set(10);

    ColorPickerWidget colorPicker = ColorPickerWidget.of(property);
    this.addEntry(colorPicker);

    ButtonWidget resetButton = ButtonWidget.i18n("glowfix.ui.reset");
    resetButton.setScale(0.9f);
    resetButton.setPressListener(() -> {
      property.reset();
      return true;
    });
    this.addEntry(resetButton);
  }

  @SettingElement
  @Target({ ElementType.FIELD })
  @Retention(RetentionPolicy.RUNTIME)
  public @interface ResettableColorSetting {

  }

  @SettingFactory
  public static class Factory implements WidgetFactory<ResettableColorSetting, ResettableColor> {

    @Override
    public Class<?>[] types() {
      return new Class[] { Color.class };
    }

    @Override
    public ResettableColor[] create(Setting setting, ResettableColorSetting annotation, SettingAccessor accessor) {
      return new ResettableColor[] { new ResettableColor(accessor.property()) };
    }
  }
}
