package dev.rocco.mods.laby.glowfix.v1_8_9.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import dev.rocco.mods.laby.glowfix.v1_8_9.GlExt;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Makes the glowing effect match the appearance it has in 1.9+.
 *
 * <p>Specifically, it fixes:
 * <ul>
 *   <li>The glowing effect not applying to layers (held items, etc.)</li>
 *   <li>The glowing effect not matching the entity outline precisely.</li>
 * </ul>
 */
@Mixin(RendererLivingEntity.class)
public abstract class MixinMatch19Glow {
  @Shadow
  private boolean renderOutlines;

  @Redirect(method = "doRender", at = @At(
          value = "INVOKE",
          target = "net/minecraft/client/renderer/entity/RendererLivingEntity.renderModel(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V"
  ))
  public void fixLayerOutlines(RendererLivingEntity<?> renderer, EntityLivingBase entity, float a, float b, float c,
                               float d, float e, float f, @Local(ordinal = 1, argsOnly = true) float partialTicks) {
    // Render layers in outline pass
    ((RendererInvoker) renderer).callRenderModel(entity, a, b, c, d, e, f);
    if (((MixinMatch19Glow) (RendererInvoker) renderer).renderOutlines
            && (!(entity instanceof EntityPlayer player) || !player.isSpectator())) {
      ((RendererInvoker) renderer).callRenderLayers(entity, a, b, partialTicks, c, d, e, 0.0625F);
    }
  }

  @Redirect(method = "setScoreTeamColor", at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/GlStateManager.color(FFFF)V"))
  public void setColor(float r, float g, float b, float a) {
    GlExt.enableOutlineMode(r, g, b, a);
    // don't run original glColor
  }

  @Redirect(method = "setScoreTeamColor", at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/GlStateManager.disableTexture2D()V", ordinal = 0))
  public void cancel2dTex() {
    // don't run original glDisable
  }

  @Inject(method = "unsetScoreTeamColor", at = @At("TAIL"))
  public void unsetColor(CallbackInfo ci) {
    GlExt.disableOutlineMode();
  }
}
