package io.github.jacobpassam.kits.kit.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.github.jacobpassam.kits.kit.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Apples implements Kit {

    @Override
    public String getName() {
        return "Apples";
    }

    @Override
    public String getRankName() {
            return "LEGEND";
    }

    @Override
    public long getTimeout() {
        return 10 * 1000L; // 10 seconds
    }

    @Override
    public int getCost() {
        return 675;
    }

    @Override
    public Set<ItemStack> getItems() {
        Set<ItemStack> items = Sets.newHashSet();
        items.add(new ItemStack(Material.APPLE, 16));
        items.add(new ItemStack(Material.GOLDEN_APPLE, 4));

        return items;
    }

    @Override
    public List<ItemStack> getArmor() {
        return Lists.newArrayList(); // Empty List
    }
}
