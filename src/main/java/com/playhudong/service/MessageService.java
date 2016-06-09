package com.playhudong.service;

import java.util.List;

import com.playhudong.model.Message;

public interface MessageService {

	Message getMessageById(int id);
	
	List<Message> getMessages();
	
	int insert(Message message);
	
	int getMaxId();
	
}
