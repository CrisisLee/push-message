package com.playhudong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playhudong.dao.PushLogMapper;
import com.playhudong.model.PushLog;
import com.playhudong.service.PushLogService;

@Service("pushLogService")
public class PushLogServiceImpl implements PushLogService{

	@Autowired
	private PushLogMapper pushLogMapper;
	
	public int insert(PushLog pushLog) {
		// TODO Auto-generated method stub
		return pushLogMapper.insert(pushLog);
	}

	public int update(PushLog pushLog) {
		// TODO Auto-generated method stub
		return pushLogMapper.updateByPrimaryKey(pushLog);
	}

	public List<PushLog> getPushLogByMessageId(int messageId) {
		// TODO Auto-generated method stub
		return pushLogMapper.selectByMessageId(messageId);
	}

	public List<PushLog> getPushLogs() {
		// TODO Auto-generated method stub
		return pushLogMapper.selectAll();
	}

	public int getMaxId() {
		// TODO Auto-generated method stub
		List<PushLog> pushLogs = getPushLogs();
		if(pushLogs.isEmpty())
			return 1;
		int maxId = -1;
		for(PushLog pushLog : pushLogs) {
			if(pushLog.getId() > maxId) {
				maxId = pushLog.getId();
			}
		}
		return maxId + 1;
	}

}
