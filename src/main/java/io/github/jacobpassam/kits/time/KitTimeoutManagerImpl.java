package io.github.jacobpassam.kits.time;

import com.google.common.collect.Maps;
import io.github.jacobpassam.kits.kit.Kit;
import io.github.jacobpassam.kits.time.storage.KitRepository;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

/**
 * Implementation of {@link KitTimeoutManager} in which Kit timeouts are
 * cached locally for reading and stored permanently inside a KitRepository.
 * <p>
 * Caches are created upon calling the {@link org.bukkit.event.player.PlayerJoinEvent} and
 * removed on calling the {@link org.bukkit.event.player.PlayerQuitEvent}. As they are only held in
 * memory, any restart/reload of the server will clear the kit timeout cache and data would be
 * read again from the {@link KitRepository} on join.
 */
public class KitTimeoutManagerImpl implements KitTimeoutManager {

    private final KitRepository repository;

    private final Map<UUID, PlayerKitData> dataMap;

    /**
     * Class constructor
     *
     * @param repository The repository in which permanent Kit data is stored.
     */
    public KitTimeoutManagerImpl(KitRepository repository) {
        this.repository = repository;
        this.dataMap = Maps.newHashMap();
    }

    @Override
    public void load(UUID uuid) {
        dataMap.put(uuid, repository.getDataForPlayer(uuid));
    }

    @Override
    public boolean isKitUsable(UUID uuid, Kit kit) {
        return dataMap.get(uuid).canUseKit(kit);
    }

    @Override
    public long getTimeoutRemaining(UUID uuid, Kit kit) {
        if (!dataMap.containsKey(uuid)) load(uuid);

        Long lastUsage = dataMap.get(uuid).getLastUsageForKit(kit);

        if (lastUsage == null) return 0L;
        return (dataMap.get(uuid).getLastUsageForKit(kit) + kit.getTimeout()) - System.currentTimeMillis();
    }

    @Override
    public void writeUsage(UUID uuid, Kit kit) {
        if (!dataMap.containsKey(uuid)) load(uuid);

        long time = System.currentTimeMillis();

        dataMap.get(uuid).writeUsage(kit, time);
        repository.setLastUsedTime(uuid, kit, time);
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        load(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        dataMap.remove(event.getPlayer().getUniqueId());
    }
}
