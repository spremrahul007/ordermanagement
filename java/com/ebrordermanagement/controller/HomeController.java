package com.ebrordermanagement.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebrordermanagement.model.Password;
 
@Controller
public class HomeController 

{
	@RequestMapping("/index")
	  public void showLogin( HttpServletResponse response) throws Exception 
	  {
	    // 
		response.sendRedirect("index.html");
	  } 
	
	@RequestMapping("/welcome")
	  public String showLogin1() 
	  {
	    // 
	     return "welcome";
	  } 
	
	 
}
