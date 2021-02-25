package io.github.jacobpassam.kits.kit.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.github.jacobpassam.kits.kit.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Warrior implements Kit {

    @Override
    public String getName() {
        return "Warrior";
    }

    @Override
    public String getRankName() {
        return null;
    }

    @Override
    public long getTimeout() {
        return -1L;
    }

    @Override
    public int getCost() {
        return 150;
    }

    @Override
    public Set<ItemStack> getItems() {
        Set<ItemStack> items = Sets.newHashSet();
        items.add(new ItemStack(Material.STONE_SWORD));

        return items;
    }

    @Override
    public List<ItemStack> getArmor() {
        List<ItemStack> items = new ArrayList<>();

        items.add(new ItemStack(Material.DIAMOND_BOOTS));
        items.add(new ItemStack(Material.GOLDEN_LEGGINGS));
        items.add(null);
        items.add(new ItemStack(Material.CHAINMAIL_HELMET));

        // The above is equal to doing this - use whichever you prefer.
        items = Lists.newArrayList(
                new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.GOLDEN_LEGGINGS),
                null,
                new ItemStack(Material.CHAINMAIL_HELMET)
        );

        return items;
    }
}
