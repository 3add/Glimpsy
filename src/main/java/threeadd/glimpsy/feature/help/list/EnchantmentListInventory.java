package threeadd.glimpsy.feature.help.list;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.NotNull;
import threeadd.glimpsy.util.enchantments.CustomEnchantment;
import threeadd.glimpsy.util.enchantments.CustomEnchantmentRegistry;
import threeadd.glimpsy.util.inventory.ItemBuilder;

public class EnchantmentListInventory extends SubHelpListInventory<CustomEnchantment> {

    public EnchantmentListInventory(Player viewer) {
        super(Component.text("Custom Enchantments"), CustomEnchantmentRegistry.INSTANCE.getRegisteredItems(), viewer);
    }

    @Override
    protected @NotNull ItemStack mapToItem(@NotNull CustomEnchantment enchantment) {
        Enchantment bukkitEnchantment = enchantment.toBukkitEnchantment();
        return new ItemBuilder(Material.ENCHANTED_BOOK)
                .withItemMeta(EnchantmentStorageMeta.class, meta ->
                        meta.addStoredEnchant(bukkitEnchantment, bukkitEnchantment.getMaxLevel(), true))
                .withLore(enchantment.createUsage())
                .build();
    }
}
