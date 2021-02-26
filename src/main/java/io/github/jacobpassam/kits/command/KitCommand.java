package io.github.jacobpassam.kits.command;

import io.github.jacobpassam.kits.kit.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {

    private final KitManager kitManager;

    public KitCommand(KitManager kitManager) {
        this.kitManager = kitManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("Invalid usage. /kit <name>");
            return true;
        }

        String kitName = args[0];

        if (!kitManager.exists(kitName)) player.sendMessage("That kit does not exist.");
        else kitManager.giveKitToPlayer(player, kitName);

        return true;
    }
}
