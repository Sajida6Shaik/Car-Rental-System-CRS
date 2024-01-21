package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.CustomerCar;
import com.springboot.main.repository.CustomerCarRepository;

@Service
public class CustomerCarService {
	
	@Autowired
	private CustomerCarRepository customerCarRepository;

	public CustomerCar insert(CustomerCar customerCar) {
		return  customerCarRepository.save(customerCar);
	}
	
	
	
	//GET ALL CUSTOMERS
		public List<CustomerCar> getAll(Pageable pageable) {
			return customerCarRepository.findAll(pageable).getContent();
			 
		}

	
	//GET BY ID -CUSTOMER CAR
	
	public CustomerCar getById(int custid) throws InvalidIdException {
		Optional<CustomerCar>optional = customerCarRepository.findById(custid);
		if(!optional.isPresent())
			throw new InvalidIdException("CustomerID Invalid");
		
		 
		 return optional.get();
	}


	 
	 

	 
}
