package masp.plugins.kitpvp.kit.valid.valids;

import masp.plugins.kitpvp.KitPvP;
import masp.plugins.kitpvp.kit.identity.KitIdentity;
import masp.plugins.kitpvp.kit.valid.KitResult;
import masp.plugins.kitpvp.kit.valid.KitValidator;
import masp.plugins.kitpvp.messages.Message;
import masp.plugins.kitpvp.messages.messages.BasicMessage;
import masp.plugins.kitpvp.player.PlayerAttribute;

import org.bukkit.entity.Player;

public class AlreadyHasValidator implements KitValidator {

	private static final Message failMessage = new BasicMessage("You already have this kit");

	private KitIdentity id;

	public AlreadyHasValidator(KitIdentity identity) {
		this.id = identity;
	}

	public KitResult validate(Player player) {
		KitResult result;
		PlayerAttribute<KitIdentity> kit = KitPvP.getPlayerManager().getPlayer(player).getAttribute("kit", KitIdentity.class);
		if (kit.get().is(id)) {
			result = new KitResult(false, failMessage);
		} else {
			result = new KitResult(true, new BasicMessage("Success!"));
		}
		return result;
	}

}
