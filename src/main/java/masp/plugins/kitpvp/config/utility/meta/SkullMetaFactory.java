package masp.plugins.kitpvp.config.utility.meta;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullMetaFactory implements MetaFactory {

	@Override
	public ItemMeta createMeta(ItemMeta meta, ConfigurationSection section) {
		if (!(meta instanceof SkullMeta)) {
			return meta;
		}
		SkullMeta skull = (SkullMeta) meta;
		skull.setOwner(section.getString("owner", "Notch"));
		return skull;
	}
}
