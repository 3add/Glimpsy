package threeadd.glimpsy.feature.competition.type;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import threeadd.glimpsy.feature.competition.Competition;

public class TimesFishedCompetition extends Competition {

    public TimesFishedCompetition() {
        super(Component.text("Times Fished")
                .color(TextColor.color(0x68A7D9)));
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (!event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH))
            return;

        increment(event.getPlayer());
    }
}
