package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Admin;
import com.springboot.main.repository.AdminRepository;
 

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	 
	
	
	public Admin insert(Admin admin) {
		 
		return adminRepository.save(admin);
	}

//GET ALL ADMIN

	public List<Admin> getAll(Pageable pageable) {
		 return adminRepository.findAll(pageable).getContent();
	}

	 //GET ADMIN BY ID
	public Admin getById(int aid) throws InvalidIdException {
		Optional<Admin> optional=adminRepository.findById(aid);
		if(!optional.isPresent())
			throw new InvalidIdException("admin id invalid");
				return optional.get();
	}
	//TO DELETE AN ADMIN

	public void deleteAdmin(Admin admin) {
		 
		adminRepository.delete(admin);
	}

	 
	 
	 


}
