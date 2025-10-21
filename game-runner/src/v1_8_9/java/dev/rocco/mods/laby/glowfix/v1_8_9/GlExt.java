package dev.rocco.mods.laby.glowfix.v1_8_9;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

public class GlExt {
  private static final FloatBuffer BUF_FLOAT_4 = BufferUtils.createFloatBuffer(4);

  public static void enableOutlineMode(float r, float g, float b, float a) {
    BUF_FLOAT_4.put(0, r);
    BUF_FLOAT_4.put(1, g);
    BUF_FLOAT_4.put(2, b);
    BUF_FLOAT_4.put(3, a);
    GL11.glTexEnvfv(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_COLOR, BUF_FLOAT_4);
    GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, OpenGlHelper.GL_COMBINE);
    GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
    GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_RGB, GL11.GL_REPLACE);
    GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_ALPHA, GL11.GL_REPLACE);

    // Don't outline over translucent parts
    GlStateManager.enableBlend();
    GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 1);
  }

  public static void disableOutlineMode() {
    GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
    GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_RGB, GL11.GL_TEXTURE);
    GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_RGB, GL11.GL_MODULATE);
    GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_ALPHA, GL11.GL_MODULATE);

    GlStateManager.disableBlend();
  }
}
