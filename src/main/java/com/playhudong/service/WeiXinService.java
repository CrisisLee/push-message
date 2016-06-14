package com.playhudong.service;

import javax.servlet.http.HttpServletRequest;

public interface WeiXinService {

	boolean checkSignature(HttpServletRequest request);
	
}
