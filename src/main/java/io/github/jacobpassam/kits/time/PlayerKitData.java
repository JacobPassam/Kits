package io.github.jacobpassam.kits.time;

import io.github.jacobpassam.kits.kit.Kit;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the data held about a player's usage of Kits.
 * <p>
 * This is held strictly only in memory and should be loaded from a database query
 * when first needed by the server.
 * <p>
 * Any writes into here should also be written to the KitRepository data source in order to keep data matching.
 * This object purely acts as a cache to avoid constant network queries and should be able to be reloaded from
 * information in the repository at any point.
 */
public class PlayerKitData {

    private Map<Kit, Long> usages;

    public PlayerKitData() {
        this.usages = new HashMap<>();
    }

    /**
     * Gets the time (milliseconds) at which a player last used the kit. If the
     * kit has not been used, then the method returns <code>null</code>.
     *
     * @param kit The kit to check for.
     * @return The time at which the kit was last used by this player.
     */
    public Long getLastUsageForKit(Kit kit) {
        return usages.get(kit);
    }

    /**
     * Gets whether or not this kit is usable by the player based on Kit timeouts.
     *
     * @param kit The kit to question.
     * @return Whether or not this kit is usable.
     */
    public boolean canUseKit(Kit kit) {
        if (kit.getTimeout() == -1) return true;

        if (getLastUsageForKit(kit) == null) return true;
        else return kit.getTimeout() < ((System.currentTimeMillis() - getLastUsageForKit(kit)));
    }

    /**
     * Writes a Kit usage into the Map at a given (usually current) time.
     *
     * @param kit  The kit to write usage for.
     * @param time The time to set.
     */
    public void writeUsage(Kit kit, long time) {
        usages.put(kit, time);
    }

}
