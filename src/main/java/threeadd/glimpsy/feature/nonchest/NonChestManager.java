package threeadd.glimpsy.feature.nonchest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threeadd.glimpsy.util.manager.Manager;

import java.util.Objects;

public class NonChestManager extends Manager {

    private static final Logger log = LoggerFactory.getLogger(NonChestManager.class);
    private static NonChest nonChest;

    @Override
    public void enable() {
        NonChest loadedChest = NonChestRepository.getInstance().load();
        nonChest = Objects.requireNonNullElseGet(loadedChest, NonChest::new);

        log.info("Loaded the NonChest with {} items", nonChest.getInventory().getContents().length);
    }

    @Override
    public void disable() {
        NonChestRepository.getInstance().save(nonChest);
    }

    public static NonChest getNonChest() {
        return nonChest;
    }
}