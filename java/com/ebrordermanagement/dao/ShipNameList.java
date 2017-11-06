package com.ebrordermanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ebrordermanagement.model.Orders;
import com.ebrordermanagement.model.ShipName;

public class ShipNameList 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ShipNameList(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@ModelAttribute("shipnamelist")
	public List<ShipName> getShipNameList() {            
		String sql = "select distinct shipname  from orderdetails";
		List<ShipName> listShipNames = jdbcTemplate.query(sql, new RowMapper<ShipName>() {

			 
			public ShipName mapRow(ResultSet rs, int rowNum) throws SQLException {
				ShipName aContact = new ShipName(); 
				aContact.setShipName(rs.getString("shipname"));  
				return aContact;
			} 
		});
		
		return listShipNames;
	}
}
