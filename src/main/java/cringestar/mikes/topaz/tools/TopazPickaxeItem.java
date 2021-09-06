package cringestar.mikes.topaz.tools;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TopazPickaxeItem extends PickaxeItem {
    public TopazPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

@Override
public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
    miner.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 3));
    miner.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 1));
    return super.postMine(stack,world,state,pos,miner);
}

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.hasStatusEffect(StatusEffects.SLOWNESS)){
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1));
        } else {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0));
        }

        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 3));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 1));


        return super.postHit(stack, target, attacker);
    }
}
