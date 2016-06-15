package com.playhudong.util;

import java.sql.Timestamp;
import com.playhudong.model.AdvancedPushLog;
import com.playhudong.model.Message;
import com.playhudong.model.PushLog;
import com.playhudong.service.AdvancedPushLogService;
import com.playhudong.service.MessageService;
import com.playhudong.service.PushLogService;

public class SendMessageTask implements Runnable {

	private PushLogService pushLogService;
	
	private AdvancedPushLogService advancedPushLogService;
	
	private MessageService messageService;
	
	private Message message;




	public SendMessageTask(PushLogService pushLogService, AdvancedPushLogService advancedPushLogService,
			MessageService messageService, Message message) {
		super();
		this.pushLogService = pushLogService;
		this.advancedPushLogService = advancedPushLogService;
		this.messageService = messageService;
		this.message = message;
	}


	public void run(){
		// TODO Auto-generated method stub
		
		
		
		
		boolean pushed = sendMessage(message);

		// push log
		int status = pushed ? PushLog.STATUS_SUCCESS : PushLog.STATUS_FAILED;
		Timestamp current = new Timestamp(System.currentTimeMillis());
		PushLog pushLog = new PushLog(message.getId(), current, status);
		pushLogService.insert(pushLog);

		// if it is an advanced message, make an advanced push-log, or
		// update one instead
		if (message.getPushType() == Message.ADVANCED) {
			// current message has appeared in db before, update it
			if (advancedPushLogService.existsMessageLog(message.getId())) {
				AdvancedPushLog advancedPushLog = advancedPushLogService.getAdvancedPushLogById(message.getId());
				advancedPushLog.setLastPushTime(current);
				// pushed successfully, pushed-count + 1
				// else, do nothing
				int pushedCount = pushed ? 1 : 0;
				advancedPushLog.setPushedCount(advancedPushLog.getPushedCount() + pushedCount);
				// update in db
				advancedPushLogService.update(advancedPushLog);

			}
			// has-not appeared before, insert a new one
			else {
				advancedPushLogService.insert(new AdvancedPushLog(message.getId(), current));
			}

		}
		// after pushing a message, update its status, success of fail
		messageService.updateAfterPush(message, pushed);
	}
	
	
	public boolean sendMessage(Message message) {
		// to be continued...

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
