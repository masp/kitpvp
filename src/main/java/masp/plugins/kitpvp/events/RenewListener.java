package masp.plugins.kitpvp.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

/**
 * I made a separate class for this because there wasn't a great
 * place else to put this thing.
 */
public class RenewListener implements Listener {

	private PlayerHandler handler;
	private Plugin plugin;
	
	public RenewListener(Plugin plugin, PlayerHandler handler) {
		this.handler = handler;
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void addPlayer(PlayerJoinEvent event) {
		handler.addPlayer(event.getPlayer());
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void removePlayer(PlayerQuitEvent event) {
		handler.remPlayer(event.getPlayer());
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void removePlayer(PlayerKickEvent event) {
		handler.remPlayer(event.getPlayer());
	}

	// Highest because we want to be the last
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDisable(PluginDisableEvent event) {
		if (event.getPlugin().equals(plugin)) {
			for (final Player player : Bukkit.getOnlinePlayers()) {
				handler.remPlayer(player);
			}
		}
	}

	@EventHandler(priority= EventPriority.MONITOR)
	public void onEnable(PluginEnableEvent event) {
		if (event.getPlugin().equals(plugin)) {
			for (final Player player : Bukkit.getOnlinePlayers()) {
				handler.addPlayer(player);
			}
		}
	}

	public static interface PlayerHandler {
		public void addPlayer(final Player player);

		public void remPlayer(final Player player);
	}

}
