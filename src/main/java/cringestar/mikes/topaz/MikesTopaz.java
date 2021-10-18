package cringestar.mikes.topaz;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldDisabledCallback;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldEnchantment;
import com.google.common.collect.ImmutableList;
import cringestar.mikes.topaz.armor.TopazArmorMaterial;
import cringestar.mikes.topaz.molds.AxeMoldBlock;
import cringestar.mikes.topaz.molds.HoeMoldBlock;
import cringestar.mikes.topaz.molds.PickaxeMoldBlock;
import cringestar.mikes.topaz.molds.SwordMoldBlock;
import cringestar.mikes.topaz.shield.ShieldBashEnchantment;
import cringestar.mikes.topaz.shield.ShieldFlingEnchantment;
import cringestar.mikes.topaz.tools.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("ALL")

public class MikesTopaz implements ModInitializer {


	public static final Logger logger = LogManager.getLogger("MikesTopaz");
	
	public static final Item TOPAZ = new Item(new Item.Settings());
	
	public static final Block TOPAZ_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(6.0F, 5.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

	public static final Block TOPAZ_ORE = new TopazOreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
	
	public static final Block DEEPSLATE_TOPAZ_ORE = new TopazOreBlock(FabricBlockSettings.of(Material.STONE).strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

	public static final Item TOPAZ_COVERED_MELON = new Item(new Item.Settings().food(TopazFoodComponents.TOPAZ_COVERED_MELON));

	public static final Item MELTY_TOPAZ_COVERED_MELON = new Item(new Item.Settings().food(TopazFoodComponents.MELTY_TOPAZ_COVERED_MELON));

	public static final Item TOPAZ_ENRICHED_MELON = new Item(new Item.Settings().food(TopazFoodComponents.TOPAZ_ENRICHED_MELON));

	public static final Item MELTY_TOPAZ_ENRICHED_MELON = new Item(new Item.Settings().food(TopazFoodComponents.MELTY_TOPAZ_ENRICHED_MELON));

	public static final Block DECORATIVE_TOPAZ_ORE = new Block(FabricBlockSettings.of(Material.SPONGE).strength(0.6F, 0.6F).breakByTool(FabricToolTags.HOES).breakByHand(true).sounds(BlockSoundGroup.NETHER_STEM));

	public static final Block DECORATIVE_DEEPSLATE_TOPAZ_ORE = new Block(FabricBlockSettings.of(Material.SPONGE).strength(0.6F, 0.6F).breakByTool(FabricToolTags.HOES).breakByHand(true).sounds(BlockSoundGroup.NETHER_STEM));
	
	public static final Block HEATER = new HeaterBlock(FabricBlockSettings.of(Material.METAL).strength(2.5F, 2.5F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());

	public static final Block SUPER_CONTAINER = new Block(FabricBlockSettings.of(Material.METAL).strength(2.5F, 2.5F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());

	public static final Block CONTAINED_COMPACT_LAVA = new Block(FabricBlockSettings.of(Material.METAL).strength(2.5F, 2.5F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

	public static final Block PICKAXE_MOLD = new PickaxeMoldBlock(FabricBlockSettings.of(Material.STONE).strength(2.5F, 7.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool().nonOpaque().ticksRandomly());

	public static final Block AXE_MOLD = new AxeMoldBlock(FabricBlockSettings.of(Material.STONE).strength(2.5F, 7.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool().nonOpaque().ticksRandomly());

	public static final Block SWORD_MOLD = new SwordMoldBlock(FabricBlockSettings.of(Material.STONE).strength(2.5F, 7.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool().nonOpaque().ticksRandomly());

	public static final Block HOE_MOLD = new HoeMoldBlock(FabricBlockSettings.of(Material.STONE).strength(2.5F, 7.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool().nonOpaque().ticksRandomly());

	public static final Block MOLD_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(2.5F, 7.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool());

	public static final Block MOLD_MATERIAL = new MoldMaterial(FabricBlockSettings.of(Material.STONE).strength(2.5F, 7.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool().nonOpaque());

	public static final Item PICKAXE_HEAD_SHAPE = new Item(new Item.Settings());

	public static final Item AXE_HEAD_SHAPE = new Item(new Item.Settings());

	public static final Item HOE_HEAD_SHAPE = new Item(new Item.Settings());

	public static final Item SHOVEL_HEAD_SHAPE = new Item(new Item.Settings());

	public static final Item SWORD_HEAD_SHAPE = new Item(new Item.Settings());

	public static final Item TOPAZ_PICKAXE_HEAD = new Item(new Item.Settings());

	public static final Item TOPAZ_AXE_HEAD = new Item(new Item.Settings());

	public static final Item TOPAZ_HOE_HEAD = new Item(new Item.Settings());

	public static final Item TOPAZ_SHOVEL_HEAD = new Item(new Item.Settings());

	public static final Item TOPAZ_SWORD_HEAD = new Item(new Item.Settings());

	public static ToolItem TOPAZ_PICKAXE = new TopazPickaxeItem(TopazToolMaterial.INSTANCE, 1, -2.8F, new Item.Settings());

	public static ToolItem TOPAZ_SWORD = new TopazSwordItem(TopazToolMaterial.INSTANCE, 1, -1.6F, new Item.Settings());

	public static ToolItem TOPAZ_AXE = new TopazAxeItem(TopazToolMaterial.INSTANCE, 2, -2.5F, new Item.Settings());

	public static ToolItem TOPAZ_SHOVEL = new TopazShovelItem(TopazToolMaterial.INSTANCE, 1.5F, -3.0F, new Item.Settings());

	public static ToolItem TOPAZ_HOE = new TopazHoeItem(TopazToolMaterial.INSTANCE, -3, -0.5F, new Item.Settings());

    public static final Item TOPAZ_SHIELD = new FabricBannerShieldItem(new FabricItemSettings().maxDamage(640), 80, 5, MikesTopaz.TOPAZ);

	public static final EntityModelLayer TOPAZ_SHIELD_MODEL_LAYER = new EntityModelLayer(new Identifier("mikestopaz", "topaz_shield"),"main");

	public static final ArmorMaterial TOPAZ_ARMOR_MATERIAL = new TopazArmorMaterial();

	public static final Item TOPAZ_HELMET = new ArmorItem(TOPAZ_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings());

	public static final Item TOPAZ_CHESTPLATE = new ArmorItem(TOPAZ_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings());

	public static final Item TOPAZ_LEGGINGS = new ArmorItem(TOPAZ_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings());

	public static final Item TOPAZ_BOOTS = new ArmorItem(TOPAZ_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings());

	public static final FabricShieldEnchantment SHIELD_BASH = new ShieldBashEnchantment(Enchantment.Rarity.VERY_RARE);

	public static final FabricShieldEnchantment SHIELD_FLING = new ShieldFlingEnchantment(Enchantment.Rarity.UNCOMMON);

	private static ConfiguredFeature<?, ?> TOPAZ_ORE_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, TOPAZ_ORE.getDefaultState()),
	OreFeatureConfig.createTarget(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, DEEPSLATE_TOPAZ_ORE.getDefaultState())),
	3))
	.decorate(Decorator.RANGE
	.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(0), YOffset.fixed(32)))))
	.spreadHorizontally()
	.repeat(1);

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
		new Identifier("mikestopaz","general"))
		.icon(() -> new ItemStack(MikesTopaz.TOPAZ))
		.appendItems(stacks -> {
			stacks.add(new ItemStack(MikesTopaz.TOPAZ));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_BLOCK));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_ORE));
			stacks.add(new ItemStack(MikesTopaz.DEEPSLATE_TOPAZ_ORE));
			stacks.add(new ItemStack(MikesTopaz.DECORATIVE_TOPAZ_ORE));
			stacks.add(new ItemStack(MikesTopaz.DECORATIVE_DEEPSLATE_TOPAZ_ORE));
			stacks.add(new ItemStack(Items.GLISTERING_MELON_SLICE));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_COVERED_MELON));
			stacks.add(new ItemStack(MikesTopaz.MELTY_TOPAZ_COVERED_MELON));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_ENRICHED_MELON));
			stacks.add(new ItemStack(MikesTopaz.MELTY_TOPAZ_ENRICHED_MELON));
			stacks.add(new ItemStack(MikesTopaz.HEATER));
			stacks.add(new ItemStack(MikesTopaz.SUPER_CONTAINER));
			stacks.add(new ItemStack(MikesTopaz.CONTAINED_COMPACT_LAVA));
			stacks.add(new ItemStack(MikesTopaz.MOLD_BLOCK));
			stacks.add(new ItemStack(MikesTopaz.MOLD_MATERIAL));
			stacks.add(new ItemStack(MikesTopaz.PICKAXE_MOLD));
			stacks.add(new ItemStack(MikesTopaz.AXE_MOLD));
			stacks.add(new ItemStack(MikesTopaz.SWORD_MOLD));
			stacks.add(new ItemStack(MikesTopaz.HOE_MOLD));
			stacks.add(new ItemStack(MikesTopaz.PICKAXE_HEAD_SHAPE));
			stacks.add(new ItemStack(MikesTopaz.SWORD_HEAD_SHAPE));
			stacks.add(new ItemStack(MikesTopaz.AXE_HEAD_SHAPE));
			stacks.add(new ItemStack(MikesTopaz.SHOVEL_HEAD_SHAPE));
			stacks.add(new ItemStack(MikesTopaz.HOE_HEAD_SHAPE));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_PICKAXE_HEAD));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_SWORD_HEAD));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_AXE_HEAD));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_SHOVEL_HEAD));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_HOE_HEAD));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_PICKAXE));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_SWORD));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_AXE));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_SHOVEL));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_HOE));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_SHIELD));
			stacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(SHIELD_BASH, 1)));
			stacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(SHIELD_BASH, 2)));
			stacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(SHIELD_BASH, 3)));
			stacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(SHIELD_FLING, 1)));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_HELMET));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_CHESTPLATE));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_LEGGINGS));
			stacks.add(new ItemStack(MikesTopaz.TOPAZ_BOOTS));


		})
		.build();


	@Override
	public void onInitialize() {

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz"), TOPAZ);

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "topaz_block"), TOPAZ_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_block"), new BlockItem(TOPAZ_BLOCK, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "topaz_ore"), TOPAZ_ORE);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_ore"), new BlockItem(TOPAZ_ORE, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "deepslate_topaz_ore"), DEEPSLATE_TOPAZ_ORE);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "deepslate_topaz_ore"), new BlockItem(DEEPSLATE_TOPAZ_ORE, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "decorative_topaz_ore"), DECORATIVE_TOPAZ_ORE);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "decorative_topaz_ore"), new BlockItem(DECORATIVE_TOPAZ_ORE, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "decorative_deepslate_topaz_ore"), DECORATIVE_DEEPSLATE_TOPAZ_ORE);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "decorative_deepslate_topaz_ore"), new BlockItem(DECORATIVE_DEEPSLATE_TOPAZ_ORE, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "heater"), HEATER);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "heater"), new BlockItem(HEATER, new Item.Settings()));

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_covered_melon"), TOPAZ_COVERED_MELON);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "melty_topaz_covered_melon"), MELTY_TOPAZ_COVERED_MELON);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_enriched_melon"), TOPAZ_ENRICHED_MELON);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "melty_topaz_enriched_melon"), MELTY_TOPAZ_ENRICHED_MELON);

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "super_container"), SUPER_CONTAINER);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "super_container"), new BlockItem(SUPER_CONTAINER, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "contained_compact_lava"), CONTAINED_COMPACT_LAVA);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "contained_compact_lava"), new BlockItem(CONTAINED_COMPACT_LAVA, new Item.Settings().maxCount(1)));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "pickaxe_mold"), PICKAXE_MOLD);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "pickaxe_mold"), new BlockItem(PICKAXE_MOLD, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "axe_mold"), AXE_MOLD);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "axe_mold"), new BlockItem(AXE_MOLD, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "sword_mold"), SWORD_MOLD);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "sword_mold"), new BlockItem(SWORD_MOLD, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "hoe_mold"), HOE_MOLD);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "hoe_mold"), new BlockItem(HOE_MOLD, new Item.Settings()));


		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "mold_block"), MOLD_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "mold_block"), new BlockItem(MOLD_BLOCK, new Item.Settings()));

		Registry.register(Registry.BLOCK, new Identifier("mikestopaz", "mold_material"), MOLD_MATERIAL);
		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "mold_material"), new BlockItem(MOLD_MATERIAL, new Item.Settings()));

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "pickaxe_head_shape"), PICKAXE_HEAD_SHAPE);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "sword_head_shape"), SWORD_HEAD_SHAPE);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "axe_head_shape"), AXE_HEAD_SHAPE);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "shovel_head_shape"), SHOVEL_HEAD_SHAPE);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "hoe_head_shape"), HOE_HEAD_SHAPE);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_pickaxe_head"), TOPAZ_PICKAXE_HEAD);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_sword_head"), TOPAZ_SWORD_HEAD);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_axe_head"), TOPAZ_AXE_HEAD);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_shovel_head"), TOPAZ_SHOVEL_HEAD);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_hoe_head"), TOPAZ_HOE_HEAD);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_pickaxe"), TOPAZ_PICKAXE);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_sword"), TOPAZ_SWORD);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_axe"), TOPAZ_AXE);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_shovel"), TOPAZ_SHOVEL);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_hoe"), TOPAZ_HOE);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_shield"), TOPAZ_SHIELD);

		Registry.register(Registry.ENCHANTMENT, new Identifier("mikestopaz", "shield_fling"), SHIELD_FLING);

		Registry.register(Registry.ENCHANTMENT, new Identifier("mikestopaz", "shield_bash"),SHIELD_BASH);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_helmet"), TOPAZ_HELMET);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_chestplate"), TOPAZ_CHESTPLATE);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_leggings"), TOPAZ_LEGGINGS);

		Registry.register(Registry.ITEM, new Identifier("mikestopaz", "topaz_boots"), TOPAZ_BOOTS);

		ShieldBlockCallback.EVENT.register((defender, source, amount, hand, shield) -> {
			if (SHIELD_BASH.hasEnchantment(shield)){
				Entity attacker = source.getAttacker();
				float headYaw = defender.getHeadYaw();
				double level = EnchantmentHelper.getLevel(SHIELD_BASH, shield);
				double launchX = 0;
				double launchZ = 0;
				double mult = level-0.5;
				World world = defender.getEntityWorld();
				if (!world.isClient) {
					if (headYaw <= -90 && headYaw >= -180) {
						launchX = 1.5D;
						launchZ = -1.5D;
					}

					if (headYaw <= 0 && headYaw >= -90) {
						launchX = 1.5D;
						launchZ = 1.5D;
					}
					if (headYaw <= 90 && headYaw >= 0) {
						launchX = -1.5D;
						launchZ = 1.5D;
					}
					if (headYaw <= 180 && headYaw >= 90) {
						launchX = -1.5D;
						launchZ = -1.5D;
					}

					if (level > 1) {
						launchX = launchX * mult;
						launchZ = launchZ * mult;
					}
				assert attacker != null;
				if(defender instanceof PlayerEntity) {  //Defender should always be a player, but check anyways
					attacker.addVelocity(launchX, 1D, launchZ);
					attacker.damage(DamageSource.player((PlayerEntity) defender), 3.0F);
					shield.damage(5, defender, (p) -> p.sendToolBreakStatus(defender.getActiveHand()));
				} else {
					attacker.addVelocity(launchX, 1D, launchZ);
					attacker.damage(DamageSource.mob(defender), 3.0F);
					shield.damage(5, defender, (p) -> p.sendToolBreakStatus(defender.getActiveHand()));
				}
			}

		}
			return ActionResult.PASS;
		});

		ShieldDisabledCallback.EVENT.register((defender, hand, shield) -> {
			float headYaw = defender.getHeadYaw();
			double launchX = 0;
			double launchZ = 0;
			World world = defender.getEntityWorld();
				if (!world.isClient) {
					if (headYaw <= -90 && headYaw >= -180) {
						launchX = 5D;
						launchZ = -5D;
					}

					if (headYaw <= 0 && headYaw >= -90) {
						launchX = 5D;
						launchZ = 5D;
					}
					if (headYaw <= 90 && headYaw >= 0) {
						launchX = -5D;
						launchZ = 5D;
					}
					if (headYaw <= 180 && headYaw >= 90) {
						launchX = -5D;
						launchZ = -5D;
					}
					shield.damage(5, defender, (p) -> p.sendToolBreakStatus(defender.getActiveHand()));
					defender.addVelocity(launchX, 1D, launchZ);
					defender.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 300, 1));
					defender.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 300, 1));
					defender.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 1));
					defender.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 450, 1));
			}
				return ActionResult.PASS;
		});


			RegistryKey<ConfiguredFeature<?, ?>> topazOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("mikestopaz", "topaz_ore"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, topazOreOverworld.getValue(), TOPAZ_ORE_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, topazOreOverworld);


	}
}
