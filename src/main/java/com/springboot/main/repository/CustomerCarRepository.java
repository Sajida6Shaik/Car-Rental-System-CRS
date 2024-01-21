package com.springboot.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.CustomerCar;

public interface CustomerCarRepository extends JpaRepository<CustomerCar, Integer> {

	List<CustomerCar> findByCustomer_custId(int custid);

}
