package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Admin;
import com.springboot.main.model.Car;
import com.springboot.main.model.CustomerCar;
import com.springboot.main.model.User;
import com.springboot.main.service.AdminService;
import com.springboot.main.service.UserService;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	



	
	
	@PostMapping("/admin/post/{uid}")
	public ResponseEntity<?> postAdmin(@RequestBody Admin admin,@PathVariable("uid") int uid) {
		try {
			User user = userService.getById(uid);
			admin.setUser(user);
			admin=adminService.insert(admin);
			return ResponseEntity.ok().body(admin);
			
		}	catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
				
		}
}
	//GET ALL ADMIN
	
	@GetMapping("/admin/getall")  
	public List<Admin> getAll(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
							   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) {  
		Pageable pageable = PageRequest.of(page, size);  
		return adminService.getAll(pageable);
	}
	
	
	
	//GET ADMIN BY ID
	@GetMapping("/admin/one/{aid}")
	
public ResponseEntity<?> getById(@PathVariable("aid")int aid) {
	 
	try {
		Admin admin = adminService.getById(aid);
			
		return ResponseEntity.ok().body(admin);
		
			
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		}
	
	
//	//UPDATE ADMIN
	@PutMapping("/admin/update/{aid}")   
	public ResponseEntity<?> updateAdmin(@PathVariable("aid") int aid,
							@RequestBody Admin newAdmin) throws InvalidIdException {
		//validate id
		Admin oldAdmin = adminService.getById(aid);
		
		if(newAdmin.getAdminName() != null)
			oldAdmin.setAdminName(newAdmin.getAdminName());
		if(newAdmin.getEmail() != null) 
			oldAdmin.setEmail(newAdmin.getEmail()); 
		if(newAdmin.getPhoneNo() != null)
			oldAdmin.setPhoneNo(newAdmin.getPhoneNo());
		 
		oldAdmin = adminService.insert(oldAdmin); 
		return ResponseEntity.ok().body(oldAdmin);
	}
	 


@DeleteMapping("/admin/delete/{aid}")
public ResponseEntity<?> deleteAdmin(@PathVariable("aid") int aid) throws InvalidIdException {
	
	//validate id
	Admin admin = adminService.getById(aid);
	//delete
	adminService.deleteAdmin(admin);
	return ResponseEntity.ok().body("Admin deleted successfully");
}
}
