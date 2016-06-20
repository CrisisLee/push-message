package com.playhudong.dao;

import java.util.List;

import com.playhudong.model.TargetUser;

public interface TargetUserMapper {

	//int insertSelective(TargetUser targetUser);
	
	int insert(TargetUser targetUser);
	
	int deleteByPrimaryKey(int userId);
	
	int deleteByGroupId(int groupId);
	
	int update(TargetUser targetUser);
	
	//int updateSelective(TargetUser targetUser);
	
	TargetUser getByPrimaryKey(int userId);
	
	List<TargetUser> getTargetUsers();
	
	List<TargetUser> getTargetUsersByGroup(int groupId);
	
}
