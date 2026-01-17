package threeadd.glimpsy.feature.simple;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import threeadd.glimpsy.util.text.ComponentParser;

public class ExpensiveAnvilListener implements Listener {

    @SuppressWarnings("UnstableApiUsage")
    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {

        int repairCost = event.getView().getRepairCost();

        if (!(repairCost >= event.getView().getMaximumRepairCost())) return;

        event.getView().setMaximumRepairCost(Integer.MAX_VALUE);
        event.getView().getPlayer().sendMessage(
                ComponentParser.parseRichString("This anvil upgrade costs you: <#F8DD97>" + event.getView().getRepairCost() + " Levels"));
    }
}
