package com.caresoft.models;
import java.util.Date;
import java.util.ArrayList;

import com.caresoft.clinicapp.HIPAACompliantAdmin;
import com.caresoft.clinicapp.HIPAACompliantUser;


public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {

	private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents = new ArrayList<String>();
  
    
	  public AdminUser(int i, String string) {
		super(i);
		setRole(string);
		}
	
	  
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}
	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}
	
	    
	   	    
	    public void newIncident(String notes) {
	        String report = String.format(
	            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
	            new Date(), this.id, notes
	        );
	        securityIncidents.add(report);
	    }
	    
	    public void authIncident() {
	        String report = String.format(
	            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
	            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
	        );
	        securityIncidents.add(report);
	    }
	    
	    @Override
		public boolean assignPin(int pin) {
			int pinCheck = String.valueOf(pin).length();
			if(pinCheck == 6) {
				return true;
			}else {
			return false;
			}
		}

		@Override
		public boolean accessAuthorized(Integer confirmedAuthID) {
			if(confirmedAuthID == this.id) {
				return true;
				
			}else {
				authIncident();
				return false;
			}
			
		}
		@Override
		public ArrayList<String> reportSecurityIncidents() {
			return this.securityIncidents;
			
		}
}
