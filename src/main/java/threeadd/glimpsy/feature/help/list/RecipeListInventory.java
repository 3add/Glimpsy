package threeadd.glimpsy.feature.help.list;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import threeadd.glimpsy.util.inventory.ItemBuilder;
import threeadd.glimpsy.util.recipes.CustomRecipe;
import threeadd.glimpsy.util.recipes.CustomRecipeRegistry;
import threeadd.glimpsy.util.text.ComponentParser;

import java.util.function.Consumer;

public class RecipeListInventory extends SubHelpListInventory<CustomRecipe> {

    public RecipeListInventory(Player viewer) {
        super(Component.text("Custom Recipes"), CustomRecipeRegistry.INSTANCE.getRegisteredItems(), viewer);
    }

    private boolean hasDiscovered(CustomRecipe recipe) {
        return getViewer().hasDiscoveredRecipe(recipe.getKey());
    }

    @Override
    protected @NotNull ItemStack mapToItem(@NotNull CustomRecipe recipe) {
        return new ItemBuilder(recipe.createRecipe().getResult())
                .withLore(recipe.createUsage(),
                        ComponentParser.parseRichString("Discovered: ").append(ComponentParser.parseBoolean(hasDiscovered(recipe))))
                .build();
    }

    @Override
    protected @Nullable Consumer<InventoryClickEvent> mapClick(@NotNull CustomRecipe recipe) {
        return event -> {
            if (hasDiscovered(recipe))
                getViewer().openInventory(new RecipeInfoInventory(getViewer(), recipe).getInventory());
            else
                event.getView().getPlayer().sendMessage(ComponentParser.parseRichString("You haven't discovered this recipe yet!"));
        };
    }
}
