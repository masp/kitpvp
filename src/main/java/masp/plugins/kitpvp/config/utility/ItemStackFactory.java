package masp.plugins.kitpvp.config.utility;

import java.util.HashMap;
import java.util.Map;

import masp.plugins.kitpvp.config.utility.meta.BookMetaFactory;
import masp.plugins.kitpvp.config.utility.meta.EnchantedBookMetaFactory;
import masp.plugins.kitpvp.config.utility.meta.EnchantmentMetaFactory;
import masp.plugins.kitpvp.config.utility.meta.InfoMetaFactory;
import masp.plugins.kitpvp.config.utility.meta.LeatherMetaFactory;
import masp.plugins.kitpvp.config.utility.meta.MetaFactory;
import masp.plugins.kitpvp.config.utility.meta.SkullMetaFactory;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackFactory {

	private static Map<String, MetaFactory> factories = new HashMap<String, MetaFactory>();

	static {
		factories.put("book", new BookMetaFactory());
		factories.put("skull", new SkullMetaFactory());
		factories.put("leather-armor", new LeatherMetaFactory());
		factories.put("enchanted-book", new EnchantedBookMetaFactory());
		factories.put("enchantments", new EnchantmentMetaFactory());
		factories.put("info", new InfoMetaFactory());
	}

	public static ItemStack createItemStack(ConfigurationSection config) {
		Material material = Material.matchMaterial(config.getString("type", "APPLE"));
		if (material == null)
			material = Material.APPLE;
		int amount = config.getInt("amount", 1);
		short damage = (short) config.getInt("damage", 0);
		ItemStack item = new ItemStack(material, amount, damage);
		ConfigurationSection metaSec = config.getConfigurationSection("meta");
		if (metaSec != null) {
			ItemMeta meta = item.getItemMeta();
			for (String type : metaSec.getKeys(false)) {
				if (factories.containsKey(type)) {
					meta = factories.get(type).createMeta(meta, metaSec.getConfigurationSection((type)));
				}
			}
			item.setItemMeta(meta);
		}
		return item;
	}
}
