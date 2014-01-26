package masp.plugins.kitpvp.config.utility.meta;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantedBookMetaFactory implements MetaFactory {

	@Override
	public ItemMeta createMeta(ItemMeta meta, ConfigurationSection section) {
		if (!(meta instanceof EnchantmentStorageMeta)) {
			return meta;
		}

		EnchantmentStorageMeta storageMeta = (EnchantmentStorageMeta) meta;
		for (String enchantmentName : section.getKeys(false)) {
			Enchantment enchantment = Enchantment.getByName(enchantmentName.toUpperCase());
			if (enchantment == null) continue;
			storageMeta.addStoredEnchant(enchantment, section.getInt(enchantmentName + ".level", 1), true);
		}
		return storageMeta;
	}

}
