package com.ebrordermanagement.model;
 

public class Password {
		
		private String emailid;
	
		private String password;
		
		private String passwordConf;
		
		 public String getemailid() 
		  {
				return emailid;
		  }
			
		  public void setemailid(String emailid) 
		  {
				this.emailid = emailid;
		  }
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPasswordConf() {
			return passwordConf;
		}

		public void setPasswordConf(String passwordConf) {
			this.passwordConf = passwordConf;
		}
		
		

}