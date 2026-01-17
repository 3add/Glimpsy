package threeadd.glimpsy.util.recipes;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import threeadd.glimpsy.util.Documentable;

import java.util.List;

public abstract class CustomRecipe implements Documentable {
    public abstract Recipe createRecipe();

    public NamespacedKey getKey() {
        Recipe recipe = createRecipe();

        if (recipe instanceof Keyed keyed)
            return keyed.getKey();

        throw new IllegalStateException("Unknown CraftingRecipe instance" + recipe);
    }

    public List<RecipeChoice> getRequirements() {
        Recipe recipe = createRecipe();

        if (recipe instanceof ShapelessRecipe shapelessRecipe) {
            return shapelessRecipe.getChoiceList();
        } else if (recipe instanceof ShapedRecipe shapedRecipe) {
            return shapedRecipe.getChoiceMap().values().stream().toList();
        } else if (recipe instanceof FurnaceRecipe furnaceRecipe) {
            return List.of(furnaceRecipe.getInputChoice());
        }

        throw new IllegalStateException("Unknown CraftingRecipe instance" + recipe);
    }
}
