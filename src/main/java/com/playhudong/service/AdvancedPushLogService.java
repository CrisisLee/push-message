package com.playhudong.service;

import java.util.List;

import com.playhudong.model.AdvancedPushLog;

public interface AdvancedPushLogService {

	int insert(AdvancedPushLog advancedPushLog);
	
	int update(AdvancedPushLog advancedPushLog);
	
	List<AdvancedPushLog> getAdvancedPushLogs();
	
	AdvancedPushLog getAdvancedPushLogById(int id);
	
	boolean existsMessageLog(int id);
	
}
