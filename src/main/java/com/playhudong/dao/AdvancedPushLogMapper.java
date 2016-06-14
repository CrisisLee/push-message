package com.playhudong.dao;

import java.util.List;

import com.playhudong.model.AdvancedPushLog;

public interface AdvancedPushLogMapper {

	int insert(AdvancedPushLog record);
	
	List<AdvancedPushLog> selectAll();
	
	AdvancedPushLog selectByMessageId(int messageId);
	
	int updateByMessageId(AdvancedPushLog record);
	
}
