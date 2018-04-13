package com.hand.hls;

import com.hand.hls.sys.dto.User;
import com.hand.hls.sys.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IUserServiceTest2 {

	
	public static void main(String[] args) {
		ApplicationContext application = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		IUserService uService = (IUserService) application.getBean("userService");
		User user = uService.getUserById(1);
		System.out.println(user.getUserName());
	}
}
