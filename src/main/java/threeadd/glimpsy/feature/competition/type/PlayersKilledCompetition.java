package threeadd.glimpsy.feature.competition.type;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import threeadd.glimpsy.feature.competition.Competition;

public class PlayersKilledCompetition extends Competition {

    public PlayersKilledCompetition() {
        super(Component.text("Players Killed")
                .color(TextColor.color(0xD05F5F)));
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        if (!(event.getDamageSource().getCausingEntity() instanceof Player attacker))
            return;

        increment(attacker);
    }
}
