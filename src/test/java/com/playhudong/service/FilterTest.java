package com.playhudong.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
"classpath:spring-mybatis.xml" })
public class FilterTest {

	@Test
	public void testFilter() throws FileNotFoundException, IOException {
		Properties props = new Properties();

		System.out.println("before");
		
		File file = new File("");
		String filepath = file.getAbsolutePath() + "/src/main/resources/mail.properties";
		props.load(new BufferedInputStream(new FileInputStream(new File(filepath))));
		
		System.out.println(props.toString());
		
	
	}
}
