package threeadd.glimpsy.feature.simple;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import threeadd.glimpsy.util.text.ComponentParser;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent event) {

        // Using viewerUnaware renderer boosts performance, see https://jd.papermc.io/paper/1.21.11-pre3/io/papermc/paper/chat/ChatRenderer.ViewerUnaware.html
        // Unfortunately we do need a viewer (to measure distance) and thus I just wasted your time :)

        event.renderer(((source, sourceDisplayName, message, receiver) -> {

            Component name = sourceDisplayName;

            if (receiver instanceof Player playerReceiver)
                name = ComponentParser.getDistancedString(source.getLocation(), playerReceiver.getLocation(), source.getName());

            return Component.textOfChildren(
                    name,
                    ComponentParser.parseRichString(": "),
                    message
            );
        }));
    }
}
