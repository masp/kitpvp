package masp.plugins.kitpvp.messages.receivers;

import masp.plugins.kitpvp.messages.MessageReceiver;

import org.bukkit.command.CommandSender;

public class SenderReceiver implements MessageReceiver {

	private CommandSender sender;

	public SenderReceiver(CommandSender sender) {
		this.sender = sender;
	}

	@Override
	public void receive(String message) {
		sender.sendMessage(message);
	}

}
