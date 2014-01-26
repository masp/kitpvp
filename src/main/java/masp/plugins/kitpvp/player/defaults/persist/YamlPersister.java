package masp.plugins.kitpvp.player.defaults.persist;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import masp.plugins.kitpvp.player.KitPlayer;
import masp.plugins.kitpvp.player.PlayerAttribute;
import masp.plugins.kitpvp.player.defaults.AttributeFactory;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public final class YamlPersister implements PlayerPersister {

	private File dir;
	private Map<String, File> subDirs = new HashMap<String, File>();

	// Cache to speed up accessing - flyweight
	// player name - .yml data file
	private Map<String, FileConfiguration> playerFiles = new HashMap<String, FileConfiguration>();

	public YamlPersister(File dir) {
		this.dir = dir;
	}

	@Override
	public void setup() {
		dir.mkdir();
		for (char i = 'a'; i <= 'z'; i++) {
			File subDir = new File(dir, String.valueOf(i));
			if (!subDir.exists()) {
				subDir.mkdir();
			}
			subDirs.put(String.valueOf(i), subDir);
		}
	}

	@Override
	public <T> PlayerAttribute<T> create(final KitPlayer player, AttributeFactory<T> factory) {
		return factory.create(player, this);
	}

	@Override
	public <T> void save(final KitPlayer player, PlayerAttribute<?> att, AttributeFactory<T> factory) {
		factory.persist(player, att, this);
	}

	@Override
	public void apply(final KitPlayer player) {
		if (playerFiles.containsKey(player.getName())) {
			updateFile(player.getName());
		}
	}

	@Override
	public void clean() {
		// Apply unperformed saves to configuration files
		for (String player : playerFiles.keySet()) {
			updateFile(player);
		}
	}

	public Configuration getConfiguration(String player) {
		if (!playerFiles.containsKey(player)) {
			playerFiles.put(
					player,
					YamlConfiguration.loadConfiguration(getFile(player)));
		}
		return playerFiles.get(player);
	}

	private File getFile(String player) {
		File file = new File(
						subDirs.get(player.substring(0, 1).toLowerCase()),
						player + ".yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return file;
	}
	
	private void updateFile(String player) {
		FileConfiguration config = playerFiles.get(player);

		try {
			config.save(getFile(player));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
