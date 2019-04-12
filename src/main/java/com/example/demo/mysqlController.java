package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mysql")
public class mysqlController {

	@Autowired
	private mysqlService mysqlService;

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginPage(){
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String checkUser(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("name");
		String details = request.getParameter("details");
		
		if(mysqlService.checkUserName(name))
		{
			if(mysqlService.saveDetails(name,details))
			{
				return "login-insertion";
			}
			return "login-noinsertion";
		}
		else
			return "failed-login";
	}
}
