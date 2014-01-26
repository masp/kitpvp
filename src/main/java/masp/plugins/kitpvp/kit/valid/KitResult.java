package masp.plugins.kitpvp.kit.valid;

import masp.plugins.kitpvp.messages.Message;

public final class KitResult {

	private boolean success;
	private Message message;

	public KitResult(boolean success, Message message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccessful() {
		return success;
	}

	public Message getMessage() {
		return message;
	}

}
