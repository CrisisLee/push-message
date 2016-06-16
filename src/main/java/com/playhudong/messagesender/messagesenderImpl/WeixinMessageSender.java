package com.playhudong.messagesender.messagesenderImpl;

import com.playhudong.messagesender.MessageSender;
import com.playhudong.model.Message;

public class WeixinMessageSender implements MessageSender {

	public boolean sendMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("message " + message.getId() + " pushed via channel " + message.getChannel());
		return true;
	}

}
