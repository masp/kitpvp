package masp.plugins.kitpvp.kit.kits.effects;

import masp.plugins.kitpvp.player.KitPlayer;

/**
 * When the player for example leaves, some effects need to perform
 * special actions when the player has the kit, and the effect, and has
 * had it, but now needs the effects of it to be re-enabled, i.e. abilities
 */
public interface RenewableEffect {
	
	public void suspend(final KitPlayer player);

	public void renew(final KitPlayer player);

}
