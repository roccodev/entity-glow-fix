package dev.rocco.mods.laby.glowfix.v1_8_9.mixins;

import dev.rocco.mods.laby.glowfix.GlowEntityRegistry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class MixinWorld {
  @Shadow
  private boolean isRemote;

  @Inject(method = "updateEntities", at = @At("HEAD"))
  public void resetGlowing(CallbackInfo ci) {
    // In single-player worlds, this is also called by the internal server
    if (isRemote) {
      GlowEntityRegistry.INSTANCE.setGlowingBusy(false);
    }
  }
}
