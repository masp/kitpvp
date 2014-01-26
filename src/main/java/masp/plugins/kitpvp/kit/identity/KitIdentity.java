package masp.plugins.kitpvp.kit.identity;

/**
 * @purpose
 * Represents the unique identity of a kit, and only has to verify
 * its distinctness.
 */
public interface KitIdentity {
	
	public boolean is(KitIdentity identity);

}
