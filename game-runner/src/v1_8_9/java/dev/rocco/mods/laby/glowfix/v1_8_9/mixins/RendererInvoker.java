package dev.rocco.mods.laby.glowfix.v1_8_9.mixins;

import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RendererLivingEntity.class)
public interface RendererInvoker {
  @Invoker
  void callRenderModel(EntityLivingBase entity, float a, float b, float c, float d, float e, float f);

  @Invoker
  void callRenderLayers(EntityLivingBase entity, float a, float b, float c, float d, float e, float f, float g);
}
