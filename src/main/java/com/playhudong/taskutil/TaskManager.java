package com.playhudong.taskutil;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.playhudong.model.Message;
import com.playhudong.service.AdvancedPushLogService;
import com.playhudong.service.MessageService;
import com.playhudong.service.PushLogService;

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
	// public static PriorityBlockingQueue<Message> pushList = new
	// PriorityBlockingQueue<Message>();
	public static DelayQueue<Message> pushList = new DelayQueue<Message>();

	@Autowired
	private MessageService messageService;

	@Autowired
	PushLogService pushLogService;

	@Autowired
	AdvancedPushLogService advancedPushLogService;

	//A thread pool to send messages, when we get a new message to push,
	//we will new a thread to push it.
	//The type of thread pool needs to be checked. If we have 10000000 messages
	//to push, we cannot create that much threads. So a fixed num thread pool may
	//fits better.
	public static ExecutorService executorService = Executors.newCachedThreadPool();

	@Scheduled(fixedRate = 3000L)
	public void scanOrdinaryMessages() {
		System.out.println("进行一次普通扫描");
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
	@Scheduled(fixedRate = 3000L)
	public void scanAdavancedMessages() {
		System.out.println("进行一次高级扫描");
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

	@Scheduled(fixedDelay = 1L)
	public void sendMessages() {

		while (true) {
			Message message = null;
			try {
				message = pushList.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(1);
				e.printStackTrace();
			}
			SendMessageTask sendMessageTask = new SendMessageTask(pushLogService, advancedPushLogService,
					messageService, message);
			executorService.execute(sendMessageTask);
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

}
