package threeadd.glimpsy.feature.recipes;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import threeadd.glimpsy.Glimpsy;
import threeadd.glimpsy.util.recipes.CustomRecipe;
import threeadd.glimpsy.util.text.ComponentParser;

public class LeatherRecipe extends CustomRecipe {

    @Override
    public Component createUsage() {
        return ComponentParser.parseRichString("This was added to make obtaining bundles easier.");
    }

    @Override
    public Recipe createRecipe() {
        return new ShapelessRecipe(new NamespacedKey(Glimpsy.getInstance(), "leather"), ItemStack.of(Material.LEATHER))
                .addIngredient(9, Material.LEATHER);
    }
}
