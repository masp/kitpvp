package masp.plugins.kitpvp.kit.kits.effects;

import java.util.ArrayList;

import masp.plugins.kitpvp.kit.kits.Kit;
import masp.plugins.kitpvp.kit.kits.KitInfo;
import masp.plugins.kitpvp.kit.valid.KitValidator;
import masp.plugins.kitpvp.player.KitPlayer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EmptyKit implements Kit {

	@Override
	public void renew(KitPlayer player) {
		// NOTHING
	}

	@Override
	public void suspend(KitPlayer player) {
		// NOTHING
	}

	@Override
	public void apply(KitPlayer player) {
		// NOTHING
	}

	@Override
	public void cease(KitPlayer player) {
		// NOTHING
	}

	@Override
	public KitInfo getInfo() {
		return new KitInfo("null", "null", new ItemStack(Material.AIR), new ArrayList<KitValidator>());
	}

}
