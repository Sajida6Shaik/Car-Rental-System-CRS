package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Customer;
import com.springboot.main.model.Payment;
import com.springboot.main.repository.CustomerRepository;
import com.springboot.main.repository.PaymentRepository;

@Service
public class CustomerService   {
	
	@Autowired
	private CustomerRepository customerRepository;

	public Customer postCustomer(Customer customer) {
		 
		return customerRepository.save(customer);
	}

	public Customer insert(Customer customer) {
		 
		return customerRepository.save(customer);
	}

	
	
	//GET CUSTOMER BY ID
	public Customer getById(int cid) throws InvalidIdException {
		Optional<Customer>optional = customerRepository.findById(cid);
		if(!optional.isPresent())
			throw new InvalidIdException("CustomerID Invalid");
		
		 
		 return optional.get();
	}

	
	
	
	
	//GET  ALL CUSTOMER 
	public List<Customer> getAll() {
		 
		return customerRepository.findAll();
	}

//	public Host getById(int hid) throws InvalidIdException {
//	Optional<Host> optional =hostRepository.findById(hid);
//	if(!optional.isPresent())
//		throw new InvalidIdException("HostID Invalid");
//	return optional.get();
//}


	 

}
