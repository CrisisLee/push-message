package com.playhudong.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playhudong.dao.MessageMapper;
import com.playhudong.model.Message;
import com.playhudong.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageMapper messageMapper;
	
	public Message getMessageById(int id) {
		// TODO Auto-generated method stub
		return messageMapper.selectByPrimaryKey(id);
	}

	public List<Message> getAdavancedMessages() {
		// TODO Auto-generated method stub
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return messageMapper.selectByPushType(Message.ADVANCED, timestamp);
	}
	
	public List<Message> getOrdinaryMessages() {
		// TODO Auto-generated method stub
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return messageMapper.selectByPushType(Message.ORDINARY, timestamp);
	}
	
	public List<Message> getMessages() {
		// TODO Auto-generated method stub
		return messageMapper.selectAll();
	}
	
	//if it is an advanced message, calculate next push-time
	private void beforeUpdateAdvanced(Message message) {
		if(!message.isOrdinary()) {
			CronExpression cronExpression = null;
			Date nextPushTime = null;
			try {
				cronExpression = new CronExpression(message.getCronExpression());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				nextPushTime = cronExpression.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
				message.setPushTime(new Timestamp(nextPushTime.getTime()));
			}
		}
	}
	
	public int insert(Message message) {
		// TODO Auto-generated method stub
		
		//if it is an advanced message, calculate next push-time
		if(!message.isOrdinary()) {
			beforeUpdateAdvanced(message);
		}
		return messageMapper.insertSelective(message);
	}

	public int getMaxId() {
		// TODO Auto-generated method stub
		List<Message> messages = getMessages();
		if (messages.isEmpty()) {
			return 1;
		}
		int maxId = -1;
		for(Message message : messages)
			if(message.getId() > maxId) {
				maxId = message.getId();
			}
			
		return maxId + 1;
	}
	
	public int update(Message message) {
		//if it is an advanced message, calculate next push-time
		if(!message.isOrdinary()) {
			beforeUpdateAdvanced(message);
		}
		return messageMapper.updateByPrimaryKey(message);
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return messageMapper.deleteByPrimaryKey(id);
	}

	public int addToPushList(int id) {
		// TODO Auto-generated method stub
		return messageMapper.updateStatus(id, Message.STATUS_READYTOPUSH);
	}

	/**
	 * after pushing a message, change its status,
	 * if it is an advanced message, change its 
	 * push-time
	 */
	public int updateAfterPush(Message message, boolean pushed) {
		// TODO Auto-generated method stub
		int result = 0;
		if (pushed) {
			setMessageStatus(message, Message.STATUS_PUSHED);
		} else {
			setMessageStatus(message, Message.STATUS_FAILTOPUSH);
		}
		// if it is an advanced message, update its push-time
		if (message.getPushType() == Message.ADVANCED) {
			CronExpression cronExpression = null;
			try {
				cronExpression = new CronExpression(message.getCronExpression());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				//calculate next valid time, and convert it into a time-stamp
				//reset push-time of the message, and update it in DB
				Date temp = cronExpression.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
				Timestamp nextPushTime = new Timestamp(temp.getTime());
				message.setPushTime(nextPushTime);
				//pushed-count + 1
				message.setPushedCount(message.getPushedCount() + 1);
				//reset status to 0
				//why isn't this line executed?????
				message.setStatus(Message.STATUS_EDITABLE);
				result = messageMapper.updateByPrimaryKey(message);
			}
		}
		return result;
	}

	public int setMessageListStatus(List<Message> messages, int status) {
		// TODO Auto-generated method stub
		int changeCount = 0;
		for(Message message : messages)
			changeCount += setMessageStatus(message, status);
		return changeCount;
	}
	
	public int setMessageStatus(Message message, int status) {
		return messageMapper.updateStatus(message.getId(), status);
	}
	

}
