package com.playhudong.messagesender;

import com.playhudong.messagesender.messagesenderImpl.EmailMessageSender;
import com.playhudong.messagesender.messagesenderImpl.SmsMessageSender;
import com.playhudong.messagesender.messagesenderImpl.WeiboMessageSender;
import com.playhudong.messagesender.messagesenderImpl.WeixinMessageSender;
import com.playhudong.model.Message;

public class MessageSenderFactory {
	
	public static MessageSender getMessageSender(int channel) {
		switch (channel) {
		case 0:
			return new WeixinMessageSender();
		case 1:
			return new WeiboMessageSender();
		case 2:
			return new SmsMessageSender();
		case 3:
			return new EmailMessageSender();

		default:
			return null;
		}
	}
	
	public static MessageSender getMessageSender(Message message) {
		return getMessageSender(message.getChannel());
	}
}
