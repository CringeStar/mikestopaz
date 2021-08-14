package cringestar.mikes.topaz.shield;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;

public class ShieldRegistry {
    //Shield item stuff
    private static final Set<CustomShield> shields = new HashSet<CustomShield>();
    private static final Set<ShieldEnchantment> enchantments = new HashSet<ShieldEnchantment>();

    /**
     * registerShield
     *
     * Registers an instance of FabricShieldItem or Item into the shield registry.
     *
     * @param shield - Instance of shield to register.
     */
    public static void register(CustomShield shield) {
        shields.add(shield);
    }

    public static void register(ShieldEnchantment enchantment) {
        enchantments.add(enchantment);
    }

    /**
     * containsShield
     *
     * Check if the shield registry contains a particular Item instance.
     *
     * @param shield - Item instance to check for.
     */
    public static boolean containsShield(CustomShield shield) {
        return shields.contains(shield);
    }

    /**
     * getAllFabricShields
     *
     * @return Every registered FabricShield instance at the time this is invoked.
     */
    public static CustomShield[] getAllFabricShields() {
        CustomShield[] result = new CustomShield[shields.size()];
        result = shields.toArray(result);
        return result;
    }

    public static ShieldEnchantment[] getAllShieldEnchantments() {
        ShieldEnchantment[] result = new ShieldEnchantment[enchantments.size()];
        result = enchantments.toArray(result);
        return result;
    }

    public static boolean isFabricShield(Item shield) {
        return shield instanceof CustomShield;
    }

    public static boolean isShieldEnchantment(Enchantment enchantment) {
        return enchantment instanceof ShieldEnchantment;
    }
}
