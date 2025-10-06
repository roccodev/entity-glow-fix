# Entity Glow Fix
A LabyMod 4 addon (and soon Forge mod?) that ports entity glowing to 1.8.9.

Unlike vanilla 1.8 glowing (used for spectator highlighting), this addon also:

* Outlines entity layers (held item, etc.)
* Outlines the entity more precisely (as fixed in 15w51a)
* Works with OptiFine fast render

Those fixes also apply to vanilla spectator highlighting when using the mod.

## Usage

The mod announces itself to the server by sending a plugin message in the `glowfix:1.0` channel
containing its full version as a string.

Servers can then set bit 6 in the entity's data watcher slot 0 to enable glowing for the entity.
This is how vanilla servers report glowing in 1.9, and on some servers (like Hypixel) this is already sent
even for 1.8 clients, so the mod works automatically there.

## License
This mod is distributed under the terms of the [GPLv3](https://www.gnu.org/licenses/gpl-3.0.html). See [COPYING](COPYING) for details.