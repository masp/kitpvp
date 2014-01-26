package masp.plugins.kitpvp.config.populator;

import masp.plugins.kitpvp.config.ConfigException;
import masp.plugins.kitpvp.kit.identity.KitIdentity;
import masp.plugins.kitpvp.kit.kits.Kit;

public interface KitPopulator {

	public Kit getKit() throws ConfigException;

	public KitIdentity getIdentity() throws ConfigException;

}
