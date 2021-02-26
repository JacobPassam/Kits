package io.github.jacobpassam.kits;

import org.bukkit.plugin.java.JavaPlugin;

public final class KitsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new KitsBase(this).load();
    }

    @Override
    public void onDisable() { }
}
