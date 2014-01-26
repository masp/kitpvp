package masp.plugins.kitpvp.commands;

import masp.plugins.kitpvp.kit.identity.KitIdentity;
import masp.plugins.kitpvp.kit.identity.identities.EmptyIdentity;
import masp.plugins.kitpvp.kit.kits.Kit;
import masp.plugins.kitpvp.kit.kits.KitInfo;
import masp.plugins.kitpvp.kit.kits.KitManager;
import masp.plugins.kitpvp.kit.valid.KitResult;
import masp.plugins.kitpvp.kit.valid.KitValidator;
import masp.plugins.kitpvp.messages.messages.BasicMessage;
import masp.plugins.kitpvp.messages.receivers.SenderReceiver;
import masp.plugins.kitpvp.player.KitPlayer;
import masp.plugins.kitpvp.player.KitPlayerManager;
import masp.plugins.kitpvp.player.PlayerAttribute;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class KitCommand implements CommandExecutor {

	private KitManager kManager;
	private KitPlayerManager pManager;

	public KitCommand(KitManager kManager, KitPlayerManager pManager) {
		this.kManager = kManager;
		this.pManager = pManager;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			final KitPlayer kPlayer = pManager.getPlayer(player);
			final PlayerAttribute<KitIdentity> currKit = kPlayer.getAttribute("kit", KitIdentity.class);
			if (args.length == 1) {
				String kitName = args[0];	
				KitIdentity identity = kManager.getIdentities().getIdentifier(kitName);
				if (identity == null || identity.equals(new EmptyIdentity())) {
					new BasicMessage(ChatColor.RED + "No kit found by that name!").send(new SenderReceiver(sender));
					return true;
				}
				Kit kit = kManager.getKit(identity);
				KitInfo info = kit.getInfo();

				for (KitValidator validator : info.getValidators()) {
					KitResult result = validator.validate(player);
					if (!result.isSuccessful()) {
						result.getMessage().send(new SenderReceiver(sender));
						return true;
					}
				}

				Kit prevKit = kManager.getKit(currKit.get());
				prevKit.cease(kPlayer);
				kit.apply(kPlayer);
				currKit.set(identity);

				new BasicMessage(ChatColor.GREEN + "You have successfully changed to " + info.getDisplayName().toLowerCase() + "!")
					.send(kPlayer);
				return true;
			} else if (args.length == 0) {
				Kit prevKit = kManager.getKit(kPlayer.getAttribute("kit", KitIdentity.class).get());
				prevKit.cease(kPlayer);
				currKit.set(new EmptyIdentity());
				new BasicMessage("Cleared!").send(new SenderReceiver(sender));
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You are unable to run the kit command: must be a player!");
			return true;
		}
		return false;
	}

}
