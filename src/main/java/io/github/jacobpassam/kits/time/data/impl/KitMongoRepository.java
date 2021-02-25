package io.github.jacobpassam.kits.time.data.impl;

import io.github.jacobpassam.kits.kit.Kit;
import io.github.jacobpassam.kits.time.PlayerKitData;
import io.github.jacobpassam.kits.time.data.KitRepository;

import java.util.UUID;

/**
 * Example class for a Kit Mongo repository.
 */
public class KitMongoRepository implements KitRepository {

    @Override
    public PlayerKitData getDataForPlayer(UUID uuid) {
        // Get a row from Mongo by the UUID and parse into a PlayerKitData object.
        return null;
    }

    @Override
    public void setLastUsedTime(UUID uuid, Kit kit, long time) {
        // Update/create the Mongo document for this UUID and add/set the Kit's last usage to 'time'.
    }
}
