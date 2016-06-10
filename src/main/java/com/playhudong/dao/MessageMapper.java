package com.playhudong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.playhudong.model.Message;

public interface MessageMapper {

	int deleteByPrimaryKey(Integer id);
	
	int insertSelective(Message record);
	
	Message selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(Message record);
	
	int updateByPrimaryKey(Message record);
	
	List<Message> selectAll();
	
	int updateStatus(@Param("id") int id, @Param("newStatus") int newStatus);
}
