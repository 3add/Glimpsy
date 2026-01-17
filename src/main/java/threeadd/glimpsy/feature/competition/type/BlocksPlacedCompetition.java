package threeadd.glimpsy.feature.competition.type;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import threeadd.glimpsy.feature.competition.Competition;

public class BlocksPlacedCompetition extends Competition {

    public BlocksPlacedCompetition() {
        super(Component.text("Blocks Placed")
                .color(TextColor.color(0xDDD1A2)));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        increment(event.getPlayer());
    }
}
