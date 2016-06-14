package com.playhudong.service;

import java.util.List;

import com.playhudong.model.PushLog;

public interface PushLogService {

	int insert(PushLog pushLog);
	
	int  update(PushLog pushLog);
	
	List<PushLog> getPushLogByMessageId(int messageId);
	
	List<PushLog> getPushLogs();
	
	int getMaxId();
	
}
