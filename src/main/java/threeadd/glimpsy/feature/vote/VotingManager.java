package threeadd.glimpsy.feature.vote;

import threeadd.glimpsy.util.manager.Manager;

public class VotingManager extends Manager {

    private static final int VOTES_NEEDED = 50;

    private static int votes = 0;

    public static void addVote() {
        votes++;
    }

    public static int getVotes() {
        return votes;
    }
}
