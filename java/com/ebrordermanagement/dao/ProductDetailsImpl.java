package com.ebrordermanagement.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ebrordermanagement.model.Product;

public class  ProductDetailsImpl implements ProductDetails {
	
	 
	private JdbcTemplate jdbcTemplate;
	
	 
	public ProductDetailsImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	 
	public void saveOrUpdate(String ProductID) {
		// TODO Auto-generated method stub
		
		//System.out.println (ProductID );
		String sql = "UPDATE ProductDetails SET Approved='YES'  "
			+ " WHERE ProductID=?";
		jdbcTemplate.update(sql, ProductID);
	}


	public void saveOrUpdate() {
		// TODO Auto-generated method stub
		
	}

	 

	

	 

}
