package masp.plugins.kitpvp.player.attributes;

import masp.plugins.kitpvp.player.defaults.AttributeFactory;
import masp.plugins.kitpvp.player.factory.AttributeKitPlayerFactory;

public class AttributeLoader implements PlayerFactoryVisitor {

	private String name;
	private AttributeFactory<?> def;

	public AttributeLoader(String name, AttributeFactory<?> def) {
		this.name = name;
		this.def = def;
	}

	@Override
	public void accept(AttributeKitPlayerFactory factory) {
		factory.addDefault(name, def);
	}

}
