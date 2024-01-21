package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Car;

public interface CarRepository  extends JpaRepository<Car, Integer> {

}
