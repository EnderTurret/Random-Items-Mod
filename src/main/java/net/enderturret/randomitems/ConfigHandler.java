package net.enderturret.randomitems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Config(modid = Reference.MOD_ID, type = Config.Type.INSTANCE)
public class ConfigHandler {

	@Comment("Whether tesseracts apply effects.")
	public static boolean tesseractsEnabled = true;

	@Comment("Whether FLARD does anything when right-clicked.")
	public static boolean flardEnabled = true;

	@Comment("Whether questionable cheese explodes.")
	public static boolean questionableCheeseEnabled = true;

	@Comment("Whether puffballs apply effects.")
	public static boolean puffballEffectsEnabled = true;

	@Comment("Whether the brown puffball applies its effect.")
	public static boolean brownPuffballEnabled = true;

	@Comment("Whether the stone chisels can be used.")
	public static boolean stoneChiselEnabled = true;

	@Comment("Whether /repair is enabled")
	@Config.RequiresWorldRestart
	public static boolean repairCommandEnabled = true;

	@Comment("Whether the anti-gravity enchant applies its effect.")
	public static boolean antiGravityEnabled = true;

	@Comment("Whether core crafting is enabled.")
	public static boolean coreCraftingEnabled = true;

	@Comment("For controlling individual effects.")
	public static SubCategoryFLARD flardEffects = new SubCategoryFLARD();

	public static void bake() {
		flardEffects.refreshBlacklist();
	}

	public static class SubCategoryFLARD {

		@Config.Ignore
		private List<ResourceLocation> blockKeys = null;
		@Config.Ignore
		private List<ResourceLocation> bakedBlockBlacklist = null;

		@Comment("Whether the poison effect is enabled.")
		public boolean poisonEffect = true;

		@Comment("Whether the offhand enchant is enabled.")
		public boolean offhandEnchantEffect = true;

		@Comment("Whether the inventory drop effect is enabled.")
		public boolean inventoryDropEffect = true;

		@Comment("Whether the lightning strike effect is enabled.")
		public boolean lightningEffect = true;

		@Comment("Whether the diamond effect is enabled. (Gives the player one diamond.)")
		public boolean diamondEffect = true;

		@Comment("Whether the hole effect is enabled. (Removes the block under the player.)")
		public boolean holeEffect = true;

		@Comment("Whether the explosion effect is enabled.")
		public boolean explosionEffect = true;

		@Comment("Whether the XP effect is enabled. (Gives the player 0-9 xp points.)")
		public boolean xpEffect = true;

		@Comment("Whether the chest loot table effect is enabled. (Modifies an empty chest's loot table and respawns loot.)")
		public boolean chestLootEffect = true;

		@Comment("Whether the fire effect is enabled.")
		public boolean fireEffect = true;

		@Comment("Whether the puddle effect is enabled.")
		public boolean puddleEffect = true;

		@Comment("Whether the cobweb effect is enabled.")
		public boolean cobwebEffect = true;

		@Comment("Whether the block change effect is enabled. (Changes the block under the player to a random block.)")
		public boolean blockChangeEffect = true;

		@Comment("The blacklist for various effects.")
		public String[] blockBlacklist = {"minecraft:bedrock", "minecraft:repeating_command_block", "minecraft:command_block",
				"minecraft:chain_command_block", "minecraft:barrier", "minecraft:end_portal_frame",
				"minecraft:portal", "minecraft:mob_spawner", "minecraft:structure_block", "minecraft:structure_void"};

		public List<ResourceLocation> getBlockKeys() {
			if (blockKeys == null) refreshBlacklist();

			return blockKeys;
		}

		public List<ResourceLocation> getBlockBlacklist() {
			if (bakedBlockBlacklist == null) refreshBlacklist();

			return bakedBlockBlacklist;
		}

		public boolean isBlacklisted(ResourceLocation registryName) {
			return getBlockBlacklist().contains(registryName);
		}

		public void refreshBlacklist() {
			bakedBlockBlacklist = Arrays.stream(blockBlacklist).map(ResourceLocation::new).collect(Collectors.toList());
			blockKeys = ForgeRegistries.BLOCKS.getKeys().stream().filter(key -> !bakedBlockBlacklist.contains(key)).collect(Collectors.toList());
		}
	}
}