package cringestar.mikes.topaz;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class TopazSwordItem extends SwordItem {
    public TopazSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 300, 1));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 300, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0));
        return true;
    }
}
