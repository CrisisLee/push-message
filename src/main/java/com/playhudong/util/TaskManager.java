package com.playhudong.util;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.playhudong.model.Message;
import com.playhudong.service.MessageService;

/**
 * includes 3 tasks 1st is to scan the oridinary messages in db 2nd is to scan
 * the adavanced messages in db 3rd is to execute the send-messages job,
 * messages are stored in a static priority blocking queue
 * 
 * @author arlabsurface
 *
 */

@Component("taskManager")
public class TaskManager {

	// messages to be pushed today
	// ranked by time-to-be-pushed
	public static PriorityBlockingQueue<Message> pushList = new PriorityBlockingQueue<Message>();

	@Autowired
	private MessageService messageService;

	@Scheduled(fixedRate = 3000)
	public void scanOrdinaryMessages() {

		List<Message> messages = messageService.getOrdinaryMessages();
		if (messages.size() > 0) {
			pushList.addAll(messages);
			// change the messages' status into going-to-push
			messageService.setMessageListStatus(messages, Message.STATUS_GOINGTOPUSH);
		}
	}

	/**
	 * when get an adavanced message, convert it into an ordinary one, for
	 * convenience of sending them
	 */
	@Scheduled(fixedRate = 3000)
	public void scanAdavancedMessages() {
		List<Message> messages = messageService.getAdavancedMessages();
		if (messages.size() > 0) {
			pushList.addAll(messages);
			// change the messages' status into going-to-push
			messageService.setMessageListStatus(messages, Message.STATUS_GOINGTOPUSH);
		}
	}

	/**
	 * once we send a message, we get another from the queue, and wait for time
	 * to push it
	 */

	@Scheduled(fixedDelay = 1000L)
	public void sendMessages() {

		Message message = null;
		try {
			message = pushList.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(1);
			e.printStackTrace();
		} finally {
			// only when current time is after pushtime
			// it is time to send the message
			while (message.getPushTime().after(new Timestamp(System.currentTimeMillis()))) {
				try {
					// avoid to new too much Timestamp objects...
					// do we have a better idea?
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println(2);
					e.printStackTrace();
				}
				continue;
			}
			// current time is after push-time, push it
			boolean pushed = sendMessage(message, message.getChannel());
			// after pushing a message, update its status, success of fail
			messageService.updateAfterPush(message, pushed);

		}

	}

	/**
	 * remove a message from the push list
	 * 
	 * @param id
	 */
	public static void removeFormPushList(int id) {
		Iterator<Message> iterator = pushList.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == id) {
				iterator.remove();
				break;
			}
		}
	}

	public boolean sendMessage(Message message, int channel) {
		// to be continued...
		
		System.out.println("message " + message.getId() + " pushed via channel " + channel);
		return true;
	}
}
