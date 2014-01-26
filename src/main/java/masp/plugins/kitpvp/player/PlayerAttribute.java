package masp.plugins.kitpvp.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


/**
 * A wrapper for a variable essentially
 */
public class PlayerAttribute<T> {

	private Class<T> type;
	private T val;
	private String player;

	public PlayerAttribute(String player, T def, Class<T> type) {
		this.val = def;
		this.type = type;
		this.player = player;
	}

	public T get() {
		return val;
	}

	public void set(T nVal) {
		this.val = nVal;
	}

	public boolean isType(Class<?> clazz) {
		return type.equals(clazz);
	}

	protected Player getPlayer() {
		return Bukkit.getPlayer(player);
	}
}
