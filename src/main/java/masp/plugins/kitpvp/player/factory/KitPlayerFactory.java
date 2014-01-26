package masp.plugins.kitpvp.player.factory;

import masp.plugins.kitpvp.player.KitPlayer;
import masp.plugins.kitpvp.player.attributes.PlayerFactoryVisitor;

import org.bukkit.entity.Player;

public interface KitPlayerFactory {

	public KitPlayer createPlayer(final Player player);
	
	public void destroyPlayer(final KitPlayer player);

	public void visit(PlayerFactoryVisitor loader);

}
