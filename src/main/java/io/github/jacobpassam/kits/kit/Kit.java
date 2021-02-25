package io.github.jacobpassam.kits.kit;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

/**
 * Represents a Kit object.
 */
public interface Kit {

    /**
     * @return The name of this kit.
     */
    String getName();

    /**
     * @return The rank name that this kit is exclusive to. Otherwise, <code>null</code>.
     */
    String getRankName();

    /**
     * @return The timeout in milliseconds before this kit can be used again by a player with the same UUID. (<code>-1</code> if it can be used infinitely)
     */
    long getTimeout();

    /**
     * @return The cost for this kit.
     */
    int getCost();

    /**
     * This method returns the non-armor items included with this kit.
     * <p>
     * The Set should be empty if no items are included.
     * <p>
     * Upon using the returned Set, {@link Inventory#addItem(ItemStack...)} should be used
     * in order to add the items in any possible slot.
     *
     * @return A Set of non-armor items given with this kit.
     */
    Set<ItemStack> getItems();

    /**
     * This method returns a List of the armor given within the kit.
     * <p>
     * The List should be empty if no armor is required. Otherwise, the List should be populated and
     * have <code>null</code> values for empty armor slots.
     * <p>
     * Index 0 in the list should be Boots.
     * Index 1 in the list should be Leggings.
     * Index 2 in the list should be a Chestplate.
     * Index 3 in the list should be a Helmet.
     * <p>
     * The implementation should provide support for <code>null</code> values where the
     * given armor slot at that index is empty but a further one isn't.
     * For example, if the kit requires all armor except a Chestplate, then
     * a <code>null</code> value should be used at index 1 and all the other indexes (0, 2, 3) should have values.
     * <p>
     * Upon checking for items within the returned List, {@link List#get(int)} should be used and
     * surrounded by a try/catch for each armor slot to avoid throwing an {@link IndexOutOfBoundsException}.
     * <p>
     * {@link List#add(Object)} should be used to add items to the list in the correct order to avoid index mismatch.
     *
     * @return A List of the armor included with the kit.
     */
    List<ItemStack> getArmor();
}
