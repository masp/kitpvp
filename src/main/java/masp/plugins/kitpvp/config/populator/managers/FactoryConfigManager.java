package masp.plugins.kitpvp.config.populator.managers;

import java.util.HashMap;
import java.util.Map;

import masp.plugins.kitpvp.config.populator.factories.ConfigFactory;

import org.bukkit.configuration.ConfigurationSection;

public class FactoryConfigManager<T> implements ConfigManager<T> {

	private Map<String, ConfigFactory<T>> factories;

	public FactoryConfigManager() {
		factories = new HashMap<String, ConfigFactory<T>>();
	}
	
	@Override
	public T create(String type, ConfigurationSection section) {
		if (!factories.containsKey(type)) {
			return null;
		}

		return factories.get(type).create(section);
	}

	public void addFactory(String type, ConfigFactory<T> factory) {
		factories.put(type, factory);
	}

}
