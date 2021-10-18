package cringestar.mikes.topaz.shield;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldEnchantment;
import cringestar.mikes.topaz.MikesTopaz;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ShieldFlingEnchantment extends FabricShieldEnchantment {
    public ShieldFlingEnchantment(Rarity weight) {
        super(weight);
    }
    @Override
    public int getMinPower(int level) {
        return 1;
    }
    @Override
    public int getMaxLevel() {
        return 1;
    }
    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof ShieldBashEnchantment) && super.canAccept(other);
    }
    @Override
    public boolean isAcceptableItem(ItemStack item) {
        return item.isOf(MikesTopaz.TOPAZ_SHIELD);
    }
}
