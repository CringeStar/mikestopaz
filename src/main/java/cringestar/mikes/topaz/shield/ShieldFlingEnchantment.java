package cringestar.mikes.topaz.shield;

public class ShieldFlingEnchantment extends ShieldEnchantment{
    public ShieldFlingEnchantment(Rarity weight, ShieldEvent event) {
        super(weight, event);
    }
    @Override
    public int getMinPower(int level) {
        return 1;
    }
    @Override
    public int getMaxLevel() {
        return 1;
    }
}
