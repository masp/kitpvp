package masp.plugins.kitpvp.config.populator;

import org.bukkit.configuration.ConfigurationSection;

public interface KitPopulatorFactory {

	public KitPopulator createPopulator(ConfigurationSection section);

}
