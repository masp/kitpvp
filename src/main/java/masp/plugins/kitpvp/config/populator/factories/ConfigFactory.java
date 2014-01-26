package masp.plugins.kitpvp.config.populator.factories;

import org.bukkit.configuration.ConfigurationSection;

public interface ConfigFactory<T> {
	public T create(ConfigurationSection config);
}
