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

import com.springboot.main.dto.HostDto;
import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Host;
import com.springboot.main.model.User;
import com.springboot.main.service.HostService;
import com.springboot.main.service.UserService;

@RestController
public class HostController {
	

	@Autowired
	private HostService hostService;
	@Autowired
	private UserService userService;
	
	
	/*       {
    "hostEmail":"",
    "hostName":"",
    "hostContact":"",
    "user":{
    "username":"",
    "password":"",
    "role":""
    }

  } 
	 */
	
	@PostMapping("/host/user/add")
	public Host postHost(@RequestBody Host host) {
		//Step1:save User :user with id
		
		User user = userService.postUser(host.getUser());
		
		//step2:attach saved user(with id) to host
		
		host.setUser(user);
		//step3:save host
	host = hostService.postHost(host);
	return host;
	}
	
	
	
	
	//GET ALL HOSTS
@GetMapping("/host/all")
	
	public List<Host> getAllHosts(@RequestParam(value= "page",required = false,defaultValue ="0")Integer page,
		                    @RequestParam(value="size",required = false, defaultValue = "1000000" )Integer size) {
		
		
		Pageable pageable = PageRequest.of(page,size);
		return hostService.getAllhosts(pageable);
		
}

  
//GET HOST BY ID
@GetMapping("/host/one/{hid}")
public ResponseEntity<?> getById(@PathVariable("hid")int hid) {
try {
Host host = hostService.getById(hid);
	 	
	return ResponseEntity.ok().body(host);
}catch(InvalidIdException e) {
	return ResponseEntity.badRequest().body(e.getMessage());
}

 
}



//UPDATE HOST DATA
  

	@PutMapping("/host/update/{hid}")
	public ResponseEntity<?> updateHost(@PathVariable("hid") int hid,@RequestBody HostDto hostDto){
		try {
			Host host = hostService.getById(hid);
			if(hostDto.getHostEmail()!=null)
				host.setHostEmail(hostDto.getHostEmail());
			if(hostDto.getHostName()!=null)
				host.setHostName(hostDto.getHostName());
			if(hostDto.getHostContact()!=null)
				host.setHostContact(hostDto.getHostContact());
			host= hostService.postHost(host);
			return ResponseEntity.ok().body(host);	
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
 
	}
	
	//DELETE AN HOST
	
	@DeleteMapping("host/delete/{hid}")
	public ResponseEntity<?> deleteHost(@PathVariable("hid") int hid) throws InvalidIdException {
		
		//validate id
		Host host = hostService.getById(hid);
		//delete
		hostService.deleteHost(host);
		return ResponseEntity.ok().body("Host deleted successfully");
	}






















//GET ALL USERS
@GetMapping("/user/all")

public List<User> getAllUsers(@RequestParam(value= "page",required = false,defaultValue ="0")Integer page,
	                    @RequestParam(value="size",required = false, defaultValue = "1000000" )Integer size) {
	
	
	Pageable pageable = PageRequest.of(page,size);
	return userService.getAllUsers(pageable);
	
}

//GET USER BY ID
@GetMapping("/user/one/{uid}")
public ResponseEntity<?> getByUserId(@PathVariable("uid")int uid) {
try {
User user = userService.getById(uid);
	 	
	return ResponseEntity.ok().body(user);
}catch(InvalidIdException e) {
	return ResponseEntity.badRequest().body(e.getMessage());
}


}

@PutMapping("/user/update/{uid}")
public ResponseEntity<?> updateUser(@PathVariable("uid") int uid,@RequestBody User newUser){
	try {
		User user = userService.getById(uid);
		
		
		if(newUser.getUserId()!=0)
			user.setUserId(newUser.getUserId());
			
		if(newUser.getUsername()!=null)
			user.setUsername(newUser.getUsername());
		
			
		if(newUser.getRole()!=null)
			user.setRole(newUser.getRole());
		
		if(newUser.getPassword()!=null)
			user.setPassword(newUser.getPassword());
		
		 
		
		user= userService.postUser(user);
		return ResponseEntity.ok().body(user);	
		
	}catch(InvalidIdException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

 

}
 

}
