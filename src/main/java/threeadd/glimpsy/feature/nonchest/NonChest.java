package threeadd.glimpsy.feature.nonchest;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class NonChest implements InventoryHolder {

    private final Inventory inventory = Bukkit.createInventory(this, 54);

    public NonChest() {
        this(Map.of());
    }

    protected NonChest(Map<Integer, ItemStack> items) {
        items.forEach(inventory::setItem);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
