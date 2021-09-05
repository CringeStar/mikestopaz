package cringestar.mikes.topaz.shield;

import cringestar.mikes.topaz.MikesTopaz;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ShieldBashEvent extends ShieldEvent{


    /**
     * Shield Event - contains the methods to be fired for a shield or shield enchantment with special effects.
     *
     * @param usesOnBlockDamage - Whether the event will use the onBlockDamage method
     * @param usesOnDisable     - Whether the event will use the onDisable method
     * @param usesWhileHolding  - Whether the event will use the whileHolding method
     */
    public ShieldBashEvent(boolean usesOnBlockDamage, boolean usesOnDisable, boolean usesWhileHolding) {
        super(usesOnBlockDamage, usesOnDisable, usesWhileHolding);
    }


public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }


@Override
public void onBlockDamage(LivingEntity defender, DamageSource source, float amount, int level, Hand hand, ItemStack shield){
        MikesTopaz.logger.warn("the method works");
        float headYaw = defender.getHeadYaw();
        double launchX = 0;
        double launchZ = 0;
        double mult = level-0.5;
        int i = this.getMaxUseTime(shield);
        World world = defender.world;
        Entity attacker = source.getAttacker();
        if (i >= 10) {
            if (!world.isClient) {
                if (headYaw <= -90 && headYaw >= -180){
                    launchX = 1.5D;
                    launchZ = -1.5D;
                }

                if (headYaw <= 0 && headYaw >= -90){
                    launchX = 1.5D;
                    launchZ = 1.5D;
                }
                if (headYaw <= 90 && headYaw >= 0){
                    launchX = -1.5D;
                    launchZ = 1.5D;
                }
                if (headYaw <= 180 && headYaw >= 90){
                    launchX = -1.5D;
                    launchZ = -1.5D;
                }

                if (level > 1){
                    launchX = launchX*mult;
                    launchZ = launchZ*mult;
                }
                if (attacker instanceof LivingEntity) {
                    if (defender instanceof PlayerEntity) {
                        attacker.addVelocity(launchX, 1D, launchZ);
                        ((PlayerEntity) defender).addVelocity(launchX, 0D, launchZ);
                        attacker.damage(DamageSource.GENERIC, 6.0F);
                        shield.damage(5, defender, (p) -> p.sendToolBreakStatus(defender.getActiveHand()));
                        MikesTopaz.logger.warn("the launch works");
                    }
                }
                }
            }
        }
}







