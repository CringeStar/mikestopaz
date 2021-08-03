package cringestar.mikes.topaz;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
//import net.minecraft.item.ItemStack;

public class TopazFoodComponents {
    
  //  public boolean hasGlint(ItemStack stack) {return true;};

    public static final FoodComponent TOPAZ_COVERED_MELON = (new FoodComponent.Builder()).hunger(2).saturationModifier(.25F)
    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA , 140, 1), 1F)
    .build();
    
    public static final FoodComponent MELTY_TOPAZ_COVERED_MELON = (new FoodComponent.Builder()).hunger(10).saturationModifier(.6F)
    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 1), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 900, 0), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 0), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 1), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 900, 1), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 900, 0), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 1), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 1F)
    .alwaysEdible().build();

    public static final FoodComponent TOPAZ_ENRICHED_MELON = (new FoodComponent.Builder()).hunger(4).saturationModifier(.3F)
    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 3), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA , 280, 3), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE , 1, 1), 1F).build();
    
    public static final FoodComponent MELTY_TOPAZ_ENRICHED_MELON = (new FoodComponent.Builder()).hunger(20).saturationModifier(1.8F)
    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 3600, 2), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2400, 1), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 1), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 2400, 1), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2400, 3), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2400, 3), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 2100, 5), 1F)
    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 2100, 7), 1F)
    .alwaysEdible().build();
    

  }

