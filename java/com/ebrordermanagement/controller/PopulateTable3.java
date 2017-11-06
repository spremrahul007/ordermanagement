package com.ebrordermanagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebrordermanagement.dao.FetchData;
import com.ebrordermanagement.dao.FetchDataMap;
import com.ebrordermanagement.dao.OrderDetails;
import com.ebrordermanagement.dao.ProductDetails;
import com.ebrordermanagement.model.Login;
import com.ebrordermanagement.model.Orders;
import com.ebrordermanagement.model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.springframework.web.servlet.ModelAndView;


 
@WebServlet("/PopulateTable3")
public class PopulateTable3 extends HttpServlet 
{
 private static final long serialVersionUID = 1L;
 	@Autowired
	private OrderDetails orderdetails;
 	private ProductDetails productDetails;
 
    public PopulateTable3() {
    	super(); 
    	//System.out.println("welome");	
    }
  
  
 protected void doGet(HttpServletRequest request, HttpServletResponse response 
		 
		 ) throws ServletException, IOException 
 {
	 HttpSession session = request.getSession(false);
	 ModelAndView model = new ModelAndView();
	 
	 try
	 {
	 
	 if (session != null) 
	{
		 
		 String getShipName = request.getParameter("json4");
		// System.out.println(getShipName);
  
		 String getOrderNo = request.getParameter("json5");
		// System.out.println(getOrderNo);
  
	 
		 ArrayList<Product> country=new ArrayList<Product>();
		 country=FetchDataMap.getAllCountries(getShipName,getOrderNo);
		 //System.out.println(request.getParameter("userName"));
		 Gson gson = new Gson();
		 JsonElement element = gson.toJsonTree(country, new TypeToken<List<Orders>>() {}.getType());
 
		 JsonArray jsonArray = element.getAsJsonArray();
		 response.setContentType("application/json");
		 response.getWriter().print(jsonArray);
	}
	else
	{
		//System.out.print("loginpage");
		model.addObject("redirect:/login", new Login());
		response.sendRedirect("/ebrordermanagement/login");
			 
	}
	 }
	 catch(Exception ex)
	 {
		// System.out.print("loginpage");
			model.addObject("redirect:/login", new Login());
			response.sendRedirect("/ebrordermanagement/login");
	 }
 }
 
 
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		 throws ServletException, IOException 
 {
	 
	System.out.println("welome3");
 
	//String[] chkSms  =  request.getParameterValues("approved");
	//boolean isChkSms =false;
    //if(chkSms !=null && chkSms.length > 0){//If checkbox is checked than assign it with true or 1       
      //  isChkSms=true; 
        //for (int i = 0; i < chkSms.length; i++) 
        //{
          //String a = chkSms[i];
           // System.out.println ( a );
           //FetchData.saveOrUpdate(a);           
        //}
    //}
    //else
    //{
    	//System.out.println("empty1");
    //}
    	
    
    //String[] chkSms1  =  request.getParameterValues("rejected");
 
    //if(chkSms1 !=null && chkSms1.length > 0){//If checkbox is checked than assign it with true or 1       
      //  isChkSms=true; 
        //for (int i = 0; i < chkSms1.length; i++) 
        //{
          // System.out.println ( chkSms1[i] );
          // productDetails.saveOrUpdate(chkSms1[i]);
        //}
   // }
    //else
   // {
    ///	System.out.println("empty2");
    //}
    //String chkSms2[] =  request.getParameterValues("Granted");
    //String jsresponse = chkSms2[0] +" - ";//"Test Response!";
    
    
    //if(request.getParameterValues("Granted")!=null && request.getParameterValues("Granted").length > 0){//If checkbox is checked than assign it with true or 1       
     //   isChkSms=true; 
     //   for (int i = 0; i < chkSms2.length; i++) 
     //   {
     //      System.out.println ( chkSms2[i] );
          // productDetails.saveOrUpdate(chkSms1[i]);
      //  }
   // }
  //  else
  //  {
    	//System.out.println("empty3");
   // }
    
   // String  i = request.getParameter("fromdate");
	//String j =  request.getParameter("todate") ;
	//System.out.println(i +" " + j);	
	 
	 HttpSession session = request.getSession(false);
	 ModelAndView model = new ModelAndView();
	 
	 try
	 {
	 
	 if (session != null) 
	 {
    String checkedValue;
    String uncheckedValue;
    String orderno = null;
    String orderdate = null;
    String k = request.getParameter("json4");
    String l = request.getParameter("json5");
    String n = request.getParameter("json6");
    if(k !=null && l!= null && n !=null)
	 { 
   	
   		request.getSession().setAttribute("fromdate1", k);
     	request.getSession().setAttribute("todate1", l);
     	request.getSession().setAttribute("shipname", n);
	 }
    else
    {
    	
    }
    
   // System.out.println("FromDate : "+ k + l + n );
   
 	
    String[] myJsonData = request.getParameterValues("json[]");
	 
	if(myJsonData !=null && myJsonData.length > 0)	 
	{
		checkedValue ="";
		 for (int m = 0; m < myJsonData.length; m++) 
		 {
			 checkedValue =  myJsonData[m];
			 // System.out.println ( "Approved Checked " +  checkedValue );
			 FetchDataMap.saveOrUpdateAppChecked(checkedValue);   
		 }
	 }
	 
	 String[] myJsonData1 = request.getParameterValues("json1[]");
	 if(myJsonData1 !=null && myJsonData1.length > 0)
	 { 
		 uncheckedValue ="";
		 for (int m = 0; m < myJsonData1.length; m++) 
		 {
			 uncheckedValue =  myJsonData1[m];
			 //System.out.println ("Approved Unchecked " + uncheckedValue );
			 FetchDataMap.saveOrUpdateAppUnChecked(uncheckedValue);   
		 }
	 }
	 
	 String[] myJsonData2 = request.getParameterValues("json2[]");
	 if(myJsonData2 !=null && myJsonData2.length > 0)
 	 { 
		 checkedValue ="";
 		 for (int m = 0; m < myJsonData2.length; m++) 
 	     {
 			checkedValue =  myJsonData2[m];
 	       // System.out.println ("Reject Checked " +checkedValue );
 			FetchDataMap.saveOrUpdateRejChecked(checkedValue);  
 	     }
 	 }
 	 
 	 String[] myJsonData3 = request.getParameterValues("json3[]");	 
 	 if(myJsonData3 !=null && myJsonData3.length > 0)
 	 { 
 		uncheckedValue ="";
 		 for (int m = 0; m < myJsonData3.length; m++) 
 	     {
 		   uncheckedValue =  myJsonData3[m];
 	       //System.out.println ("Rejected Unchecked " + uncheckedValue );
 		  FetchDataMap.saveOrUpdateRejUnChecked(uncheckedValue);  
 	     }
 	 }
 	 
 	 String[] myJsonData4 = request.getParameterValues("json7[]");	 
 	 if(myJsonData4 !=null && myJsonData4.length > 0)
 	 { 
 		orderno ="";
 		 for (int m = 0; m < myJsonData4.length; m++) 
 	     {
 			orderno =  myJsonData4[m];
 	       //System.out.println ("orderno" + orderno ); 
 	     }
 	 }
 	 String[] myJsonData5 = request.getParameterValues("json8[]");	 
 	 if(myJsonData5 !=null && myJsonData5.length > 0)
 	 { 
 		orderdate ="";
 		 for (int m = 0; m < myJsonData5.length; m++) 
 	     {
 		   orderdate =  myJsonData5[m];
 	       //System.out.println ("orderdate " + orderdate ); 	       
 	      
 	     }
 	 }
 	//System.out.println ("orderno" + orderno  + " "  + orderdate + "" + n); 
 	  	    
 	if (orderno != null && orderdate!= null)
 	{
 		
 		FetchDataMap.saveOrUpdateOrderStatus(orderno,orderdate,n);
 	}
 	
    response.sendRedirect("/ebrordermanagement/getencorderdetails");
    
	 }
	 else
	 {
		//System.out.print("loginpage");
		model.addObject("redirect:/login", new Login());
		response.sendRedirect("/ebrordermanagement/login");
		 
	 }
	 
    //request.setAttribute("message", "Hello world");
    //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ordermanagement/getorderdetails");
    //dispatcher.forward(request, response);
	 }
	 catch(Exception ex)
	 {
		// System.out.print("loginpage");
		 model.addObject("redirect:/login", new Login());
		 response.sendRedirect("/ebrordermanagement/login");
	 }
    
 }
 
}
