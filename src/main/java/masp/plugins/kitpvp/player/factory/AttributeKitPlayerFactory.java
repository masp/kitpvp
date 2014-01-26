package masp.plugins.kitpvp.player.factory;

import java.util.HashMap;
import java.util.Map;

import masp.plugins.kitpvp.player.KitPlayer;
import masp.plugins.kitpvp.player.attributes.PlayerFactoryVisitor;
import masp.plugins.kitpvp.player.defaults.AttributeFactory;
import masp.plugins.kitpvp.player.defaults.persist.PlayerPersister;

import org.bukkit.entity.Player;

public class AttributeKitPlayerFactory implements KitPlayerFactory {

	private Map<String, AttributeFactory<?>> defaults;
	private PlayerPersister persistance;

	public AttributeKitPlayerFactory(PlayerPersister persistance) {
		defaults = new HashMap<String, AttributeFactory<?>>();

		this.persistance = persistance;
		persistance.setup();
	}

	public void addDefault(String name, AttributeFactory<?> def) {
		defaults.put(name, def);
	}

	@Override
	public KitPlayer createPlayer(Player player) {
		KitPlayer kPlayer = new KitPlayer(player);
		for (String attName : defaults.keySet()) {
			kPlayer.addAttribute(attName, persistance.create(kPlayer, defaults.get(attName)));
		}
		persistance.apply(kPlayer);
		return kPlayer; 
	}

	@Override
	public void destroyPlayer(KitPlayer player) {
		for (String attName : defaults.keySet()) {
			persistance.save(player, player.getAttribute(attName), defaults.get(attName));	
		}
		persistance.apply(player);
	}

	public void visit(PlayerFactoryVisitor loader) {
		loader.accept(this);
	}

}
