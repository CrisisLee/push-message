package com.playhudong.service;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.playhudong.model.AdvancedPushLog;
import com.playhudong.model.PushLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
"classpath:spring-mybatis.xml" })
public class TestPushLogService {

	@Autowired
	private PushLogService pushLogService;

	@Autowired
	private AdvancedPushLogService advancedPushLogService;
	
	@Autowired
	private DriverManagerDataSource dataSource;
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	@Test
	public void testInsert() {
		
		AdvancedPushLog advancedPushLog = advancedPushLogService.getAdvancedPushLogById(10);
		
		
		System.out.println(advancedPushLog);
		
	}
}
