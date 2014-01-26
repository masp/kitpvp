package masp.plugins.kitpvp.player.managers;

import java.util.HashMap;
import java.util.Map;

import masp.plugins.kitpvp.events.RenewListener.PlayerHandler;
import masp.plugins.kitpvp.player.KitPlayer;
import masp.plugins.kitpvp.player.KitPlayerManager;
import masp.plugins.kitpvp.player.factory.KitPlayerFactory;

import org.bukkit.entity.Player;

public final class HandlerPlayerManager implements KitPlayerManager, PlayerHandler {

	private Map<String, KitPlayer> players;
	private KitPlayerFactory playerFactory;

	public HandlerPlayerManager(KitPlayerFactory factory) {
		players = new HashMap<String, KitPlayer>();
		
		this.playerFactory = factory;
	}

	public KitPlayer getPlayer(final Player player) {
		if (player == null) throw new IllegalArgumentException("Player must not be null!");

		return players.get(player.getName());
	}

	public void addPlayer(final Player player) {
		players.put(player.getName(), playerFactory.createPlayer(player));
	}

	public void remPlayer(final Player player) {
		playerFactory.destroyPlayer(players.get(player.getName()));
		players.remove(player.getName());
	}


}
