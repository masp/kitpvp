package masp.plugins.kitpvp.config.utility.meta;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;

public class InfoMetaFactory implements MetaFactory {

	@Override
	public ItemMeta createMeta(ItemMeta meta, ConfigurationSection section) {
		meta.setDisplayName(section.getString("display-name"));
		meta.setLore(section.getStringList("lore"));
		return meta;
	}

}
