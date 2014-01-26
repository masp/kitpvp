package masp.plugins.kitpvp.player.defaults.persist;

import masp.plugins.kitpvp.player.KitPlayer;
import masp.plugins.kitpvp.player.PlayerAttribute;
import masp.plugins.kitpvp.player.defaults.AttributeFactory;

public interface PlayerPersister {

	public void setup();

	public <T> PlayerAttribute<T> create(final KitPlayer player, AttributeFactory<T> factory);

	public <T> void save(final KitPlayer player, PlayerAttribute<?> att, AttributeFactory<T> factory);

	public void apply(final KitPlayer player);

	public void clean();

}
