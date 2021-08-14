package cringestar.mikes.topaz;

import cringestar.mikes.topaz.shield.ShieldEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TopazShieldEvent extends ShieldEvent {


    /**
     * Shield Event - contains the methods to be fired for a shield or shield enchantment with special effects.
     *
     * @param usesOnBlockDamage - Whether or not the event will use the onBlockDamage method
     * @param usesOnDisable     - Whether or not the event will use the onDisable method
     * @param usesWhileHolding  - Whether or not the event will use the whileHolding method
     */
    public TopazShieldEvent(boolean usesOnBlockDamage, boolean usesOnDisable, boolean usesWhileHolding) {
        super(usesOnBlockDamage, usesOnDisable, usesWhileHolding);
    }


    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }




    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            if (i >= 10) {
                int j = EnchantmentHelper.getFireAspect(playerEntity);
                    if (!world.isClient) {
                        stack.damage(5, playerEntity, (p) -> p.sendToolBreakStatus(user.getActiveHand()));
                        if (j == 0) {

                        user.addVelocity(5D,5D,5D);

                        }
                    }
            }
        }
    }

}




