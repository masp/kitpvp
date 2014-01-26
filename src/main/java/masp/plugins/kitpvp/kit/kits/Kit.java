package masp.plugins.kitpvp.kit.kits;

import masp.plugins.kitpvp.kit.kits.effects.KitEffect;
import masp.plugins.kitpvp.kit.kits.effects.RenewableEffect;

public interface Kit extends KitEffect, RenewableEffect {

	public KitInfo getInfo();

}
