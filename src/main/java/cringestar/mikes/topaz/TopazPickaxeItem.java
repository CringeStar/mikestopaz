package cringestar.mikes.topaz;

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
    if (!world.isClient) {
        miner.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 300, 1));
        miner.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 1));
        miner.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 300, 1));
        miner.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0));

    }
    return super.postMine(stack,world,state,pos,miner);
}

}
