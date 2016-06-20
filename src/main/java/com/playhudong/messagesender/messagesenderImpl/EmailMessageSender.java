package com.playhudong.messagesender.messagesenderImpl;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.playhudong.messagesender.MessageSender;
import com.playhudong.model.Message;
import com.playhudong.service.TargetUserService;
import com.playhudong.util.BeanFactoryUtil;

public class EmailMessageSender implements MessageSender {

	private static TargetUserService targetUserService = 
			(TargetUserService)BeanFactoryUtil.getBean("targetUserService");
	
	public boolean sendMessage(Message message) {
		// TODO Auto-generated method stub

		final Properties props = new Properties();

		try {
			Resource fileResource = new ClassPathResource("mail.properties");
			
			props.load(new BufferedInputStream(fileResource.getInputStream()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		props.put("mail.smtp.host", props.get("mail.smtp.host"));
		props.put("mail.smtp.auth", "true");

		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		Session mailSession = Session.getInstance(props, authenticator);

		MimeMessage mimeMessage = new MimeMessage(mailSession);

		try {
			InternetAddress from = new InternetAddress(props.getProperty("mail.user"));
			mimeMessage.setFrom(from);

			String email = targetUserService.getMailById(message.getToUsers());
			InternetAddress to = new InternetAddress(email);
			mimeMessage.setRecipient(RecipientType.TO, to);

			mimeMessage.setSubject(message.getTitle());

			mimeMessage.setContent(message.getContent(), "text/html;charset=UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("before");
		try {
			Transport.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		System.out.println("done");

		return true;
	}

}
