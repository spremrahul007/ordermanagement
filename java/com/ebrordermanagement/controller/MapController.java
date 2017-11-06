package com.ebrordermanagement.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ebrordermanagement.dao.MapDataForm;
import com.ebrordermanagement.model.MapData;
import com.google.gson.Gson;

@Controller
public class MapController 
{
	
	private static List<MapData> contacts = new ArrayList<MapData>();
	 
	@Autowired
    private MapDataForm mapDataForm;
	//static {
		//contacts.add(new MapData("Bondi","",40.9616658, 29.0041673,4, 5));
		//contacts.add(new MapData("Coogee Beach","", 36.3488867, 14.8993175, 6, 7));
		 //	}
	
	@RequestMapping(value = "/ajax")
	public @ResponseBody
	String ShowUserDetails(String id, String fromdate, String todate, String overview
			,String general, String coastal, String approach , String harbour,
			String berthing) 
	{
		
	  try
	  {
	  Gson gson = new Gson();   
	  String paramshipname =  id;
	  String paramfromdate =  fromdate;
	  String paramtodate =  todate;
	  String paramoverview = overview;
	  //System.out.println(paramshipname + paramfromdate + paramtodate + overview + general + coastal + approach + harbour + berthing);	
	  
	  List<MapData> contacts;
	 // List<MapData> contacts = mapDataForm.getContacts(paramshipname,paramfromdate,paramtodate); 
	  
	  if(overview == null)
	  {
		  overview = "0";
	  }
	  
	  if(general == "")
	  {
		  general = "0";
	  }
	  
	  if(coastal == null)
	  {
		  coastal = "0";
	  }
	  
	  if(approach == null)
	  {
		  approach = "0";
	  }
	  
	  if(harbour == null)
	  {
		  harbour = "0";
	  }
	  
	  if(berthing == null)
	  {
		  berthing = "0";
	  }
	  
	  
	  String usageband =  "" + overview +"," + general + "" ;
	 // System.out.println(usageband);	
		 
	  
	  if((paramshipname != null && paramshipname.length() > 0) 
		  && (paramfromdate != null && paramfromdate.length() > 0)
		  && (paramtodate != null && paramtodate.length() > 0)
		  )
	  {
		 // System.out.println(paramshipname + paramfromdate + paramtodate);	
		   //contacts = mapDataForm.getContacts(paramshipname,paramfromdate,paramtodate);
		  contacts = mapDataForm.getContacts(paramshipname,paramfromdate,paramtodate,usageband);
	  }
	  else  
	  {
		  //System.out.println(paramshipname );	
		  contacts = mapDataForm.getContacts1(paramshipname ); 
	  }
	  
	  // MapDataForm contactForm = new MapDataForm();
	  //	contactForm.setContacts(contacts);
	  
	 //List<MapData> contacts = mapDataForm.getContacts();
	  //System.out.println(paramshipname + paramfromdate + paramtodate);
	 ObjectMapper mapper = new ObjectMapper();
	 String json = "";
	 try {
	    	json = mapper.writeValueAsString(contacts);
	     } catch (Exception e) {
		 e.printStackTrace();
	 }
 
	
	  return gson.toJson(json);
	  
	  }
	  catch(Exception ex)
	  {
		  
			 return null;
	  }
	 }
	 
	 
	@RequestMapping("/welcome1")
	public ModelAndView showLogin1() 	
	{
		ModelAndView model = new ModelAndView("mapdisplay");
		ObjectMapper mapper = new ObjectMapper();
	    //MapDataForm contactForm = new MapDataForm();
		//contactForm.setContacts(contacts);
		//String json = "";
		//try {
		//	json = mapper.writeValueAsString(contacts);
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}
		//System.out.println(contactForm);
		
	
		//model.addObject("contacts", contactForm);

		//return new ModelAndView("mapdisplay" , "contactForm", contactForm);
		return model;
	}
	
	 
	
	
	@RequestMapping(value = "/mapdisplay")
    public String map(Model model) {
       // log.debug("showing map()...");
		//System.out.println("map");
    

        return "mapdisplay";
    }
	
	
	@RequestMapping(value = "/ajax1")
	public @ResponseBody
	String ShowUserDetails( String overview ) 
	{
		return null;
	}
	
}
