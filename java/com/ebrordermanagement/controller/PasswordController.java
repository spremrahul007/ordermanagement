package com.ebrordermanagement.controller;

 
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebrordermanagement.dao.UserDaoImpl;
import com.ebrordermanagement.model.Password;

@Controller 
public class PasswordController 
{

	@Autowired
	UserDaoImpl UserDaoImpl;
	 
    @Autowired
    @Qualifier("passwordValidator")
    private Validator validator;
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
	@RequestMapping(value ="/password" , method = RequestMethod.GET)
	public String initForm(Model model){
		Password password = new Password();
		model.addAttribute("password", password);
		return "password";
	}
	
	@RequestMapping(value ="/password" ,method = RequestMethod.POST)
	public String submitForm(
		Model model, @Validated Password password, BindingResult result,HttpServletRequest request) {
		String returnVal = "successPwd";
		
		String  i = request.getParameter("password");
		String  j = request.getParameter("emailid");
		//System.out.println(i);
		//System.out.println(j);
		
		if(result.hasErrors()) {
			returnVal = "password";
		} else {
			model.addAttribute("password", password);
			UserDaoImpl.updatePassword(j,i);
		}		
		return returnVal;
	}

}
