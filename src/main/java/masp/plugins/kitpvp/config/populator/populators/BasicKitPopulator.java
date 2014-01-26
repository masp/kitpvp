package masp.plugins.kitpvp.config.populator.populators;

import java.util.ArrayList;
import java.util.List;

import masp.plugins.kitpvp.config.ConfigException;
import masp.plugins.kitpvp.config.populator.KitPopulator;
import masp.plugins.kitpvp.config.populator.managers.FactoryConfigManager;
import masp.plugins.kitpvp.config.utility.ItemStackFactory;
import masp.plugins.kitpvp.kit.identity.KitIdentity;
import masp.plugins.kitpvp.kit.identity.identities.NameIdentity;
import masp.plugins.kitpvp.kit.kits.Kit;
import masp.plugins.kitpvp.kit.kits.KitInfo;
import masp.plugins.kitpvp.kit.kits.effects.BasicKit;
import masp.plugins.kitpvp.kit.kits.effects.KitEffect;
import masp.plugins.kitpvp.kit.valid.KitValidator;
import masp.plugins.kitpvp.kit.valid.valids.AlreadyHasValidator;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class BasicKitPopulator implements KitPopulator {
	
	private FactoryConfigManager<KitValidator> valFact;
	private FactoryConfigManager<KitEffect> effFact;
	private ConfigurationSection config;

	public BasicKitPopulator(FactoryConfigManager<KitValidator> valFact,
			FactoryConfigManager<KitEffect> effFact,
			ConfigurationSection section) {
		this.config = section;
		this.valFact = valFact;
		this.effFact = effFact;
	}

	@Override
	public KitIdentity getIdentity() {
		return new NameIdentity(config.getName());
	}

	@Override
	public Kit getKit() throws ConfigException {

		List<KitValidator> validators = loadSection(valFact, config.getConfigurationSection("requirements"));
		validators.add(new AlreadyHasValidator(getIdentity()));
		String name = config.getString("name", config.getName());
		String desc = config.getString("desc", "Default description! :(");
		ItemStack icon;
		if (config.isConfigurationSection("icon")) {
			icon = ItemStackFactory.createItemStack(config.getConfigurationSection("icon"));
		} else {
			icon = new ItemStack(Material.matchMaterial(config.getString("icon", "diamond")));
		}
		KitInfo info = new KitInfo(name, desc, icon, validators);
		
		List<KitEffect> effects = loadSection(effFact, config.getConfigurationSection("effects"));
		return new BasicKit(info, effects);
	}

	private <T> List<T> loadSection(FactoryConfigManager<T> manager, ConfigurationSection section) throws ConfigException {
		if (section == null) return new ArrayList<T>();
		List<T> retList = new ArrayList<T>();
		for (String key : section.getKeys(false)) {
			T val = manager.create(key, section.getConfigurationSection(key));
			if (val == null)
				throw new ConfigException("Unable to create value by name " + key + " in path " + section.getCurrentPath());
			retList.add(val);
		}
		return retList;
	}

}
