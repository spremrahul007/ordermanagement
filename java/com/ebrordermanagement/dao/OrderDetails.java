package com.ebrordermanagement.dao;

import java.util.List;

import com.ebrordermanagement.model.Orders;

/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
public interface OrderDetails	
{ 
	public List<Orders> list(String fromdate, String todate );
	public List<Orders> list(String fromdate, String todate ,String shipname );
	public List<Orders> list(String shipname); 
	public List<Orders> list(); 
	public List<Orders> getShipNameList();
	public List<Orders> list1(String k);
	public List<Orders> list2(String k);
}
 
	
 


