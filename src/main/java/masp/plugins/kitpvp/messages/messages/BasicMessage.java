package masp.plugins.kitpvp.messages.messages;

import masp.plugins.kitpvp.messages.Message;
import masp.plugins.kitpvp.messages.MessageReceiver;

public final class BasicMessage implements Message {

	private String message;

	public BasicMessage(String message) {
		this.message = message;
	}

	@Override
	public void send(MessageReceiver... receivers) {
		for (MessageReceiver receiver : receivers) {
			receiver.receive(message);
		}
	}

	@Override
	public int hashCode() {
		return message.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof BasicMessage) {
			return ((BasicMessage) o).message.equals(message);
		}
		return false;
	}
}
