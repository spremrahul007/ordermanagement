package com.ebrordermanagement.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.ebrordermanagement.dao.OrderDetails;
import com.ebrordermanagement.model.Orders;
import com.ebrordermanagement.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.ModelAttribute;

 

/**
 * An implementation of the ContactDAO interface.
 * @author www.codejava.net
 *
 */
public class OrderDetailsImpl implements OrderDetails 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public OrderDetailsImpl(DataSource dataSource) 
	{
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

 
	@ModelAttribute("shipnamelist")
	public List<Orders> getShipNameList() 
	{            
		String sql = "select distinct shipname  from orderdetails";
		List<Orders> listShipNames = jdbcTemplate.query(sql, new RowMapper<Orders>() 
				{
					public Orders mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						Orders aContact = new Orders(); 
						aContact.setshipname(rs.getString("shipname"));  
						return aContact;
					} 
				});
		
		return listShipNames;
	}

	 
	public List<Orders> list() 
	{
		String sql = "select date orderdate,shipname, orderno, noofcell,orderstatus,permitstatus,price from orderdetails";
		List<Orders> listContact = jdbcTemplate.query(sql, new RowMapper<Orders>() 
				{
			 
					public Orders mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						Orders aContact = new Orders();
						
						aContact.setshipname(rs.getString("shipname"));
						aContact.setorderno(rs.getString("orderno"));
						aContact.setdatetime(rs.getString("orderdate"));
						aContact.setnoofcell(rs.getInt("NoOfCell"));
						aContact.setorderstatus(rs.getString("OrderStatus"));
						aContact.setpermitstatus(rs.getString("PermitStatus"));
						aContact.setprice(rs.getString("Price"));
				
						return aContact;
					}
			
				});
		
		return listContact;
	} 
	 
	public List<Orders> list(String fromdate, String todate, String shipname)
	{
		String sql = "select date orderdate,shipname, orderno, noofcell,orderstatus,permitstatus,price from orderdetails "
				+ "where date >= STR_TO_DATE('"+ fromdate +"' ,'%m/%d/%Y')" 
				 + " and date <= STR_TO_DATE('"+ todate +"' ,'%m/%d/%Y')" 
				 + " and (shipname = '" +shipname + "' or '--Select ALL--' = '--Select ALL--') " ;    
		//System.out.println (sql );
		//System.out.println(fromdate +" " + todate + " " +sql);		
		List<Orders> listContact = jdbcTemplate.query(sql, new RowMapper<Orders>() 
				{

					public Orders mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						Orders aContact = new Orders();
				 
						aContact.setshipname(rs.getString("shipname"));
						aContact.setorderno(rs.getString("orderno"));
						aContact.setdatetime(rs.getString("orderdate"));
						aContact.setnoofcell(rs.getInt("NoOfCell"));
						aContact.setorderstatus(rs.getString("OrderStatus"));
						aContact.setpermitstatus(rs.getString("PermitStatus"));
						aContact.setprice(rs.getString("Price"));
				
						return aContact;
					}
			
				});		
		return listContact;
	}


	public List<Orders> list(String fromdate, String todate) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	public List<Orders> list(String shipname) {
		// TODO Auto-generated method stub
		String sql = "select date orderdate,shipname, orderno, noofcell,orderstatus,permitstatus,price from orderdetails "
				+ "where  "
				 + "   (shipname = '" +shipname + "'  ) " ;    
		//System.out.println (sql );
		//System.out.println(fromdate +" " + todate + " " +sql);		
		List<Orders> listContact = jdbcTemplate.query(sql, new RowMapper<Orders>() 
				{

					public Orders mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						Orders aContact = new Orders();
				 
						aContact.setshipname(rs.getString("shipname"));
						aContact.setorderno(rs.getString("orderno"));
						aContact.setdatetime(rs.getString("orderdate"));
						aContact.setnoofcell(rs.getInt("NoOfCell"));
						aContact.setprice(rs.getString("Price"));
						aContact.setorderstatus(rs.getString("OrderStatus"));
						aContact.setapprovalDateTime(rs.getString("ApprovalDateTime"));
						aContact.setrecvOnBoardDateTime(rs.getString("RecvOnBoardDateTime"));
		                
						
				
						return aContact;
					}
			
				});		
		return listContact;
	} 
	
	
	public List<Orders> list2(String shipname) {
		// TODO Auto-generated method stub
		String sql = " select o.date orderdate,o.shipname, o.orderno, o.noofcell,o.orderstatus,o.permitstatus,o.price,p.ApprovalDateTime ApprovalDateTime "
					+ " ,p.RecvOnBoardDateTime RecvOnBoardDateTime "
					+ "	from orderdetails o "
					+ "inner join productdetails p on o.orderno = p.orderno "   
					+ " where o.orderno  = p.orderno  and  o.shipname = p.shipname "
					+ " and  "
					+ " (o.shipname = '" +shipname + "'  ) " ;    
		//System.out.println (sql );
		//System.out.println(fromdate +" " + todate + " " +sql);		
		List<Orders> listContact = jdbcTemplate.query(sql, new RowMapper<Orders>() 
				{

					public Orders mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						Orders aContact = new Orders();
				 
						aContact.setshipname(rs.getString("shipname"));
						aContact.setorderno(rs.getString("orderno"));
						aContact.setdatetime(rs.getString("orderdate"));
						aContact.setnoofcell(rs.getInt("NoOfCell"));
						aContact.setprice(rs.getString("Price"));
						aContact.setorderstatus(rs.getString("OrderStatus"));
						aContact.setapprovalDateTime(rs.getString("ApprovalDateTime"));
						aContact.setrecvOnBoardDateTime(rs.getString("RecvOnBoardDateTime"));
		                
						
				
						return aContact;
					}
			
				});		
		return listContact;
	} 
	
	
	 public List<Orders>  list1(String shipname) 
	 {	
		 Connection connection = null;
			//System.out.println (productID);
			connection = FetchData.getConnection();
			
			List<Orders> listContact = null;
			
			
			 try 
			 {
		            Statement statement = connection.createStatement();	            
		            ResultSet rs;
		            
		            //System.out.print("end");
		            //System.out.print(orderno);
		           //CallableStatement cstmt = null;
		            connection = FetchData.getConnection();
		            CallableStatement cstmt = connection.prepareCall("{call USP_GetENCeOrderStatus (?)}");	          
		            
		            cstmt.setString(1, shipname);
		            rs = cstmt.executeQuery();
		            
		            while(rs.next()) {	
		            	Orders country=new Orders();
		            	country.setshipname(rs.getString("shipname"));
		            	country.setorderno(rs.getString("orderno"));
		            	country.setdatetime(rs.getString("orderdate"));
		            	country.setnoofcell(rs.getInt("NoOfCell"));
		            	country.setprice(rs.getString("Price"));
		            	country.setorderstatus(rs.getString("OrderStatus"));
		            	country.setapprovalDateTime(rs.getString("ApprovalDateTime"));
		            	country.setrecvOnBoardDateTime(rs.getString("RecvOnBoardDateTime"));
		                
		            	listContact.add(country);
		            }
		            
		            return listContact;
		            
		            
		            
		            
			 } catch (SQLException e) 
			 {
		            e.printStackTrace();
		            return null;
		     }
		}  
	 
	 
}
