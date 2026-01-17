package threeadd.glimpsy.feature.vote;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threeadd.glimpsy.util.text.ComponentParser;

public class VoteListener implements Listener {

    private static final Logger log = LoggerFactory.getLogger(VoteListener.class);

    @EventHandler
    public void onEvent(VotifierEvent event) {
        Vote vote = event.getVote();

        VotingManager.addVote();
        Bukkit.getServer().broadcast(ComponentParser.parseRichString(
                vote.getUsername() + " has voted on " + vote.getServiceName()
        ));
        log.info("Player {} voted on {}", vote.getUsername(), vote.getServiceName());
    }
}
