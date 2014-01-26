package masp.plugins.kitpvp.kit.identity;

/**
 * @purpose
 * KitManager manages the identifiers for kits. For example,
 * KitManager manages the retrieval of the identity of a kit,
 * not a "functional" kit.
 */
public interface KitIdentityManager {

	public KitIdentity getIdentifier(String identifier);

}
