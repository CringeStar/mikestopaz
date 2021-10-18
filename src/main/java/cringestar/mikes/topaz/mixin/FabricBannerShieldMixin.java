package cringestar.mikes.topaz.mixin;


import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import cringestar.mikes.topaz.MikesTopaz;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin (FabricBannerShieldItem.class)
public class FabricBannerShieldMixin {

        @Inject(method = "<init>(Lnet/minecraft/item/Item$Settings;II[Lnet/minecraft/item/Item;)V", at = @At("TAIL"))
        private void addBashing(CallbackInfo ci) {
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                System.out.println("HEY IT ADDED THE THING LETS GOOOOO");
                FabricModelPredicateProviderRegistry.register(new Identifier("blocking"), (itemStack, clientWorld, livingEntity, i) ->
                        livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && EnchantmentHelper.getLevel(MikesTopaz.SHIELD_BASH, itemStack) != 0 ? 1.0F : 0.0F);
            }
        }
}
