package io.github.jacobpassam.kits.kit;

import io.github.jacobpassam.kits.kit.impl.Apples;
import io.github.jacobpassam.kits.kit.impl.Warrior;
import io.github.jacobpassam.kits.time.KitTimeoutManager;
import io.github.jacobpassam.kits.util.UtilTime;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * KitManager class used to manage granting of Kits within the game.
 */
public class KitManager {

    private final JavaPlugin plugin;
    private final KitTimeoutManager kitTimeoutManager;

    private Kit[] kits;

    /**
     * Class constructor
     *
     * @param plugin            JavaPlugin instance used for logging.
     * @param kitTimeoutManager An instance of KitTimeoutManager to manage kit timeouts.
     */
    public KitManager(JavaPlugin plugin, KitTimeoutManager kitTimeoutManager) {
        this.plugin = plugin;
        this.kitTimeoutManager = kitTimeoutManager;
    }

    /**
     * Find a kit by its name.
     *
     * @param name The name.
     * @return The Kit, or <code>null</code> if null.
     */
    public Kit getByName(String name) {
        Kit foundKit = null;

        // Search for Kit in loaded kits.
        for (Kit kit : kits) {
            if (kit.getName().equalsIgnoreCase(name)) {
                foundKit = kit;
            }
        }

        return foundKit;
    }

    /**
     * Gets whether or not this Kit exists.
     *
     * @param name The name of the Kit.
     * @return Whether or not the Kit with the specified name exists.
     */
    public boolean exists(String name) {
        return getByName(name) != null;
    }

    /**
     * Loads all Kits into an array.
     */
    public void loadKits() {
        this.kits = new Kit[]{
                new Warrior(),
                new Apples()
        };

        plugin.getLogger().info("Kits have been loaded.");
    }

    /**
     * Gives a Kit to a player by its name.
     *
     * @param player The player.
     * @param name   The name of the Kit.
     * @throws IllegalArgumentException Thrown if the Kit name was invalid.
     * @see KitManager#exists(String)
     * @see KitManager#giveKitToPlayer(Player, Kit)
     */
    public void giveKitToPlayer(Player player, String name) throws IllegalArgumentException {
        Kit kit = getByName(name);

        // Kit not found.
        if (kit == null) {
            throw new IllegalArgumentException("Invalid kit name provided: " + name);
        }

        giveKitToPlayer(player, kit);
    }

    /**
     * Gives a Kit to the player.
     * @param player The player.
     * @param kit The Kit to give.
     */
    public void giveKitToPlayer(Player player, Kit kit) {

        if (kit.getRankName() != null) {
            /*
            Check if the player has the required rank. If they don't, inform the player and return.
            */
        }

        if (kit.getCost() != 0) {
            /*
            Try to deduct amount from player balance. If they don't have the required amount,
            inform the player and return. Otherwise, deduct the amount.
            */
        }

        if (kitTimeoutManager.isKitUsable(player.getUniqueId(), kit)) {

            player.sendMessage("You were given the kit: " + kit.getName() + ".");

            // Give kit armour
            if (!kit.getArmor().isEmpty()) {

                // Move old armour into inventory where it needs to be moved.
                List<ItemStack> playerArmor = Arrays.asList(player.getInventory().getArmorContents());

                boolean moved = false;
                for (ItemStack itemStack : playerArmor) {
                    int index = playerArmor.indexOf(itemStack);
                    if (itemStack != null && kit.getArmor().get(index) != null) {
                        player.getInventory().addItem(itemStack);

                        moved = true;
                    }
                }

                if (moved)
                    player.sendMessage("We've moved some of your old armor into your inventory to avoid it being replaced.");

                ItemStack[] arr = player.getInventory().getArmorContents();
                for (int i = 0; i < kit.getArmor().size(); i++) {
                    if (kit.getArmor().get(i) != null) arr[i] = kit.getArmor().get(i);
                }

                // Set armour contents
                player.getInventory().setArmorContents(arr);
            }

            // Give kit items
            player.getInventory().addItem(kit.getItems().toArray(new ItemStack[0]));

            // Inform the timeout manager that the Kit has been used.
            kitTimeoutManager.writeUsage(player.getUniqueId(), kit);
        } else {
            long remaining = kitTimeoutManager.getTimeoutRemaining(player.getUniqueId(), kit);

            player.sendMessage("You cannot use the Kit as the timeout has not completed. There is " + UtilTime.timeToString(remaining) + " remaining.");
        }

    }

}
