package com.playhudong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playhudong.dao.AdvancedPushLogMapper;
import com.playhudong.model.AdvancedPushLog;
import com.playhudong.service.AdvancedPushLogService;


@Service("advancedPushLogService")
public class AdvancedPushLogServiceImpl implements AdvancedPushLogService {

	@Autowired
	private AdvancedPushLogMapper advancedPushLogMapper;
	
	public int insert(AdvancedPushLog advancedPushLog) {
		// TODO Auto-generated method stub
		return advancedPushLogMapper.insert(advancedPushLog);
	}

	public int update(AdvancedPushLog advancedPushLog) {
		// TODO Auto-generated method stub
		return advancedPushLogMapper.updateByMessageId(advancedPushLog);
	}

	public List<AdvancedPushLog> getAdvancedPushLogs() {
		// TODO Auto-generated method stub
		return advancedPushLogMapper.selectAll();
	}

	public AdvancedPushLog getAdvancedPushLogById(int id) {
		// TODO Auto-generated method stub
		return advancedPushLogMapper.selectByMessageId(id);
	}

	public boolean existsMessageLog(int id) {
		// TODO Auto-generated method stub
		return (advancedPushLogMapper.selectByMessageId(id) != null);
	}

}
