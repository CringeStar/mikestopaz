package cringestar.mikes.topaz.shield;

import cringestar.mikes.topaz.MikesTopaz;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ShieldFlingEvent extends ShieldEvent{


    /**
     * Shield Event - contains the methods to be fired for a shield or shield enchantment with special effects.
     *
     * @param usesOnBlockDamage - Whether the event will use the onBlockDamage method
     * @param usesOnDisable     - Whether the event will use the onDisable method
     * @param usesWhileHolding  - Whether the event will use the whileHolding method
     */
    public ShieldFlingEvent(boolean usesOnBlockDamage, boolean usesOnDisable, boolean usesWhileHolding) {
        super(usesOnBlockDamage, usesOnDisable, usesWhileHolding);
    }


public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }


@Override
public void onDisable(PlayerEntity defender, int level, Hand hand, ItemStack shield) {
        MikesTopaz.logger.warn("the method works");
        float headYaw = defender.getHeadYaw();
        double launchX = 0;
        double launchZ = 0;
        int i = this.getMaxUseTime(shield);
        World world = defender.world;
        if (i >= 10) {
            if (!world.isClient) {
                if (headYaw <= -90 && headYaw >= -180){
                    launchX = 5D;
                    launchZ = -5D;
                }

                if (headYaw <= 0 && headYaw >= -    90){
                    launchX = 5D;
                    launchZ = 5D;
                }
                if (headYaw <= 90 && headYaw >= 0){
                    launchX = -5D;
                    launchZ = 5D;
                }
                if (headYaw <= 180 && headYaw >= 90){
                    launchX = -5D;
                    launchZ = -5D;
                }
                shield.damage(5, defender, (p) -> p.sendToolBreakStatus(defender.getActiveHand()));
                    defender.addVelocity(launchX, 1D, launchZ);
                    MikesTopaz.logger.warn("the thing itself works");
                    if (world.getDimension().isUltrawarm()){
                        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 300, 1));
                        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 300, 1));
                        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 80, 1));
                    }
                    if (!world.getDimension().isUltrawarm()){
                        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 300, 1));
                        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 60, 0));
                        defender.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 450, 1));

                    }
                }
            }
        }
}







