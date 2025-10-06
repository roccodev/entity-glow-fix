package dev.rocco.mods.laby.glowfix.v1_8_9.mixins;

import dev.rocco.mods.laby.glowfix.GlowEntityRegistry;
import dev.rocco.mods.laby.glowfix.v1_8_9.GlowFilter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {
  @Shadow
  private Framebuffer entityOutlineFramebuffer;
  @Shadow
  private ShaderGroup entityOutlineShader;

  @Inject(method = "isRenderEntityOutlines", at = @At("HEAD"), cancellable = true)
  public void isRenderEntityOutlines(CallbackInfoReturnable<Boolean> ci) {
    if (this.entityOutlineFramebuffer != null && this.entityOutlineShader != null) {
      if (GlowEntityRegistry.INSTANCE.isGlowingEnabled()) {
        ci.setReturnValue(true);
      }
    }
  }

  @ModifyConstant(method = "renderEntities", constant = @Constant(classValue = EntityPlayer.class))
  private Class<?> entityFilter(Object rawEntity, Class<?> original) {
    // This is an instanceof check, so return the object's class if the check should succeed.
    if (rawEntity instanceof Entity entity) {
      if (GlowFilter.isGlowing(entity)) {
        return entity.getClass();
      }
    }
    Minecraft mc = Minecraft.getMinecraft();
    if (mc.thePlayer.isSpectator() && mc.gameSettings.keyBindSpectatorOutlines.isKeyDown()) {
      // Vanilla spectator behavior
      return original;
    }
    // Filter out entity
    return Void.class;
  }
}
