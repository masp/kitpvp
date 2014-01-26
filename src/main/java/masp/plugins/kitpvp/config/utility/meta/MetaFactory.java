package masp.plugins.kitpvp.config.utility.meta;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;

public interface MetaFactory {

	public ItemMeta createMeta(ItemMeta meta, ConfigurationSection section);

}
