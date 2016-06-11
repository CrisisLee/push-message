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

	public int insert(Message message) {
		// TODO Auto-generated method stub
		
		//if it is an advanced message, calculate next push-time
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
		return messageMapper.updateByPrimaryKey(message);
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return messageMapper.deleteByPrimaryKey(id);
	}

	public int addToPushList(int id) {
		// TODO Auto-generated method stub
		return messageMapper.updateStatus(id, 1);
	}
	

}
