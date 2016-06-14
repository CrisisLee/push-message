package com.playhudong.dao;

import java.util.List;

import com.playhudong.model.PushLog;

public interface PushLogMapper {

	int insert(PushLog pushLog);
	
	List<PushLog> selectByMessageId(int id);
	
	List<PushLog> selectAll();
	
	int updateByPrimaryKey(PushLog record);
	
}
