package com.playhudong.messagesender;

import com.playhudong.model.Message;

/**
 * An interface of message senders.
 * Only one method: sendMessage.
 * @author arlabsurface
 *
 */
public interface MessageSender {

	/**
	 * send the message using channel 
	 * @param message
	 * @return
	 */
	boolean sendMessage(Message message);
}
