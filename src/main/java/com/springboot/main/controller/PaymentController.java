package com.springboot.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.dto.PaymentDto;
import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Customer;
 
import com.springboot.main.model.Payment;
import com.springboot.main.service.CustomerService;
import com.springboot.main.service.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/payment/add/{cid}")
	public ResponseEntity<?> insertPayment(@PathVariable("cid")int cid,
			@RequestBody Payment payment){
	try{
		
		Customer customer = customerService.getById(cid);
		
		payment.setCustomer(customer);
		
		payment = paymentService.insert(payment);
		return ResponseEntity.ok().body(payment);
	} catch(InvalidIdException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	 
}
	//GET ALL PAYMENTS
	
	@GetMapping("/payment/getall")  
	public List<Payment> getAll(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
							   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) {  
		Pageable pageable = PageRequest.of(page, size);  
		return paymentService.getAll(pageable);
	}
	
	
	
	//GET ONE PAYMENT
	
	@GetMapping("/payment/getone/{pid}")
	public ResponseEntity<?> getone(@PathVariable("pid")int pid) throws InvalidIdException {
		try {
	     Payment payment =paymentService.getByPaymentId(pid);
		return ResponseEntity.ok().body(payment);
	}
	catch (InvalidIdException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
			
	}
}
	
	
	
	//UPDATE PAYMENT
	
	
	@PutMapping("/payment/update/{pid}")   
	public ResponseEntity<?> updatePayment(@PathVariable("pid") int pid,
							@RequestBody PaymentDto newPayment) throws InvalidIdException {
		//validate id
		Payment oldpayment = paymentService.getByPaymentId(pid);
		
		if(newPayment.getPaymentPrice()!= 0)
			oldpayment.setPaymentPrice(newPayment.getPaymentPrice());

		if(newPayment.getPaymentType()!= null)
			oldpayment.setPaymentType(newPayment.getPaymentType());
		
		
		
		 
		oldpayment =paymentService.insert(oldpayment); 
		return ResponseEntity.ok().body(oldpayment);
	}
	
}
