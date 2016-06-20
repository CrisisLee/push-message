package com.playhudong.service;

import java.util.List;

import com.playhudong.model.Message;

public interface MessageService {

	Message getMessageById(int id);
	
	List<Message> getMessages();
	
	int insert(Message message);
	
	int getMaxId();
	
	int update(Message message);
	
	int delete(int id);
	
	int addToPushList(int id);

	List<Message> getAdavancedMessages();
	
	List<Message> getOrdinaryMessages();
	
	int updateAfterPush(Message message, boolean pushed);
	
	int setMessageListStatus(List<Message> messages, int status);
	
	int setMessageStatus(Message message, int status);
	
	
	
}
