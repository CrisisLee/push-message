package com.playhudong.controller;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.playhudong.model.Message;
import com.playhudong.service.MessageService;
import com.playhudong.taskutil.TaskManager;

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
	public ModelAndView addMessage(HttpServletRequest request) {

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
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day, hour, minute, second);
			Timestamp pushTime = new Timestamp(calendar.getTimeInMillis());
			
			createTime = new Timestamp(System.currentTimeMillis());
			message = new Message(createTime, "lxd", title, content, toUsers, channel, 0, pushType, pushTime);

		} else {// case advanced message
			String cronExpression = request.getParameter("cronExpression");
			createTime = new Timestamp(System.currentTimeMillis());
			message = new Message(createTime, "lxd", title, content, toUsers, channel, 0, pushType,
					cronExpression);

		}

		messageService.insert(message);

//		List<Message> messages = messageService.getMessages();
//		modelMap.addAttribute("messageList", messages);
//		return "redirect:/message/showInfo";
		return new ModelAndView("redirect:/message/showInfo.htmls");

	}
	/**
	 * redirect to update page
	 * id of message is param
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/updatePage")
	public String updatePage(@RequestParam("id") final int id, ModelMap modelMap) {
		Message message = messageService.getMessageById(id);
		modelMap.addAttribute("message", message); 
		return "/message/updatePage";
	}
	
	@RequestMapping("/updateMessage")
	public ModelAndView updateMessage(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int toUsers = Integer.parseInt(request.getParameter("toUsers"));
		int channel = Integer.parseInt(request.getParameter("channel"));
		int pushType = Integer.parseInt(request.getParameter("pushType"));
		
		Message message = messageService.getMessageById(id);
		//update basic infos
		message.setTitle(title);
		message.setContent(content);
		message.setToUsers(toUsers);
		message.setChannel(channel);
		message.setPushType(pushType);
		
		//set current time as updateTime
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		message.setUpdateTime(updateTime);
		
		if(pushType == 0) {
			//need to be changed.
			//dont know how to set push time by a "input text"
			Timestamp pushTime = new Timestamp(System.currentTimeMillis());
			message.setPushTime(pushTime);
			message.setCronExpression(null);
			message.setPushedCount(-1);
		} else if (pushType == 1) {
			message.setPushTime(null);
			String cronExpression = request.getParameter("cronExpression");
			message.setCronExpression(cronExpression);
			//after update
			//set pushedCount 0 or original value?
			message.setPushedCount(0);
		}
		
		messageService.update(message);
		
		//back to showInfo page
		return new ModelAndView("redirect:/message/showInfo.htmls");
	}
	
	@RequestMapping("/deleteMessage")
	public String deleteMessage(@RequestParam("id") final int id) {
		messageService.delete(id);
		//remove the message if it is in the push-list
		TaskManager.removeFormPushList(id);
		
		return "redirect:/message/showInfo.htmls";
	}
	
	@RequestMapping("/addToPushList")
	public String addToPushList(@RequestParam("id") final int id) {
		messageService.addToPushList(id);
		return "redirect:/message/showInfo.htmls";
	}
	
}
