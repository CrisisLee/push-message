package com.playhudong.service.impl;

import java.util.List;

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

	public List<Message> getMessages() {
		// TODO Auto-generated method stub
		return messageMapper.selectAll();
	}

	public int insert(Message message) {
		// TODO Auto-generated method stub
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

}
