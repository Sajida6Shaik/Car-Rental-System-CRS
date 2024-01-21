package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

 
import com.springboot.main.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	 

}
