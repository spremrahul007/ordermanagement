package com.ebrordermanagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ebrordermanagement.dao.UserDaoImpl;
import com.ebrordermanagement.model.Login;
import com.ebrordermanagement.model.User;
 

@Controller
public class LoginController  
{
  @Autowired
  UserDaoImpl UserDaoImpl;
   
 // private static final long serialVersionUID1 = 1L;
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException
  {
	//System.out.println("loginpage");	
    ModelAndView mav = new ModelAndView("login");
    mav.addObject("login", new Login());
    return mav;
  }
  
  
  
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, 
		 
  @ModelAttribute("login") Login login,  BindingResult result) 
  {
	//Validation code start
	    boolean error = false;
	     
	    //System.out.println(login); //Verifying if information is same as input by user
	    if(login.getUsername().isEmpty()){
	    	//System.out.println("logins"); 
	        result.rejectValue("username", "error.username");
	        error = true;
	    }
	    if(login.getPassword().isEmpty()){
	    	//System.out.println("logins"); 
	        result.rejectValue("password", "error.password");
	        error = true;
	    }
	      
	    if(error) {
	    	ModelAndView mav = new ModelAndView("login");
	    	 return mav;

	    }
	    //validation code ends
	    
	    
	    
    ModelAndView mav = null;
    User user = UserDaoImpl.validateUser(login);
    
  
    
    String un = request.getParameter("username");
	String pwd = request.getParameter("password");
	//System.out.println(un +" " + pwd);	
    if (null != user)
    {
    	 
    	  
    	  
    	mav = new ModelAndView("redirect:/getorderdetails");
    	mav.addObject("firstname", user.getFirstname());
    	
    	HttpSession session = request.getSession(true); // reuse existing
		// session if exist // or create one
    	session.setAttribute("user", un);
    	session.setMaxInactiveInterval(120); // 30 seconds    
    } 
    else 
    {
    	mav = new ModelAndView("login");
    	mav.addObject("message", "Username or Password is wrong!!");
    }
    return mav;
  }
  
  //private static final long serialVersionUID = 1L;
    
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
  {
	  
	  HttpSession session = request.getSession(false);
	  ModelAndView model = new ModelAndView();
	  String SessionExists = (String) request.getSession().getAttribute("user");
	  if (SessionExists != null) 
		 {
			   //System.out.print("logout");
			  // TODO Auto-generated method stub
			  //System.out.println("thanq you!!, Your session was destroyed successfully!!");
			   
			  session.setAttribute("user", null);
			  session.removeAttribute("user");
			  session.getMaxInactiveInterval();
			  String redirectUrl = "redirect:/login";
			  response.setHeader("Location", redirectUrl);
			  ModelAndView mav = new ModelAndView("redirect:/login");
			  mav.addObject("redirect:/login", new Login());
			  return mav;	
		 }
		 else
		 {
			 model.addObject("redirect:/login", new Login());
			 response.sendRedirect("/ebrordermanagement/login");
			 return null;
		 }
	    
	}
  
   
}