package com.ebrordermanagement.dao;

import java.util.List;

import com.ebrordermanagement.model.MapData;

public interface MapDataForm {
	
	public List<MapData> list();

	public List<MapData> getContacts();
	
	public List<MapData> getContacts1(String shipname);
	
	public List<MapData> getContacts(String shipname,String formdate, String todate);

	public List<MapData> getContacts(String shipname,String formdate, String todate,String usageband);
	
	public void setContacts(List<MapData> contacts);

}
