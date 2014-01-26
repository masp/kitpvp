package masp.plugins.kitpvp.config.populator.managers;

import org.bukkit.configuration.ConfigurationSection;

public interface ConfigManager<T> {
	public T create(String type, ConfigurationSection config);
}
