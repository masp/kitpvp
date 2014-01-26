package masp.plugins.kitpvp.config.utility.meta;

import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherMetaFactory implements MetaFactory {

	@Override
	public ItemMeta createMeta(ItemMeta meta, ConfigurationSection section) {
		if (!(meta instanceof LeatherArmorMeta)) {
			return meta;
		}
		LeatherArmorMeta armorMeta = (LeatherArmorMeta) meta;
		armorMeta.setColor(section.getColor("color", Color.fromRGB(0xA06540)));
		return armorMeta;
	}

}
