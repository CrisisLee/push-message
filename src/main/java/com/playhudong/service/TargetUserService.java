package com.playhudong.service;

import java.util.List;

import com.playhudong.model.TargetUser;

public interface TargetUserService {

	int insert(TargetUser targetUser);
	
	int delete(int userId);
	
	int update(TargetUser targetUser);
	
	TargetUser getTargetUserById(int userId);
	
	List<TargetUser> getTargetUsers();
	
	List<TargetUser> getTargetUsersByGroupId(int groupId);
	
	String getMobNumById(int userId);
	
	String getMailById(int userId);

}
