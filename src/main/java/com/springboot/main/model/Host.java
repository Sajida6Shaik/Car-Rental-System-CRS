package com.springboot.main.model;

 

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity
public class Host {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int hostId; 
	private String hostEmail;	
	private String hostName;
	private String hostContact;
	@OneToOne
	private User user;
	
	//generate Getters and setters
	 
	 
	
	public String getHostEmail() {
		return hostEmail;
	}
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostContact() {
		return hostContact;
	}
	public void setHostContact(String hostContact) {
		this.hostContact = hostContact;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	 
	
	
  
	
	
	
}
