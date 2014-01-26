package masp.plugins.kitpvp.config.utility.meta;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class BookMetaFactory implements MetaFactory {

	@Override
	public ItemMeta createMeta(ItemMeta meta, ConfigurationSection section) {
		if (!(meta instanceof BookMeta)) {
			return meta;
		}
		// Guarunteed to be a book meta
		BookMeta bMeta = (BookMeta) meta;
		bMeta.setTitle(section.getString("title", "Default Title"));
		bMeta.setAuthor(section.getString("author", "The Owner"));
		List<String> pages = section.getStringList("pages");
		if (pages == null) pages = new ArrayList<String>();
		bMeta.addPage(pages.toArray(new String[pages.size()-1]));	

		return bMeta;
	}

}
