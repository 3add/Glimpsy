package threeadd.glimpsy.feature.competition.type;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import threeadd.glimpsy.feature.competition.Competition;

public class BlocksBrokenCompetition extends Competition {

    public BlocksBrokenCompetition() {
        super(Component.text("Blocks Broken")
                .color(TextColor.color(0xDDD1A2)));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        increment(event.getPlayer());
    }
}