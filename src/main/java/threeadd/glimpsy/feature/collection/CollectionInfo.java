package threeadd.glimpsy.feature.collection;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;

public class CollectionInfo {

    private final UUID ownerId;

    // Material -> Has Collected
    private final Map<Material, Boolean> states;

    public CollectionInfo(Player owner) {
        this(owner.getUniqueId(), List.of()); // None collected by default
    }

    protected CollectionInfo(UUID ownerId, List<Material> collectedMaterials) {
        this.ownerId = ownerId;
        Map<Material, Boolean> totalStates = createDefaultStates();

        for (Material disabledMaterial : collectedMaterials) {
            if (!totalStates.containsKey(disabledMaterial))
                throw new IllegalStateException("Tried disabling invalid material " + disabledMaterial);

            totalStates.put(disabledMaterial, true); // Only override database stored values (it only stores collected states)
        }

        this.states = totalStates;
    }

    public static Map<Material, Boolean> createDefaultStates() {
        Map<Material, Boolean> itemStates = new LinkedHashMap<>();

        CollectionManager.COLLECTABLES.forEach(item ->
                itemStates.put(item, false));

        return itemStates;
    }

    protected Map<Material, Boolean> getStates() {
        return states;
    }

    protected UUID getOwnerId() {
        return ownerId;
    }

    public List<Material> getCollected() {

        List<Material> collected = new ArrayList<>();
        states.forEach(((material, state) -> {
            if (state)
                collected.add(material);
        }));

        return collected;
    }

    public void collect(Material material) {
        if (states.get(material) == true)
            throw new IllegalStateException("Already Collected " + material);

        states.put(material, true);
    }
}
