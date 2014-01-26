package masp.plugins.kitpvp.messages.builders;

import masp.plugins.kitpvp.messages.Message;
import masp.plugins.kitpvp.messages.MessageBuilder;
import masp.plugins.kitpvp.messages.messages.BasicMessage;

public class BasicMessageBuilder implements MessageBuilder {

	@Override
	public Message build(String message) {
		return new BasicMessage(message);
	}

}
