package threeadd.glimpsy.feature.competition;

import threeadd.glimpsy.feature.competition.type.*;
import threeadd.glimpsy.util.registry.Registry;

public class CompetitionRegistry extends Registry<Competition> {

    public static final CompetitionRegistry INSTANCE = new CompetitionRegistry();

    private CompetitionRegistry() {
        register(new BlocksBrokenCompetition(),
                new BlocksPlacedCompetition(),
                new EntitiesKilledCompetition(),
                new PlayersKilledCompetition(),
                new TimesFishedCompetition());
    }
}
