package com.ebrordermanagement.model;

public class Orders 
{		
	  private int id;
	  private String shipname;
	  private String orderno;
	  private String datetime;
	  private int noofcell;
	  private String orderstatus;
	  private String permitstatus;
	  private String price;
	  private String approvalDateTime;
	  private String recvOnBoardDateTime;
	  
	   
	  public int getId() {
			return id;
		}
	  public void setId(int id) 
	  {
			this.id = id;
	  }
		
	  public String getshipname() 
	  {
			return shipname;
	  }
		
	  public void setshipname(String shipname) 
	  {
			this.shipname = shipname;
	  }
	  
	  public String getorderno() 
	  {
			return orderno;
	  }
		
	  public void setorderno(String orderno) 
	  {
			this.orderno = orderno;
	  }
	  
	  public String getdatetime() 
	  {
			return datetime;
	  }
		
	  public void setdatetime(String datetime) 
	  {
			this.datetime = datetime;
	  }
	  
	  public int getnoofcell() 
	  {
			return noofcell;
	  }
		
	  public void setnoofcell(int noofcell) 
	  {
			this.noofcell = noofcell;
	  }
	  
	  public String getorderstatus() 
	  {
			return orderstatus;
	  }
		
	  public void setorderstatus(String orderstatus) 
	  {
			this.orderstatus = orderstatus;
	  }
	  
	  public String getpermitstatus() 
	  {
			return permitstatus;
	  }
		
	  public void setpermitstatus(String permitstatus) 
	  {
			this.permitstatus = permitstatus;
	  }
	  
	  public String getprice() 
	  {
			return price;
	  }
		
	  public void setprice(String price) 
	  {
			this.price = price;
	  }
	   
	  public String getapprovalDateTime() 
	  {
			return approvalDateTime;
	  }
		
	  public void setapprovalDateTime(String ApprovalDateTime) 
	  {
			this.approvalDateTime = ApprovalDateTime;
	  }
	  
	  
	  public String getrecvOnBoardDateTime() 
	  {
			return recvOnBoardDateTime;
	  }
		
	  public void setrecvOnBoardDateTime(String RecvOnBoardDateTime) 
	  {
			this.recvOnBoardDateTime = RecvOnBoardDateTime;
	  }
}
