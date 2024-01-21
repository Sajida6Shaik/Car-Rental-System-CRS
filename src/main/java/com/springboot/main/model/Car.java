package com.springboot.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
 
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int carId;
	private String carModel;
	private double price;
	private float mileage;
	private String fuelType;
	private int seating;
	private String insurance;
	@OneToOne
	private Host host;
	
	//Generate Getters and Setters
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public float getMileage() {
		return mileage;
	}
	public void setMileage(float mileage) {
		this.mileage = mileage;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public int getSeating() {
		return seating;
	}
	public void setSeating(int seating) {
		this.seating = seating;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public Host getHost() {
		return host;
	}
	public void setHost(Host host) {
		this.host = host;
	} 
	
 
	
	
}
