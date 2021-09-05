package cringestar.mikes.topaz.shield;

public class ShieldBashEnchantment extends ShieldEnchantment{

    public ShieldBashEnchantment(Rarity weight, ShieldEvent event) {
        super(weight, event);
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }
    @Override
    public int getMaxLevel() {
        return 3;
    }
}
