package io.github.jacobpassam.kits;

import io.github.jacobpassam.kits.command.KitCommand;
import io.github.jacobpassam.kits.kit.KitManager;
import io.github.jacobpassam.kits.time.KitTimeoutManager;
import io.github.jacobpassam.kits.time.KitTimeoutManagerImpl;
import io.github.jacobpassam.kits.time.storage.KitRepository;
import io.github.jacobpassam.kits.time.storage.impl.KitMemoryRepository;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class KitsBase {

    private final JavaPlugin plugin;

    private KitManager kitManager;
    private KitRepository kitRepository;
    private KitTimeoutManager kitTimeoutManager;

    /**
     * Class constructor
     *
     * @param plugin Instance of JavaPlugin
     */
    public KitsBase(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Initiates plugin by loading Kit data, registering listeners and registering commands.
     */
    public void load() {
        kitRepository = new KitMemoryRepository();
        kitTimeoutManager = new KitTimeoutManagerImpl(kitRepository);

        kitManager = new KitManager(plugin, kitTimeoutManager);
        kitManager.loadKits();

        plugin.getServer().getPluginManager().registerEvents(kitTimeoutManager, plugin);
        registerCommand("kit", new KitCommand(kitManager));
    }

    /**
     * Registers a given command name.
     *
     * @param name            The name of the command.
     * @param commandExecutor The CommandExecutor to use upon execution.
     * @see PluginCommand#setExecutor(CommandExecutor)
     */
    public void registerCommand(String name, CommandExecutor commandExecutor) {
        PluginCommand command = plugin.getCommand(name);
        assert command != null;

        command.setExecutor(commandExecutor);
    }

}
