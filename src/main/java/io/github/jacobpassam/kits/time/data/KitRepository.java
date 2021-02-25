package io.github.jacobpassam.kits.time.data;

import io.github.jacobpassam.kits.kit.Kit;
import io.github.jacobpassam.kits.time.PlayerKitData;

import java.util.UUID;

/**
 * Represents a data repository in which Kit timeouts are stored permanently. Ideally, this would be stored in a database.
 *
 * @see io.github.jacobpassam.kits.time.data.impl.KitMemoryRepository
 */
public interface KitRepository {

    /**
     * Loads a {@link PlayerKitData} object from the data source used by the {@link KitRepository}.
     *
     * @param uuid The UUID to load data for.
     * @return A {@link PlayerKitData} object.
     */
    PlayerKitData getDataForPlayer(UUID uuid);

    /**
     * Sets the time of the kit's last usage by this UUID using {@link System#currentTimeMillis()}.
     *
     * @param uuid The UUID to set for.
     * @param kit  The Kit which the UUID has accessed.
     * @param time The time when the Kit was used.
     */
    void setLastUsedTime(UUID uuid, Kit kit, long time);
}
