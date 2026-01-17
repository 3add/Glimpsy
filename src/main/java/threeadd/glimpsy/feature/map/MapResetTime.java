package threeadd.glimpsy.feature.map;

import java.time.LocalDateTime;

public class MapResetTime {

    public static final String key = "mapreset";
    private final LocalDateTime time;

    public MapResetTime() {
        this(key, LocalDateTime.now().plusDays(2));
    }

    protected MapResetTime(String key, LocalDateTime time) {
        if (!key.equalsIgnoreCase(MapResetTime.key))
            throw new IllegalStateException();

        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
