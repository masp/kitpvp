package masp.plugins.kitpvp.kit.kits.effects;

import masp.plugins.kitpvp.player.KitPlayer;

public interface KitEffect {

	public void apply(final KitPlayer player);

	public void cease(final KitPlayer player);

}
