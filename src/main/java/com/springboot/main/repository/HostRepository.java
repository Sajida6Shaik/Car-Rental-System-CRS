package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Host;

public interface HostRepository extends JpaRepository<Host, Integer>{

}
