package masp.plugins.kitpvp.player.defaults;

import masp.plugins.kitpvp.player.KitPlayer;
import masp.plugins.kitpvp.player.PlayerAttribute;
import masp.plugins.kitpvp.player.defaults.persist.YamlPersister;

public interface AttributeFactory<T> {

	public PlayerAttribute<T> create(final KitPlayer player, YamlPersister persister);

	public void persist(final KitPlayer player, PlayerAttribute<?> attribute, YamlPersister persister);

}
