package com.ebrordermanagement.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ebrordermanagement.dao.UserDaoImpl;
import com.ebrordermanagement.model.Login;
import com.ebrordermanagement.model.User;

@Controller
public class RegistrationController 
{
  
  @Autowired
  UserDaoImpl userDaoImpl;
  
   
  @RequestMapping(value ="/register", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
	  
	  //System.out.println("loginpage");	
	    ModelAndView mav = new ModelAndView("register");
	    mav.addObject("user", new User());
	    return mav;
  }
  
  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
  @ModelAttribute("user") User user) {
	  userDaoImpl.register(user);
  return new ModelAndView("orderdetails", "firstname", user.getFirstname());
  }
}