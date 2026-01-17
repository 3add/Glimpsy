package threeadd.glimpsy.feature.randomitems;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RandomItemsInfo {

    private final UUID ownerId;
    private final Map<Material, Boolean> states;

    public RandomItemsInfo(Player owner) {
        this(owner.getUniqueId(), List.of()); // Don't disable any by default
    }

    protected RandomItemsInfo(UUID ownerId, List<Material> disabledMaterials) {
        this.ownerId = ownerId;

        Map<Material, Boolean> totalStates = createDefaultStates();

        for (Material disabledMaterial : disabledMaterials) {
            if (!totalStates.containsKey(disabledMaterial))
                throw new IllegalStateException("Tried disabling invalid material " + disabledMaterial);

            totalStates.put(disabledMaterial, false); // Only override database stored values (it only stores disabled states)
        }

        this.states = totalStates;
    }

    public static Map<Material, Boolean> createDefaultStates() {
        Map<Material, Boolean> itemStates = new HashMap<>();

        RandomItemManager.RANDOM_ITEMS.keySet().forEach(item ->
                itemStates.put(item, true));

        return itemStates;
    }

    public void toggle(Material material) {
        states.put(material, !isEnabled(material));
    }

    public boolean isEnabled(Material material) {
        if (!states.containsKey(material))
            throw new IllegalStateException("Couldn't find a random material: " + material.toString());

        return states.get(material);
    }

    // Repo Data access

    protected Map<Material, Boolean> getStates() {
        return states;
    }

    protected UUID getOwnerId() {
        return ownerId;
    }
}
