package com.playhudong.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.playhudong.model.TargetUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestTargetUserService {

	@Autowired
	private TargetUserService targetUserService;

	@Test
	public void testInsert() {
		TargetUser targetUser = new TargetUser(1, "lxd", "15201471054",
				"lxdong@pku.edu.cn", "1026", "1026");
		int result = targetUserService.insert(targetUser);
		if (result == 1) {
			System.out.println("succeed!");
		} else
			System.out.println("failed!");
	}
	
	@Test
	public void testUpdate() {
		TargetUser targetUser = new TargetUser(1, "lxd", "15201471054",
				"lxdong@pku.edu.cn", "1026", "1000");
		int result = targetUserService.update(targetUser);
		if (result == 1) {
			System.out.println("succeed!");
		} else
			System.out.println("failed!");
	}
	
	@Test
	public void testDelete() {
		int result = targetUserService.delete(4);
		System.out.println(result);
	}
	
	@Test
	public void testSelect() {
		List<TargetUser> targetUsers = targetUserService.getTargetUsersByGroupId(2);
		for(TargetUser targetUser : targetUsers)
			System.out.println(targetUser);
	}
}
