package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.dto.CustomerDto;
import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Car;
import com.springboot.main.model.Customer;
import com.springboot.main.model.Host;
import com.springboot.main.model.User;
import com.springboot.main.service.CustomerService;
import com.springboot.main.service.UserService;

@RestController
 
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	

	/* 
	 * 
	 * 
	 *  {
    "custId": 1,
    "age": 54,
    "city": "Delhi",
    "area": "Ajmer",
    "date": "2024-09-25",
    "emailId": "ProphetMohammad56@gmail.com",
    "user": {
    "userId": 1
  }
}
 
	 * 
	 * 
	 * 
	 * 
	 * {
	 */
		   
	

	@PostMapping("/customer/post/{uid}")
	public ResponseEntity<?> postCustomer(@RequestBody Customer customer,@PathVariable("uid") int uid) {
		try {
			User user = userService.getById(uid);
			customer.setUser(user);
			customer=customerService.insert(customer);
			return ResponseEntity.ok().body(customer);
			
		}	catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
				
		}
	}
		
		//GET ALL  CUSTOMERS
		@GetMapping("/customer/getall")
		public List<Customer> getAllCustomer() {
			return customerService.getAll() ;
			
			
		}
	
		
		//GET CUSTOMER BY ID
@GetMapping("/customer/one/{cid}")
public ResponseEntity<?> getById(@PathVariable("cid")int cid) {
	 
	try {
		Customer customer = customerService.getById(cid);
			
		return ResponseEntity.ok().body(customer);
		
			
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
}

//UPDATE CUSTOMER

@PutMapping("/customer/update/{cid}")
public ResponseEntity<?> updateCustomer(@PathVariable("cid") int cid,@RequestBody CustomerDto customerDto){
	try {
		Customer customer = customerService.getById(cid);
		if(customerDto.getAge()!=0)
			customer.setAge(customerDto.getAge());
		if(customerDto.getCity()!=null)
			customer.setCity(customerDto.getCity());
		if(customerDto.getArea()!=null)
			customer.setArea(customerDto.getArea());
		if(customerDto.getDate()!=null)
			customer.setDate(customerDto.getDate());
		if(customerDto.getEmailId()!=null)
			customer.setEmailId(customerDto.getEmailId());
		customer=customerService.postCustomer(customer);
		return ResponseEntity.ok().body(customer);	
	}catch(InvalidIdException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
}
 


	


