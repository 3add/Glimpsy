package threeadd.glimpsy.feature.recipes;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import threeadd.glimpsy.Glimpsy;
import threeadd.glimpsy.util.recipes.CustomRecipe;
import threeadd.glimpsy.util.text.ComponentParser;

public class BundleRecipe extends CustomRecipe {

    @Override
    public Component createUsage() {
        return ComponentParser.parseRichString("Bundle Recipe was added to allow players to cary more items");
    }

    @Override
    public CraftingRecipe createRecipe() {
        return new ShapedRecipe(new NamespacedKey(Glimpsy.getInstance(), "bundle"), ItemStack.of(Material.BUNDLE))
                .shape("LSL", "SSS", "LLL")
                .setIngredient('L', Material.LEATHER)
                .setIngredient('S', Material.STRING);
    }
}
