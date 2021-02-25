package io.github.jacobpassam.kits;

import io.github.jacobpassam.kits.command.KitCommand;
import io.github.jacobpassam.kits.kit.KitManager;
import io.github.jacobpassam.kits.time.KitTimeoutManager;
import io.github.jacobpassam.kits.time.KitTimeoutManagerImpl;
import io.github.jacobpassam.kits.time.data.KitRepository;
import io.github.jacobpassam.kits.time.data.impl.KitMemoryRepository;
import org.bukkit.plugin.java.JavaPlugin;

public final class KitsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        KitRepository kitRepository = new KitMemoryRepository();
        KitTimeoutManager kitTimeoutManager = new KitTimeoutManagerImpl(kitRepository);
        getServer().getPluginManager().registerEvents(kitTimeoutManager, this);

        KitManager kitManager = new KitManager(this, kitTimeoutManager);
        kitManager.loadKits();

        getCommand("kit").setExecutor(new KitCommand(kitManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
