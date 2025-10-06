package dev.rocco.mods.laby.glowfix;

import net.labymod.api.Laby;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import net.labymod.api.event.client.network.server.SubServerSwitchEvent;
import net.labymod.api.models.addon.annotation.AddonMain;

import java.nio.charset.StandardCharsets;

@AddonMain
public class GlowFixAddon extends LabyAddon<GlowFixConfiguration> {
  static GlowFixAddon INSTANCE;

  private ResourceLocation payloadChannel;

  @Override
  protected void enable() {
    INSTANCE = this;
    payloadChannel = ResourceLocation.create("glowfix", "1.0");

    this.registerListener(this);
    this.registerSettingCategory();
  }

  @Override
  protected Class<GlowFixConfiguration> configurationClass() {
    return GlowFixConfiguration.class;
  }

  private void announceMod() {
    Laby.labyAPI().serverController().sendPayload(payloadChannel,
            addonInfo().getVersion().getBytes(StandardCharsets.UTF_8));
  }

  @Subscribe
  public void onConnect(ServerJoinEvent event) {
    announceMod();
  }

  @Subscribe
  public void onSwitch(SubServerSwitchEvent event) {
    announceMod();
  }
}
