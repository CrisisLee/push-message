package com.playhudong.service;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.playhudong.model.Message;
import com.sun.istack.internal.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
"classpath:spring-mybatis.xml" })
public class TestMessageService {

	private static final Logger LOGGER = Logger.getLogger(TestMessageService.class);

	@Autowired
	private MessageService messageService;
	@Autowired
	private DriverManagerDataSource dataSource;
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;

	@Test
	public void testQueryById() {
		Message message = messageService.getMessageById(0);
		LOGGER.info(message.toString());
		
		
	}


}
