package com.ebrordermanagement.dao;

 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
 
import java.sql.CallableStatement;
 



import org.springframework.jdbc.core.JdbcTemplate;
 

import com.ebrordermanagement.model.Orders;
import com.ebrordermanagement.model.Product;

public class FetchDataMap {

    private static Connection connection = null;
    private static JdbcTemplate jdbcTemplate;
    
    
    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = FetchDataMap.class.getClassLoader().getResourceAsStream("/db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
    
    public static ArrayList<Product> getAllCountries(String ShipName, String OrderNo) {
    	connection = FetchDataMap.getConnection();
        ArrayList<Product> countryList = new ArrayList<Product>();
        try {
            Statement statement = connection.createStatement();
            
      // System.out.println ( ShipName);

          // System.out.println ( + OrderNo);
            ResultSet rs = statement.executeQuery
           ("select  orderno,productid,shipname,orderdate,ProductType,Product,Title,Scale,UsageBand,ExpireDate,Status,InstalledInCharts"
           		+ ",case when Approved ='yes' then 'TRUE' ELSE 'FALSE' END Approved"
           		+ ",case when Rejected ='yes' then 'TRUE' ELSE 'FALSE' END Rejected from productdetails "
           		+ " where shipname = '" +  ShipName + "'"  
           		+ " and product = '" +  OrderNo + "'");
        
            while(rs.next()) {	
            	Product country=new Product();
            	country.setorderno(rs.getString("orderno"));
            	country.setproductid(rs.getInt("productid"));
            	country.setshipname(rs.getString("shipname"));
            	country.setorderdate(rs.getString("orderdate"));
            	country.setproducttype(rs.getString("producttype"));
            	country.setproduct(rs.getString("Product"));
            	country.settitle(rs.getString("title"));
            	country.setscale(rs.getString("Scale"));
            	country.setusageband(rs.getString("UsageBand"));
            	country.setexpiredate(rs.getString("ExpireDate"));
            	country.setstatus(rs.getString("Status"));
            	country.setinstalledInCharts(rs.getString("InstalledInCharts"));
            	country.setapproved(rs.getString("Approved"));
            	country.setrejected(rs.getString("Rejected"));
                
            	countryList.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryList;
    }
    
    public static void saveOrUpdateAppChecked(String productID) 
    {	
		//System.out.println ( "save productid " + productID);
		connection = FetchDataMap.getConnection();
		 try 
		 {
	            Statement statement = connection.createStatement();
	            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	            //System.out.println (sqlDate);
	            int rs = statement.executeUpdate("update productdetails  set approved = 'YES' , ApprovalDateTime = '" + sqlDate + "' "	            		
	            		 
	            		+ " where ProductID="+ productID);
	            
		 } catch (SQLException e) 
		 {
	            e.printStackTrace();
	     }
	}
    
    public static void saveOrUpdateAppUnChecked(String productID) 
    {	
    	//System.out.println ( "save productid " + productID);
		connection = FetchDataMap.getConnection();
		 try 
		 {
	            Statement statement = connection.createStatement();
	            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	           // System.out.println (sqlDate);
	            int rs = statement.executeUpdate("update productdetails  set approved = 'NO' , ApprovalDateTime =  '" + sqlDate + "' "
	            		+ "where ProductID="+ productID);
	            
		 } catch (SQLException e) 
		 {
	            e.printStackTrace();
	     }
	}
    
    public static void saveOrUpdateRejChecked(String productID) 
    {	
    	//System.out.println ( "save productid " + productID);
		connection = FetchDataMap.getConnection();
		 try 
		 {
	            Statement statement = connection.createStatement();
	            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	           // System.out.println (sqlDate);
	            int rs = statement.executeUpdate("update productdetails  set rejected = 'YES' , ApprovalDateTime =  '" + sqlDate + "' "
	            		+ "where ProductID="+ productID);
	            
		 } catch (SQLException e) 
		 {
	            e.printStackTrace();
	     }
	}
    
    public static void saveOrUpdateRejUnChecked(String productID) 
    {	
    	//System.out.println ( "save productid " + productID);
		connection = FetchDataMap.getConnection();
		 try 
		 {
	            Statement statement = connection.createStatement();
	            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	            int rs = statement.executeUpdate("update productdetails  set rejected = 'NO' , ApprovalDateTime =  '" + sqlDate + "' "
	            		+ "where ProductID="+ productID);
	            
		 } catch (SQLException e) 
		 {
	            e.printStackTrace();
	     }
	}
    
    public static void saveOrUpdateOrderStatus(String orderdate, String orderno, String shipname) 
    {	
    	//System.out.println ( "save productid " + productID);
		connection = FetchDataMap.getConnection();
		 try 
		 {
	            Statement statement = connection.createStatement();	            
	            ResultSet rs;
	            
	            //System.out.print(orderdate);
	            //System.out.print(orderno);
	           //CallableStatement cstmt = null;
	            connection = FetchDataMap.getConnection();
	            CallableStatement cstmt = connection.prepareCall("{call USP_saveOrUpdateOrderStatus (?, ?)}");
	            //cstmt = "{call USP_saveOrUpdateOrderStatus (?, ?, ?)}";
	            //cstmt = connection.prepareCall (SQL);
	           // cstmt.setString(1, orderdate );
	            cstmt.setString(1, orderno);
	            cstmt.setString(2, shipname);
	            cstmt.executeUpdate();
	            
	            
		 } catch (SQLException e) 
		 {
	            e.printStackTrace();
	     }
	}    
     
    
}