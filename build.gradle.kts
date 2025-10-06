plugins {
    id("net.labymod.labygradle")
    id("net.labymod.labygradle.addon")
}

val versions = providers.gradleProperty("net.labymod.minecraft-versions").get().split(";")

group = "dev.rocco.mods"
version = providers.environmentVariable("VERSION").getOrElse("1.0.0")

labyMod {
    defaultPackageName = "dev.rocco.mods.laby.glowfix"

    minecraft {
        registerVersion(versions.toTypedArray()) {
            runs {
                getByName("client") {}
            }
        }
    }

    addonInfo {
        namespace = "glowfix"
        displayName = "Entity Glow Fix"
        author = "RoccoDev"
        description = "Makes entity glow available in 1.8"
        minecraftVersion = "1.8.9"
        version = rootProject.version.toString()
    }
}

subprojects {
    plugins.apply("net.labymod.labygradle")
    plugins.apply("net.labymod.labygradle.addon")

    group = rootProject.group
    version = rootProject.version
}