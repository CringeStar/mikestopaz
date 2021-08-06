package cringestar.mikes.topaz;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TopazShovelItem extends ShovelItem {
    public TopazShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
            miner.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 300, 1));
            miner.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 1));
            miner.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 300, 1));
            miner.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0));
        return super.postMine(stack,world,state,pos,miner);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.hasStatusEffect(StatusEffects.SLOWNESS)){
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1));
        } else {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0));
        }

        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 1));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 1));


        return super.postHit(stack, target, attacker);
    }
}

