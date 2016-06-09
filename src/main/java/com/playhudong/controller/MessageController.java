package com.playhudong.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.playhudong.model.Message;
import com.playhudong.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@RequestMapping("/showInfo")
	public String showMessage(ModelMap modelMap) {
		List<Message> messages = messageService.getMessages();
		modelMap.addAttribute("messageList", messages);
		return "/message/showInfo";
	}

	@RequestMapping("/addPage")
	public String addPage(ModelMap modelMap) {
		return "/message/addPage";
	}

	@RequestMapping("/addMessage")
	public ModelAndView addMessage(ModelMap modelMap, HttpServletRequest request) {

		int id = messageService.getMaxId();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int toUsers = Integer.parseInt(request.getParameter("toUsers"));
		int channel = Integer.parseInt(request.getParameter("channel"));
		int pushType = Integer.parseInt(request.getParameter("pushType"));
		Message message;
		Timestamp createTime;
		if (pushType == 0) {//case ordinary message
			String time = request.getParameter("pushTime");
			String[] temp = time.split(" ");

			//use input values to construct a time_stamp for push_time
			
			int year = Integer.parseInt(temp[0]);
			int month = Integer.parseInt(temp[1]);
			int day = Integer.parseInt(temp[2]);
			int hour = Integer.parseInt(temp[3]);
			int minute = Integer.parseInt(temp[4]);
			int second = Integer.parseInt(temp[5]);
			@SuppressWarnings("deprecation")
			Timestamp pushTime = new Timestamp(year, month, day, hour, minute, second, 0);
			
			createTime = new Timestamp(System.currentTimeMillis());
			message = new Message(id, createTime, "lxd", title, content, toUsers, channel, 0, pushType, createTime);

		} else {// case advanced message
			String cronExpression = request.getParameter("cronExpression");
			createTime = new Timestamp(System.currentTimeMillis());
			message = new Message(id, createTime, "lxd", title, content, toUsers, channel, 0, pushType,
					cronExpression);

		}

		messageService.insert(message);

//		List<Message> messages = messageService.getMessages();
//		modelMap.addAttribute("messageList", messages);
//		return "redirect:/message/showInfo";
		return new ModelAndView("redirect:/message/showInfo.htmls");

	}
}
