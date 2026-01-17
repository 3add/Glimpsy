package threeadd.glimpsy;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.set.RegistrySet;
import io.papermc.paper.registry.tag.TagKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threeadd.glimpsy.util.enchantments.CustomEnchantment;
import threeadd.glimpsy.util.enchantments.CustomEnchantmentInfo;
import threeadd.glimpsy.util.enchantments.CustomEnchantmentRegistry;

import java.util.List;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public class GlimpsyBootstrap implements PluginBootstrap {

    private static final Logger log = LoggerFactory.getLogger(GlimpsyBootstrap.class);

    @Override
    public void bootstrap(@NotNull BootstrapContext context) {

        List<CustomEnchantment> enchantments = CustomEnchantmentRegistry.INSTANCE.getRegisteredItems();

        for (CustomEnchantment enchantment : enchantments) {
            TypedKey<Enchantment> enchantmentKey = TypedKey.create(RegistryKey.ENCHANTMENT, enchantment.createKey());
            CustomEnchantmentInfo info = enchantment.createInfo();
            List<TypedKey<ItemType>> supportedItemKeys = enchantment.createSupportedItems();
            List<TagKey<Enchantment>> enchantmentsTags = enchantment.createTags();

            context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.compose().newHandler(event ->
                    event.registry().register(
                            enchantmentKey,
                            b -> b.description(enchantment.createDescription())
                                    .anvilCost(info.anvilCost())
                                    .maximumCost(info.maxCost())
                                    .minimumCost(info.minCost())
                                    .weight(info.weight())
                                    .maxLevel(info.maxLevel())
                                    .activeSlots(enchantment.createActiveSlots())
                                    .exclusiveWith(RegistrySet.keySetFromValues(RegistryKey.ENCHANTMENT, enchantment.createIncompatibleEnchantments()))
                                    .supportedItems(RegistrySet.keySet(RegistryKey.ITEM, supportedItemKeys))
                    )));
            context.getLifecycleManager().registerEventHandler(LifecycleEvents.TAGS.postFlatten(RegistryKey.ENCHANTMENT), event ->
                    enchantmentsTags.forEach(tag ->
                            event.registrar().addToTag(tag, Set.of(enchantmentKey))));
        }

        log.info("Registered {} enchantments", enchantments.size());
    }
}
