package threeadd.glimpsy.feature.help.list;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import threeadd.glimpsy.util.Documentable;
import threeadd.glimpsy.util.inventory.ItemBuilder;
import threeadd.glimpsy.util.inventory.ListInventory;

import java.util.Collection;
import java.util.function.Consumer;

public abstract class SubHelpListInventory<T extends Documentable> extends ListInventory<T> {

    public SubHelpListInventory(@NotNull Component inventoryTitle, @NotNull Collection<T> items, Player viewer) {
        super(Rows.SIX, inventoryTitle, items, Rows.SIX.getInnerSlots(), viewer);

        setTransferSlot(Rows.SIX.getTotalSlots() - 9,
                new ItemBuilder(Material.RED_DYE)
                        .withName(Component.text("Return")
                                .color(TextColor.color(0xDB5858)))
                        .build(),
                () -> new threeadd.glimpsy.feature.help.HelpListInventory(getViewer()));
    }

    protected Consumer<InventoryClickEvent> mapClick(@NotNull T item) {
        return event ->
                getViewer().sendMessage(item.createUsage());
    }
}
