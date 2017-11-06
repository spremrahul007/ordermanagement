package com.ebrordermanagement.controller;
 
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebrordermanagement.dao.FetchData;
import com.ebrordermanagement.dao.OrderDetails;
import com.ebrordermanagement.dao.ProductDetails;
import com.ebrordermanagement.dao.ShipNameList;
import com.ebrordermanagement.model.Login;
import com.ebrordermanagement.model.Orders;
import com.ebrordermanagement.model.Product;
import com.ebrordermanagement.model.ShipName;
 
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

 

/**
 * This controller routes accesses to the application to the appropriate
 * handler methods. 
 * @author www.codejava.net
 *
 */
@Controller
public class Orderdetailcontroller 
{

	@Autowired
	private OrderDetails orderdetails;
	private ProductDetails productDetails;
	private ShipNameList shipnameList;
	 
    
	@ModelAttribute("shipname")
	public List<Orders> getCountryList()
	{
	  List<Orders> countryList ;
	  countryList = orderdetails.getShipNameList();
	  return countryList;
	}	
	
	 
	
	@RequestMapping(value="/orderdetails" )
	@ModelAttribute("shipnamelist") 
	public ModelAndView listContacta(ModelAndView model,HttpServletRequest request) throws IOException
	{
		//productDetails.saveOrUpdate();
		String  i = request.getParameter("fromdate");
		String j =  request.getParameter("todate") ;
		
		//System.out.println(i +" " + j);	
		
		List<Orders> listCat =   orderdetails.getShipNameList();
	    model.addObject("listCat",listCat);
	   // System.out.println(listCat);	
	    
		List<Orders> listContact = orderdetails.list();
	    model.addObject("listContact", listContact);
	   
	    model.setViewName("orderdetails");	
	 
	    List<String> courses = new ArrayList<String>();
		courses.add("Yoga");
		courses.add("Stretching");
		courses.add("Pilates");
		courses.add("Aerobic");
		courses.add("Oriental");
		
		model.addObject("courses", courses);
		
	    return model;
	} 	
	
	@RequestMapping(value="/orderdetails", method =RequestMethod.POST )
	@ModelAttribute("shipnamelist")
	public ModelAndView listContacta1(ModelAndView model,HttpServletRequest request
			) throws IOException
	{
		//productDetails.saveOrUpdate();
		
		//System.out.println("welome8");
		  
		String  i = request.getParameter("fromdate");
		String j =  request.getParameter("todate") ;
		//System.out.println(i +" " + j);	
		
		
		List<Orders> listCat =   orderdetails.getShipNameList();
	    model.addObject("listCat",listCat);
	    //System.out.println(listCat);	
	    
		List<Orders> listContact = orderdetails.list();
	    model.addObject("listContact", listContact);
	   
	    model.setViewName("orderdetails");	
	   
	    List<String> courses = new ArrayList<String>();
	    courses.add("OverView");
		courses.add("General");
		courses.add("Coastal");
		courses.add("Approach");
		courses.add("Harbour");
		courses.add("Berthing");
		
		model.addObject("courses", courses);
		
	    return model;
	} 
	
	
	@RequestMapping(value="/getorderdetails", method =RequestMethod.GET)
	public ModelAndView listContact(ModelAndView model,HttpServletRequest request 
			,HttpServletResponse response 
			,String overView
			) throws IOException
	{
		
		try
		{
		HttpSession session = request.getSession(false);
	   //System.out.println(session.getAttribute("user") );
		String SessionExists = (String) request.getSession().getAttribute("user");
		
		if (SessionExists != null) 
		{
			
		 
			String  i = request.getParameter("fromdate");
			String j =  request.getParameter("todate") ;
			String k =  request.getParameter("k");
			String l =  request.getParameter("json4");
			String m =  request.getParameter("overView");
			//System.out.println("overView : " + m );
			
			
			
			
			
			if (i ==null)
			{
				
				i = (String) request.getSession().getAttribute("fromdate1");
				//System.out.println (   i  );
				request.setAttribute("fromdate", i);
				//request.getSession().setAttribute("fromdate1", null);
		     	
			}
			if (j ==null)	
			{
				
				j = (String) request.getSession().getAttribute("todate1");
				//System.out.println (    j  );
				request.setAttribute("todate", j);
				//request.getSession().setAttribute("todate1", null);
		     
			}
			if (k ==null)
			{	
				
				k = (String) request.getSession().getAttribute("shipname");
				//System.out.println (    k  );
				request.setAttribute("k", k);
				//request.getSession().setAttribute("shipname", null);
			}
				 
			List<Orders> listShipNames = orderdetails.getShipNameList();
			model.addObject("listShipNames", listShipNames); 
			
			//System.out.println ( "getordetails : " +   i + " " + j  + " " + k +  " " +  l  + " ");
			 
			request.setAttribute("fromdate", i);
			request.setAttribute("todate", j);
			request.setAttribute("k", k); 
			
			//System.out.println (request.getParameter("fromdate"));
			//System.out.println (request.getParameter("todate") );
			//System.out.println (request.getParameter("k"));
		 
			
			
			List<Orders> listContact = orderdetails.list(i,j,k);
		    model.addObject("listContact", listContact);
		   
		    model.setViewName("getorderdetails");
		    
		    List<String> courses = new ArrayList<String>();
			courses.add("OverView");
			courses.add("General");
			courses.add("Coastal");
			courses.add("Approach");
			courses.add("Harbour");
			courses.add("Berthing");
			
			String paramoverall = request.getParameter("all");
			String paramoverView = request.getParameter("overView");
			String paramgeneral = request.getParameter("general");;
			String paramapproach = request.getParameter("approach");;
			String paramcoastal = request.getParameter("coastal");;
			String paramharbour = request.getParameter("harbour");;
			String paramberthing = request.getParameter("berthing");;
			
			 
			System.out.println ("paramoverall " + paramoverall );
			
			if (paramoverall ==null)
			{	
				//System.out.println (  "paramoverall : " +   paramoverall  );
				request.setAttribute("all",paramoverall);				
			}
			else
			{
				//System.out.println (  "paramoverall : " +   paramoverall  );
				request.setAttribute("all",paramoverall);	
			}
				
			if (paramoverView ==null)
			{	
				//System.out.println (    paramoverView  );
				request.setAttribute("overView",paramoverView);				
			}
			else
			{
				//System.out.println (    paramoverView  );
				request.setAttribute("overView",paramoverView);
			}
			request.setAttribute("general",paramgeneral);
			request.setAttribute("coastal",paramcoastal);
			request.setAttribute("approach",paramapproach);
			request.setAttribute("harbour",paramharbour);
			request.setAttribute("berthing",paramberthing);
			 
			
			model.addObject("courses", courses);
			
			
		    return model;
		}
		else
		{	 //System.out.print("loginpage");
			 model.addObject("redirect:/login", new Login());
			 response.sendRedirect("/ebrordermanagement/login");
			 return null;
		}
		}
		catch(Exception ex)
		{
			 //System.out.print("loginpage");
			 model.addObject("redirect:/login", new Login());
			 response.sendRedirect("/ebrordermanagement/login");
			 return null;
		}
		 
	} 
	
	
	@RequestMapping( value = "/getorderdetails", method =RequestMethod.POST)
    public String FicheService(HttpServletRequest request, HttpServletResponse response
    		,@RequestParam String fromdate, @RequestParam String todate, ModelMap model1
    		,@RequestParam String selectList  
    		) throws IOException
	{
		
	 HttpSession session = request.getSession(false);
	 ModelAndView model = new ModelAndView();
	 String SessionExists = (String) request.getSession().getAttribute("user");
	 System.out.println ( "welcome8" );

		try
		{
	 if (SessionExists != null) 
	 {
		 
		 
		//System.out.println ( overview );
		String  i = request.getParameter("fromdate");
		String j =  request.getParameter("todate") ;
		String k =  request.getParameter("selectList") ;
		String all =  request.getParameter("all") ;
		String overview =  request.getParameter("overView") ;
		String general =  request.getParameter("general") ;
		String coastal =  request.getParameter("coastal") ;
		String approach =  request.getParameter("approach") ;
		String harbour =  request.getParameter("harbour") ;
		String berthing =  request.getParameter("berthing") ;
		 
		// System.out.println ( i );
		// System.out.println ( j );
		// System.out.println ( k );
		 //System.out.println ("over :" + l );
		 
		
		
		
		//Date i = Date.valueOf(request.getParameter("fro)mdate"));
		//Date j = Date.valueOf(request.getParameter("todate"));
	
		model1.put("fromdate",fromdate);
		model1.put("todate",todate);
		model1.put("k",k);
		model1.put("all", all);
		model1.put("overView", overview);
		model1.put("general", general);
		model1.put("coastal", coastal);
		model1.put("approach", approach);
		model1.put("harbour", harbour);
		model1.put("berthing", berthing);
		
		//request.setAttribute("overView", l); 
		
		List<Orders> listContact = orderdetails.list(i,j);
	    model.addObject("listContact", listContact);
	    model.setViewName("getorderdetails");	
	    
	    
	    List<String> courses = new ArrayList<String>();
	    courses.add("OverView");
		courses.add("General");
		courses.add("Coastal");
		courses.add("Approach");
		courses.add("Harbour");
		courses.add("Berthing");
		
		model.addObject("courses", courses);
		
		
	    return "redirect:/getorderdetails";
	 }
	 else
	 {
		//System.out.print("loginpage");
		model.addObject("redirect:/login", new Login());
		response.sendRedirect("/ebrordermanagement/login");
		return null;
	 }
		}
		catch(Exception ex)
		{
			//System.out.print("loginpage");
			 model.addObject("redirect:/login", new Login());
			 response.sendRedirect("/ebrordermanagement/login");
			 return null;
		}
	}
	   
	
	 
    @RequestMapping(value = "/ediContact", method = RequestMethod.GET)
	@ResponseBody
	  public String FicheService1(HttpServletRequest request, HttpServletResponse response
	    		,@RequestParam String fromdate, @RequestParam String todate, ModelMap model1 )
	{
		String  i = request.getParameter("fromdate");
		String j =  request.getParameter("todate") ;
		
		//Date i = Date.valueOf(request.getParameter("fromdate"));
		//Date j = Date.valueOf(request.getParameter("todate"));
		//System.out.println(i +" " + j);	
		model1.put("fromdate",fromdate);
		model1.put("todate",todate);
		
		
		ModelAndView model = new ModelAndView();
		
		List<Orders> listContact = orderdetails.list(i,j);
	    model.addObject("listContact", listContact);
	    model.setViewName("getorderdetails");	
	    
	    return "redirect:/getorderdetails";
	}
    
    
    @RequestMapping(value="/getencorderdetails", method =RequestMethod.GET)
	public ModelAndView GetENCOrderDetails(ModelAndView model,HttpServletRequest request ,HttpServletResponse response ) throws IOException
	{
		HttpSession session = request.getSession(false);
		//System.out.println(session.getAttribute("user") );
		String SessionExists = (String) request.getSession().getAttribute("user");
		
		try
		
		{
		if (SessionExists != null) 
		{
			
		 
			//String  i = request.getParameter("fromdate");
			//String j =  request.getParameter("todate") ;
			String k =  request.getParameter("k");
			String l =  request.getParameter("json4");
			
			
			
			
			
			//if (i ==null)
			//{
				
			//	i = (String) request.getSession().getAttribute("fromdate1");
			//	System.out.println (   i  );
			//	request.setAttribute("fromdate", i);
				//request.getSession().setAttribute("fromdate1", null);
		     	
		//	}
		//	if (j ==null)	
			//{
				
			//	j = (String) request.getSession().getAttribute("todate1");
			//	System.out.println (    j  );
			//	request.setAttribute("todate", j);
				//request.getSession().setAttribute("todate1", null);
		     
			//}
			if (k ==null)
			{	
				
				k = (String) request.getSession().getAttribute("shipname");
				//System.out.println (    k  );
				request.setAttribute("k", k);
				//request.getSession().setAttribute("shipname", null);
			}
				 
			List<Orders> listShipNames = orderdetails.getShipNameList();
			model.addObject("listShipNames", listShipNames); 
			
			//System.out.println ( "getordetails :  " + k +  " " +  l  + " ");
			 
			//request.setAttribute("fromdate", i);
		//	request.setAttribute("todate", j);
			request.setAttribute("k", k); 
			
			//System.out.println (request.getParameter("fromdate"));
			//System.out.println (request.getParameter("todate") );
			//System.out.println (request.getParameter("k"));
		 
			
			
			List<Orders> listContact = orderdetails.list2(k);
		    model.addObject("listContact", listContact);
		   
		    model.setViewName("getencorderdetails");
		    return model;   
		}
		else
		{	 
			//System.out.print("loginpage");
			 model.addObject("redirect:/login", new Login());
			 response.sendRedirect("/ebrordermanagement/login");
			 return null;
		}
		
		}
		catch(Exception ex)
		{
			//System.out.print("loginpage");
			 model.addObject("redirect:/login", new Login());
			 response.sendRedirect("/ebrordermanagement/login");
			 return null;
		}
		 
	} 
    
    
    @RequestMapping( value = "/getencorderdetails", method =RequestMethod.POST)
    public String FicheService1(HttpServletRequest request, HttpServletResponse response
    		 , ModelMap model1
    		,@RequestParam String selectList) throws IOException
	{
		
	 HttpSession session = request.getSession(false);
	 ModelAndView model = new ModelAndView();
	 String SessionExists = (String) request.getSession().getAttribute("user");
	 
	 try
	 {
	 if (SessionExists != null) 
	 {
		//System.out.println ( "welcome post getorderdetails" );
		//String  i = request.getParameter("fromdate");
		//String j =  request.getParameter("todate") ;
		String k =  request.getParameter("selectList") ;
		 
		// System.out.println ( i );
		// System.out.println ( j );
		// System.out.println ( k );
		 
		
		
		
		//Date i = Date.valueOf(request.getParameter("fro)mdate"));
		//Date j = Date.valueOf(request.getParameter("todate"));
	
		//model1.put("fromdate",fromdate);
		//model1.put("todate",todate);
		model1.put("k",k);
		
		
		
		List<Orders> listContact = orderdetails.list2(k);
	    model.addObject("listContact", listContact);
	    model.setViewName("getencorderdetails");	
	    
	    return "redirect:/getencorderdetails";
	 }
	 else
	 {
		//System.out.print("loginpage");
		model.addObject("redirect:/login", new Login());
		response.sendRedirect("/ebrordermanagement/login");
		return null;
	 }
	 
	 }
	 catch(Exception ex)
	 {
		 //System.out.print("loginpage");
			model.addObject("redirect:/login", new Login());
			response.sendRedirect("/ebrordermanagement/login");
			return null;
	 }
	}
	   
	 
}
