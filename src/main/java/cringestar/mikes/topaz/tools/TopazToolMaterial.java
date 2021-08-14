package cringestar.mikes.topaz.tools;

import cringestar.mikes.topaz.MikesTopaz;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class TopazToolMaterial implements ToolMaterial {

    public static final TopazToolMaterial INSTANCE = new TopazToolMaterial();

    @Override
    public int getDurability() {
        return 640;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 40.0F;
    }

    @Override
    public float getAttackDamage() {
        return 3.5F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 3;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(MikesTopaz.TOPAZ);
    }
}
