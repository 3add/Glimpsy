package threeadd.glimpsy.feature.competition.type;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import threeadd.glimpsy.feature.competition.Competition;

public class EntitiesKilledCompetition extends Competition {

    public EntitiesKilledCompetition() {
        super(Component.text("Entities Killed")
                .color(TextColor.color(0xC47E7E)));
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        if (!(event.getDamageSource().getCausingEntity() instanceof Player attacker))
            return;

        increment(attacker);
    }
}
