package com.playhudong.util;

import java.sql.Timestamp;
import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.playhudong.dao.MessageMapper;
import com.playhudong.model.Message;

/**
 * includes 3 tasks
 * 1st is to scan the oridinary messages in db
 * 2nd is to scan the adavanced messages in db
 * 3rd is to execute the send-messages job, messages
 * 	are stored in a static priority blocking queue
 * @author arlabsurface
 *
 */

@Component("taskManager")
public class TaskManager {

	//messages to be pushed today
	//ranked by time-to-be-pushed
	private static PriorityBlockingQueue<Message> pushList = new PriorityBlockingQueue<Message>();
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Scheduled(fixedRate = 3000)
	public void scanOrdinaryMessages() {
		
		
		System.out.println("executing ori...");
	}
	
	/**
	 * when get an adavanced message,
	 * convert it into an ordinary one,
	 * for convenience of sending them
	 */
	@Scheduled(fixedRate = 3000)
	public void scanAdavancedMessages() {
		System.out.println("executing ada...");
	}
	

	/**
	 * once we send a message, we get another
	 * from the queue, and wait for time to push
	 * it
	 */
	
	@Scheduled(fixedDelay = 1000L)
	public void sendMessages() {
		
		System.out.println("balabala");
		Message message = null;
		try {
			message = pushList.take();
			System.out.println("not gonna happen");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(1);
			e.printStackTrace();
		} finally {
			//only when current time is after pushtime
			//it is time to send the message
			while (message.getPushTime().after(new Timestamp(System.currentTimeMillis()))) {
				try {
					//avoid to new too much Timestamp objects...
					//do we have a better idea?
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println(2);
					e.printStackTrace();
				}
				continue;
			}
			//current time is after push-time, push it
			sendMessage(message, message.getChannel());
		}
		
	}
	
	public void sendMessage(Message message, int channel) {
		//to be continued...
	}
}
