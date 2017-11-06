package com.ebrordermanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ebrordermanagement.model.MapData;
import com.ebrordermanagement.model.Orders;

public class MapDataFormImpl implements MapDataForm
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MapDataFormImpl(DataSource dataSource) 
	{
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<MapData> getContacts() 
	{
		//System.out.println("sql");
		
		//String sql = "select  productid,Location,Edition,LatestUpdate,UsageBand,SLAT,WLON,NLAT,ELON from CellMapingTable";
		String sql = "select  p.shipname,c.productid ,c.Location,c.Edition,c.LatestUpdate,c.UsageBand,c.SLAT,c.WLON,c.NLAT,c.ELON  "
				+ " from CellMapingTable c inner join productdetails p on c.productid = p.product "
				+ "inner join orderdetails o on o.OrderNo = p.OrderNo and o.ShipName = p.ShipName "
				+ "where o.orderno  = p.orderno  and  o.shipname = p.shipname";
		
		//System.out.println(sql);
		List<MapData> listContact = jdbcTemplate.query(sql, new RowMapper<MapData>() 
				{
			 
					public MapData mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						MapData aContact = new MapData();
						aContact.setshipname(rs.getString("shipname"));
						aContact.setproductid(rs.getString("productid"));
						aContact.setlocation(rs.getString("location"));
						aContact.setslat(rs.getDouble("slat"));
						aContact.setwlon(rs.getDouble("wlon"));
						aContact.setnlat(rs.getDouble("nlat"));
						aContact.setelon(rs.getDouble("elon"));
						 
				
						return aContact;
					}
			
				});
		
		return listContact;
		 
	}
	
	public List<MapData> getContacts1(String shipname ) 
	{
		//System.out.println("sql");
		
		//String sql = "select  productid,Location,Edition,LatestUpdate,UsageBand,SLAT,WLON,NLAT,ELON from CellMapingTable";
		String sql = "select  p.shipname,c.productid ,c.Location,c.Edition,c.LatestUpdate,c.UsageBand,c.SLAT,c.WLON,c.NLAT,c.ELON  "
				+ " from CellMapingTable c inner join productdetails p on c.productid = p.product "
				+ "inner join orderdetails o on o.OrderNo = p.OrderNo and o.ShipName = p.ShipName "
				+ "where o.orderno  = p.orderno "				 
				+ "and  o.shipname = '" +  shipname + "'";
		
		//System.out.println(sql);
		List<MapData> listContact = jdbcTemplate.query(sql, new RowMapper<MapData>() 
				{
			 
					public MapData mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						MapData aContact = new MapData();
						aContact.setshipname(rs.getString("shipname"));
						aContact.setproductid(rs.getString("productid"));
						aContact.setlocation(rs.getString("location"));
						aContact.setslat(rs.getDouble("slat"));
						aContact.setwlon(rs.getDouble("wlon"));
						aContact.setnlat(rs.getDouble("nlat"));
						aContact.setelon(rs.getDouble("elon"));
						 
				
						return aContact;
					}
			
				});
		
		return listContact;
		 
	}
	
	
	public List<MapData> getContacts(String shipname, String fromdate, String todate) 
	{
		//System.out.println("three parameter sql");
		
		//String sql = "select  productid,Location,Edition,LatestUpdate,UsageBand,SLAT,WLON,NLAT,ELON from CellMapingTable";
		String sql = "select  p.shipname,c.productid ,c.Location,c.Edition,c.LatestUpdate,c.UsageBand,c.SLAT,c.WLON,c.NLAT,c.ELON  "
				+ " from CellMapingTable c inner join productdetails p on c.productid = p.product "
				+ "inner join orderdetails o on o.OrderNo = p.OrderNo and o.ShipName = p.ShipName "
				+ "where o.orderno  = p.orderno "				
				+ "and date >= STR_TO_DATE('"+ fromdate +"' ,'%m/%d/%Y')" 
				+ "and date <= STR_TO_DATE('"+ todate +"' ,'%m/%d/%Y')" 
				+ "and  o.shipname = '" +  shipname + "'";
		
		//System.out.println(sql);
		List<MapData> listContact = jdbcTemplate.query(sql, new RowMapper<MapData>() 
				{
			 
					public MapData mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						MapData aContact = new MapData();
						aContact.setshipname(rs.getString("shipname"));
						aContact.setproductid(rs.getString("productid"));
						aContact.setlocation(rs.getString("location"));
						aContact.setslat(rs.getDouble("slat"));
						aContact.setwlon(rs.getDouble("wlon"));
						aContact.setnlat(rs.getDouble("nlat"));
						aContact.setelon(rs.getDouble("elon"));
						 
				
						return aContact;
					}
			
				});
		
		return listContact;
		 
	}
	
	public List<MapData> getContacts(String shipname, String fromdate, String todate,String usageband) 
	{
		//System.out.println("three parameter sql");
		
		//String sql = "select  productid,Location,Edition,LatestUpdate,UsageBand,SLAT,WLON,NLAT,ELON from CellMapingTable";
		String sql = "select  p.shipname,c.productid ,c.Location,c.Edition,c.LatestUpdate,c.UsageBand,c.SLAT,c.WLON,c.NLAT,c.ELON  "
				+ " from CellMapingTable c inner join productdetails p on c.productid = p.product "
				+ "inner join orderdetails o on o.OrderNo = p.OrderNo and o.ShipName = p.ShipName "
				+ "where o.orderno  = p.orderno "
				+ "and c.Usageband in ("+ usageband +"  ) "
				+ "and date >= STR_TO_DATE('"+ fromdate +"' ,'%m/%d/%Y')" 
				+ "and date <= STR_TO_DATE('"+ todate +"' ,'%m/%d/%Y')" 
				+ "and  o.shipname = '" +  shipname + "'"
						+ " order by c.Nlat desc";
		
		//System.out.println(sql);
		List<MapData> listContact = jdbcTemplate.query(sql, new RowMapper<MapData>() 
				{
			 
					public MapData mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						MapData aContact = new MapData();
						aContact.setshipname(rs.getString("shipname"));
						aContact.setproductid(rs.getString("productid"));
						aContact.setlocation(rs.getString("location"));
						aContact.setslat(rs.getDouble("slat"));
						aContact.setwlon(rs.getDouble("wlon"));
						aContact.setnlat(rs.getDouble("nlat"));
						aContact.setelon(rs.getDouble("elon"));
						 
				
						return aContact;
					}
			
				});
		
		return listContact;
		 
	}

	public void setContacts(List<MapData> contacts) {
		// TODO Auto-generated method stub
		
	}

	public List<MapData> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
