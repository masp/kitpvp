package masp.plugins.kitpvp.player.attributes;

import masp.plugins.kitpvp.player.factory.AttributeKitPlayerFactory;

public interface PlayerFactoryVisitor {

	public void accept(AttributeKitPlayerFactory factory);

}
