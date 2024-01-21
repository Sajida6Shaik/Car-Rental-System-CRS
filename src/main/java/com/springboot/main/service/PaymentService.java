package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Payment;
import com.springboot.main.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;


	public Payment insert(Payment payment) {
		 
		return paymentRepository.save(payment);
	}

//GET LIST OF PAYMENTS
	public List<Payment> getAll(Pageable pageable) {
	  return paymentRepository.findAll(pageable).getContent();
			 
		}
	
	
//GET PAYMENT BY ID
	
	
	public Payment getByPaymentId(int pid) throws InvalidIdException {
		Optional<Payment> optional=paymentRepository.findById(pid);
		if(!optional.isPresent())
			throw new InvalidIdException("payment id invalid");
				return optional.get();
	}
}
