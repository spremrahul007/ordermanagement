package com.ebrordermanagement.model;

public class MapData {
	
	    
	    private String shipname;
		private String productid;
		private String location;
		private double slat;
		private double wlon;
		private double nlat;
		private double elon;
		
		//public MapData(String productid, String location, double slat, double wlon, double nlat, double elon) {
			//this.productid = productid;
			//this.location = location;
			//this.slat = slat;
			//this.wlon = wlon;
			//this.wlon = nlat;
			//this.wlon = elon;
		//}
		
		 public String getshipname() 
		  {
				return shipname;
		  }
			
		  public void setshipname(String shipname) 
		  {
				this.shipname = shipname;
		  } 
		  
		  public String getproductid() 
		  {
				return productid;
		  }
			
		  public void setproductid(String productid) 
		  {
				this.productid = productid;
		  } 
		
		  
		  public String getlocation() 
		  {
				return location;
		  }
			
		  public void setlocation(String location) 
		  {
				this.location = location;
		  } 
		  
		  public double getslat() 
		  {
				return slat;
		  }
			
		  public void setslat(double slat) 
		  {
				this.slat = slat;
		  } 
		  
		  public double getwlon() 
		  {
				return wlon;
		  }
			
		  public void setwlon(double wlon) 
		  {
				this.wlon = wlon;
		  } 
		  
		  public double getnlat() 
		  {
				return nlat;
		  }
			
		  public void setnlat(double nlat) 
		  {
				this.nlat = nlat;
		  } 
		  
		  
		  public double getelon() 
		  {
				return elon;
		  }
			
		  public void setelon(double elon) 
		  {
				this.elon = elon;
		  } 
		// Getter and Setter methods
	 

}
