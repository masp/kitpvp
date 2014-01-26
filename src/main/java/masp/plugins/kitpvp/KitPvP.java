package masp.plugins.kitpvp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import masp.plugins.kitpvp.commands.KitCommand;
import masp.plugins.kitpvp.config.ConfigException;
import masp.plugins.kitpvp.config.KitManagerFactory;
import masp.plugins.kitpvp.config.PopulatorKitManagerFactory;
import masp.plugins.kitpvp.config.populator.factories.ManagerPopulatorFactory;
import masp.plugins.kitpvp.config.populator.managers.FactoryConfigManager;
import masp.plugins.kitpvp.events.RenewListener;
import masp.plugins.kitpvp.kit.identity.KitIdentityFactory;
import masp.plugins.kitpvp.kit.kits.KitManager;
import masp.plugins.kitpvp.kit.kits.effects.KitEffect;
import masp.plugins.kitpvp.kit.valid.KitValidator;
import masp.plugins.kitpvp.load.PluginPackage;
import masp.plugins.kitpvp.load.PluginPreLoadVisitor;
import masp.plugins.kitpvp.player.KitPlayerManager;
import masp.plugins.kitpvp.player.attributes.AttributeLoader;
import masp.plugins.kitpvp.player.defaults.KitAttributeDefault;
import masp.plugins.kitpvp.player.defaults.persist.PlayerPersister;
import masp.plugins.kitpvp.player.defaults.persist.YamlPersister;
import masp.plugins.kitpvp.player.factory.AttributeKitPlayerFactory;
import masp.plugins.kitpvp.player.factory.KitPlayerFactory;
import masp.plugins.kitpvp.player.managers.HandlerPlayerManager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPvP extends JavaPlugin {
	private static KitPvP instance;

	// This should be standard in Bukkit...
	private static List<PluginPreLoadVisitor> preLoaders;

	// Managers
	private HandlerPlayerManager plMan;
	private KitManager kitMan;

	// Load Factories
	private KitPlayerFactory plFactory;
	private FactoryConfigManager<KitValidator> valFactory;
	private FactoryConfigManager<KitEffect> effFactory;

	private KitIdentityFactory idFactory;

	private PlayerPersister persister;

	@Override
	public void onLoad() {
		instance = this;

		preLoaders = new ArrayList<PluginPreLoadVisitor>();
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}

		persister = new YamlPersister(new File(this.getDataFolder(), "players"));
		idFactory = new KitIdentityFactory();

		plFactory = new AttributeKitPlayerFactory(persister);				

		valFactory = new FactoryConfigManager<KitValidator>();
		effFactory = new FactoryConfigManager<KitEffect>();
	}

	@Override
	public void onEnable() {
		PluginPackage plgPackage = new PluginPackage(plFactory, valFactory, effFactory);
		for (PluginPreLoadVisitor visitor : preLoaders) {
			visitor.load(plgPackage);
		}

		plMan = new HandlerPlayerManager(plFactory);
		Bukkit.getPluginManager().registerEvents(new RenewListener(this, plMan), this);

		this.saveResource("kits.yml", false);
		File kitFile = new File(this.getDataFolder(), "kits.yml");

		KitManagerFactory manFactory = new PopulatorKitManagerFactory(new ManagerPopulatorFactory(valFactory, effFactory));	
		try {
			kitMan = manFactory.createKitManager(YamlConfiguration.loadConfiguration(kitFile));
		} catch (ConfigException ex) {
			this.getLogger().severe("Error in configuration file: kits.yml!");
			this.getLogger().severe("Error Message: " + ex.getMessage());
		}

		plFactory.visit(new AttributeLoader("kit", new KitAttributeDefault(kitMan, idFactory)));

		getCommand("kp").setExecutor(new KitCommand(kitMan, plMan));

		this.getLogger().info("Successfully loaded and enabled KitPvP plugin!");
	}

	@Override
	public void onDisable() {
		// Ahh, the nullification process...
		instance = null;
		preLoaders = null;

		this.getLogger().info("KitPvP successfully disabled!");
	}

	/**
	 * Will add a loader listener/visitor to the plugin
	 */
	public static void addPreLoader(PluginPreLoadVisitor visitor) {
		if (preLoaders == null)
			preLoaders = new ArrayList<PluginPreLoadVisitor>();
		preLoaders.add(visitor);
	}

	public static KitPlayerManager getPlayerManager() {
		return getInstance().plMan;
	}
	
	public static KitManager getKitManager() {
		return getInstance().kitMan;
	}

	public static KitPvP getInstance() {
		return instance;
	}

}
