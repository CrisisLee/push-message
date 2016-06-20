package com.playhudong.messagesender.messagesenderImpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

//import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.playhudong.messagesender.MessageSender;
import com.playhudong.model.Message;
import com.playhudong.service.TargetUserService;
import com.playhudong.util.BeanFactoryUtil;
/**
 * A class implementing MessageSender to deal with works about 
 * sending a message through SMS
 * @author arlabsurface
 *
 */
public class SmsMessageSender implements MessageSender {

	
	private static TargetUserService targetUserService = 
			(TargetUserService)BeanFactoryUtil.getBean("targetUserService");
	
	public boolean sendMessage(Message message) {
		// TODO Auto-generated method stub
		
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//		NameValuePair[] data = { new NameValuePair("Uid", "CrisisLee"),
//				new NameValuePair("Key", "bc5ea4056b08ce7ad1f4"), 
//				new NameValuePair("smsMob", "15201471054"),
//				new NameValuePair("smsText", "测试能否正常发送短信") };
		
		int toUser = message.getToUsers();
		System.out.println("toUsers: " + toUser);
		String testNum = targetUserService.getMobNumById(toUser);
		System.out.println(testNum);
		
		String userNumber = targetUserService.getMobNumById(message.getToUsers());
		
		NameValuePair[] data = { new NameValuePair("Uid", "CrisisLee"),//need to write it 
				new NameValuePair("Key", "bc5ea4056b08ce7ad1f4"), //into a .properties file
				new NameValuePair("smsMob", userNumber),
				new NameValuePair("smsText", message.getContent()) };
		
		
		
		post.setRequestBody(data);

		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Header[] headers = post.getResponseHeaders();
//		int statusCode = post.getStatusCode();
//		System.out.println("status code " + statusCode);
//		for (Header header : headers)
//			System.out.println("---" + header.toString());
		String result = null;
		try {
			result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		int numSent = Integer.parseInt(result);
		if (numSent == 1) {
			return true;
		}
		return false;
	}

}
