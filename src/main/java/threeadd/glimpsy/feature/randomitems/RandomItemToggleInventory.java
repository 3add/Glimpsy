package threeadd.glimpsy.feature.randomitems;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import threeadd.glimpsy.util.inventory.CustomInventory;
import threeadd.glimpsy.util.inventory.ItemBuilder;

import static threeadd.glimpsy.util.text.ComponentParser.parseBoolean;
import static threeadd.glimpsy.util.text.ComponentParser.parseRichString;

public final class RandomItemToggleInventory extends CustomInventory {

    private final Player player;

    public RandomItemToggleInventory(Player player) {
        super(Rows.THREE, Component.text("Item Toggles"));
        this.player = player;
        create();
    }

    private void create() {
        RandomItemsInfo info = RandomItemManager.getInfo(player);
        if (info == null) {
            getInventory().close();
            player.sendRichMessage("<red>You're random item data hasn't been loaded yet, please wait");
            return;
        }

        int slot = 11;

        for (Material material : RandomItemManager.RANDOM_ITEMS.keySet()) {
            setSlot(slot, new ItemBuilder(material)
                            .withLore(
                                    parseRichString("Enabled: ").append(parseBoolean(info.isEnabled(material))),
                                    parseRichString("Click to toggle " + material.name()))
                            .build(),
                    event -> {
                        info.toggle(material);
                        player.openInventory(new RandomItemToggleInventory(player).getInventory());
                    });

            slot++;
        }
    }
}
