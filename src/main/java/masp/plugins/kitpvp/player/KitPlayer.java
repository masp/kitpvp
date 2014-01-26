package masp.plugins.kitpvp.player;

import java.util.HashMap;
import java.util.Map;

import masp.plugins.kitpvp.messages.MessageReceiver;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KitPlayer implements MessageReceiver {

	private String name;
	private Map<String, PlayerAttribute<?>> atts;

	/**
	 * Hate Java generics!
	 */
	public KitPlayer(Player player) {
		atts = new HashMap<String, PlayerAttribute<?>>();

		this.name = player.getName();
	}

	@SuppressWarnings("unchecked") // We're guarunteed they are the same class by nature of PlayerAttributeDefault 
	public <T> PlayerAttribute<T> getAttribute(String name, Class<T> clazz) {
		if (!atts.containsKey(name)) throw new IllegalArgumentException("No player attribute found by name " + name);

		if (!atts.get(name).isType(clazz))
			throw new IllegalArgumentException("Class passed differs from class of attribute!");

		return (PlayerAttribute<T>) atts.get(name);
	}

	public <T> void addAttribute(String name, PlayerAttribute<T> def) {
		atts.put(name, def);
	}

	public PlayerAttribute<?> getAttribute(String name) {
		return atts.get(name);
	}
	
	public boolean removeAttribute(String name) {
		return atts.remove(name) != null;
	}

	public String getName() {
		return name;
	}

	@Override
	public void receive(String message) {
		Player player = Bukkit.getPlayer(name);	
		if (player != null)
			player.sendMessage(message);
	}

	public interface PlayerAttributeDefault<T> {
		PlayerAttribute<T> getAttribute(Player player);
	}

}
