package dev.rocco.mods.laby.glowfix.v1_8_9.mixins;

import dev.rocco.mods.laby.glowfix.GlowEntityRegistry;
import dev.rocco.mods.laby.glowfix.v1_8_9.GlowFilter;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {
  @Inject(method = "onEntityUpdate", at = @At("RETURN"))
  public void syncGlowing(CallbackInfo ci) {
    if (GlowFilter.isGlowing((Entity) (Object) this)) {
      GlowEntityRegistry.INSTANCE.setGlowingBusy(true);
    }
  }
}
