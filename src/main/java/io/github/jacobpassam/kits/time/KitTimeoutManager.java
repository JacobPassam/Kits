package io.github.jacobpassam.kits.time;

import io.github.jacobpassam.kits.kit.Kit;
import org.bukkit.event.Listener;

import java.util.UUID;

public interface KitTimeoutManager extends Listener {

    /**
     * Loads a PlayerKitData into cache from the {@link io.github.jacobpassam.kits.time.data.KitRepository}.
     *
     * @param uuid The UUID of the player whose data is to be loaded.
     */
    void load(UUID uuid);

    /**
     * Returns whether a Kit is usable or not based on timeouts.
     *
     * @param uuid The UUID of the player attempting to use the kit.
     * @param kit  The Kit to be queried.
     * @return Whether or not the kit can be accessed.
     */
    boolean isKitUsable(UUID uuid, Kit kit);

    /**
     * Returns the amount of milliseconds remaining on the kit's timeout for the player.
     *
     * @param uuid The UUID of the player attempting to use the kit.
     * @param kit  The Kit to be queried.
     * @return The amount remaining on the kit's timeout for that player.
     */
    long getTimeoutRemaining(UUID uuid, Kit kit);

    /**
     * Write a usage of this Kit into the cached {@link PlayerKitData} object and
     * the {@link io.github.jacobpassam.kits.time.data.KitRepository}.
     *
     * @param uuid The UUID of the player who has used the Kit.
     * @param kit  The Kit which has been used.
     */
    void writeUsage(UUID uuid, Kit kit);
}
