package io.github.jacobpassam.kits.time.data.impl;

import io.github.jacobpassam.kits.kit.Kit;
import io.github.jacobpassam.kits.time.PlayerKitData;
import io.github.jacobpassam.kits.time.data.KitRepository;

import java.util.UUID;

/**
 * Implementation of {@link KitRepository} which holds data in
 * memory.
 */
public class KitMemoryRepository implements KitRepository {

    @Override
    public PlayerKitData getDataForPlayer(UUID uuid) {
        return new PlayerKitData(); // No database - nothing to read from. Empty PlayerKitData object.
    }

    @Override
    public void setLastUsedTime(UUID uuid, Kit kit, long time) {
        return; // No database - nothing to set.
    }
}
