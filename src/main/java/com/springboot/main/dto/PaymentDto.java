package com.springboot.main.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PaymentDto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String paymentType;
	private double paymentPrice;
	
	//GENERATE GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public double getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(double paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	
	 
	

}
