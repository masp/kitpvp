package masp.plugins.kitpvp.messages.receivers;

import masp.plugins.kitpvp.messages.MessageReceiver;
import masp.plugins.kitpvp.messages.messages.BasicMessage;
import masp.plugins.kitpvp.player.KitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KitPlayerReceiver implements MessageReceiver {

	private KitPlayer player;

	public KitPlayerReceiver(KitPlayer player) {
		this.player = player;
	}

	@Override
	public void receive(String message) {
		Player bukkitPlayer = Bukkit.getPlayer(player.getName());
		if (bukkitPlayer != null) {
			new BasicMessage(message).send(new SenderReceiver(bukkitPlayer));
		}
	}
}
