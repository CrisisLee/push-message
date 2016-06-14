package com.playhudong.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.playhudong.service.WeiXinService;

@Controller
@RequestMapping("/wx")
public class WXTestController {
	@Autowired
	private WeiXinService weiXinService;
	
	@RequestMapping("/home")
	public void testWX(HttpServletRequest request, HttpServletResponse response) {
		
		String method = request.getMethod();
		//get: check weixin service
		if (method.equals("GET")) {
			if(weiXinService.checkSignature(request)) {
				String echostr = request.getParameter("echostr");
				PrintWriter out = null;
				try {
					out = response.getWriter();
					out.print(echostr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					out.close();
					out = null;
				}
			}
		//post: deal with user's questions		
		} else {
			;
		}
		
	}
	
}
