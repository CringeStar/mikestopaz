package cringestar.mikes.topaz.shield;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldEnchantment;
import cringestar.mikes.topaz.MikesTopaz;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class ShieldBashEnchantment extends FabricShieldEnchantment {

    public ShieldBashEnchantment(Rarity weight) {
        super(weight);
    }

    @Override
    public int getMinPower(int level) {
        return level * 7 + 5;
    }

    @Override
    public int getMaxPower(int level) {
        return level * 10;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof ShieldFlingEnchantment) && super.canAccept(other);
    }
    @Override
    public boolean isAcceptableItem(ItemStack item) {
        return item.isOf(MikesTopaz.TOPAZ_SHIELD);
    }
}

