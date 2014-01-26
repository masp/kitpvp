package masp.plugins.kitpvp.config;

import masp.plugins.kitpvp.kit.kits.KitManager;

import org.bukkit.configuration.ConfigurationSection;

public interface KitManagerFactory {

	public KitManager createKitManager(ConfigurationSection config) throws ConfigException;

}
