package dev.rocco.mods.laby.glowfix.v1_8_9.mixins;

import dev.rocco.mods.laby.glowfix.GlowEntityRegistry;
import net.labymod.api.util.Color;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RendererLivingEntity.class)
public abstract class MixinGlowColor {
  @Redirect(method = "setScoreTeamColor", at = @At(value = "INVOKE", target = "net/minecraft/client/gui/FontRenderer.getColorCode(C)I"))
  public int getColor(FontRenderer instance, char colorCode) {
    Color customColor = GlowEntityRegistry.INSTANCE.getGlowColor(colorCode);
    if (customColor != null) {
      return customColor.getRed() << 16 | customColor.getGreen() << 8 | customColor.getBlue();
    }
    return instance.getColorCode(colorCode);
  }

  @ModifyConstant(method = "setScoreTeamColor", constant = @Constant(intValue = 0xFFFFFF))
  public int getDefaultColor(int oldValue) {
    Color customColor = GlowEntityRegistry.INSTANCE.getGlowColor('f');
    if (customColor != null) {
      return customColor.getRed() << 16 | customColor.getGreen() << 8 | customColor.getBlue();
    }
    return oldValue;
  }
}
