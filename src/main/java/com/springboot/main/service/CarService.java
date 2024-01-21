package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Car;
import com.springboot.main.model.Host;
import com.springboot.main.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;

	public Car insert(Car car) {
		 
		return carRepository.save(car);
	}
	
	
	
	//GET MAPPING GET ALL CARS
		public List<Car> getAllCars() {
			 
			return carRepository.findAll();
		}
		
		
	//GET CAR BY ID

	public Car getById(int carid) throws InvalidIdException {
		Optional<Car>optional =carRepository.findById(carid) ;
		if(!optional.isPresent())
			throw new InvalidIdException("CarID is Invalid");
		 
		return optional.get();
	}

//TO DELETE A CAR
	
	

	public void deleteCar(int carid) {
		carRepository.deleteById(carid);
		
		 
	}



	 


	 

	
	 
 

}
