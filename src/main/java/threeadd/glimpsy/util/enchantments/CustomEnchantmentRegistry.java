package threeadd.glimpsy.util.enchantments;

import threeadd.glimpsy.feature.explosive.ExplosiveEnchantment;
import threeadd.glimpsy.util.registry.Registry;

public class CustomEnchantmentRegistry extends Registry<CustomEnchantment> {

    public final static CustomEnchantmentRegistry INSTANCE = new CustomEnchantmentRegistry();

    public final CustomEnchantment EXPLOSIVE;

    private CustomEnchantmentRegistry() {
        this.EXPLOSIVE = register(new ExplosiveEnchantment());
    }
}
