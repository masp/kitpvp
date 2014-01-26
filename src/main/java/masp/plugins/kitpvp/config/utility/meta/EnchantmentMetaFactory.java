package masp.plugins.kitpvp.config.utility.meta;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantmentMetaFactory implements MetaFactory {

	private static Map<String, Enchantment> enchantments = new HashMap<String, Enchantment>();

	static {
		// Protection
		enchantments.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
		enchantments.put("fire_protection", Enchantment.PROTECTION_FIRE);
		enchantments.put("feather_falling", Enchantment.PROTECTION_FALL);
		enchantments.put("blast_protection", Enchantment.PROTECTION_EXPLOSIONS);
		enchantments.put("projectile_protection", Enchantment.PROTECTION_PROJECTILE);
		enchantments.put("respiration", Enchantment.OXYGEN);
		enchantments.put("aqua_affinity", Enchantment.WATER_WORKER);
		enchantments.put("thorns", Enchantment.THORNS);

		// Weapons
		enchantments.put("sharpness", Enchantment.DAMAGE_ALL);
		enchantments.put("smite", Enchantment.DAMAGE_UNDEAD);
		enchantments.put("bane_of_arthropods", Enchantment.DAMAGE_ARTHROPODS);
		enchantments.put("knockback", Enchantment.KNOCKBACK);
		enchantments.put("fire_aspect", Enchantment.FIRE_ASPECT);
		enchantments.put("looting", Enchantment.LOOT_BONUS_MOBS);
		
		// Tools
		enchantments.put("efficiency", Enchantment.DIG_SPEED);
		enchantments.put("silk_touch", Enchantment.SILK_TOUCH);
		enchantments.put("unbreaking", Enchantment.DURABILITY);
		enchantments.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
		
		// Bow
		enchantments.put("power", Enchantment.ARROW_DAMAGE);
		enchantments.put("punch", Enchantment.ARROW_KNOCKBACK);
		enchantments.put("flame", Enchantment.ARROW_FIRE);
		enchantments.put("infinity", Enchantment.ARROW_INFINITE);

		// Fishing
		enchantments.put("luck_of_the_sea", Enchantment.LUCK);
		enchantments.put("lure", Enchantment.LURE);	
	}

	@Override
	public ItemMeta createMeta(ItemMeta meta, ConfigurationSection section) {
		for (String enchantName : section.getKeys(false)) {
			Enchantment enchantment = getEnchantment(enchantName);
			if (enchantment == null)
				continue;
			int level = section.getInt(enchantName + ".level", 1);
			meta.addEnchant(enchantment, level, true);
		}
		return meta;
	}

	public Enchantment getEnchantment(String name) {
		return enchantments.get(name.toLowerCase());
	}

}
