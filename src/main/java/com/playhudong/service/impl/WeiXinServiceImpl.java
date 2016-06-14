package com.playhudong.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.playhudong.service.WeiXinService;
@Service("weiXinService")
public class WeiXinServiceImpl implements WeiXinService{

	public boolean checkSignature(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		//String echostr = request.getParameter("echostr");
		String token = "weixinMessageTest";
		
		List<String> temp = new ArrayList<String>();
		temp.add(timestamp);
		temp.add(nonce);
		temp.add(token);
		Collections.sort(temp);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(temp.get(0));
		stringBuilder.append(temp.get(1));
		stringBuilder.append(temp.get(2));
		
		String result = stringBuilder.toString();
		String test = DigestUtils.shaHex(result);
		if(test.equals(signature)) {
			return true;
		}
		return false;
	}

}
