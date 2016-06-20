package com.playhudong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playhudong.dao.TargetUserMapper;
import com.playhudong.model.TargetUser;
import com.playhudong.service.TargetUserService;

@Service("targetUserService")
public class TargetUserServiceImpl implements TargetUserService {

	@Autowired
	private TargetUserMapper targetUserMapper;
	
	public int insert(TargetUser targetUser) {
		// TODO Auto-generated method stub
		return targetUserMapper.insert(targetUser);
	}

	public int delete(int userId) {
		// TODO Auto-generated method stub
		return targetUserMapper.deleteByPrimaryKey(userId);
	}

	public int update(TargetUser targetUser) {
		// TODO Auto-generated method stub
		return targetUserMapper.update(targetUser);
	}

	public TargetUser getTargetUserById(int userId) {
		// TODO Auto-generated method stub
		return targetUserMapper.getByPrimaryKey(userId);
	}

	public List<TargetUser> getTargetUsers() {
		// TODO Auto-generated method stub
		return targetUserMapper.getTargetUsers();
	}

	public List<TargetUser> getTargetUsersByGroupId(int groupId) {
		// TODO Auto-generated method stub
		return targetUserMapper.getTargetUsersByGroup(groupId);
	}

	public String getMobNumById(int userId) {
		// TODO Auto-generated method stub
		return targetUserMapper.getByPrimaryKey(userId).getMobileNum();
	}
	
	public String getMailById(int userId) {
		return targetUserMapper.getByPrimaryKey(userId).getEmail();
	}

	
	
}
