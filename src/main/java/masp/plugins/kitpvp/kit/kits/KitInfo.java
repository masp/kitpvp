package masp.plugins.kitpvp.kit.kits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import masp.plugins.kitpvp.kit.valid.KitValidator;

import org.bukkit.inventory.ItemStack;

public final class KitInfo {

	private String name;
	private String description;
	private List<KitValidator> validators;
	private ItemStack icon;

	public KitInfo(String name, String description, ItemStack icon, List<KitValidator> validators) {
		this.name = name;
		this.description = description;
		this.icon = icon.clone();

		if (validators == null) validators = new ArrayList<KitValidator>();
		this.validators = Collections.unmodifiableList(validators);
	}

	public String getDisplayName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ItemStack getIcon() {
		return icon.clone();
	}

	public List<KitValidator> getValidators() {
		return validators;
	}

}
