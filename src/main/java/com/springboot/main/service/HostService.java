package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Host;
import com.springboot.main.repository.HostRepository;

@Service
public class HostService {
	
	@Autowired
	
private HostRepository hostRepository;
	
	public Host postHost(Host host) {
		 
		return hostRepository.save(host);
	}
	
	
	//GET ALL HOSTS
	public List<Host> getAllhosts(Pageable pageable) {
		 
		return hostRepository.findAll(pageable).getContent();
	}
	
	
	//GET HOST BY ID
	public Host getById(int hid) throws InvalidIdException {
		Optional<Host> optional =hostRepository.findById(hid);
		if(!optional.isPresent())
			throw new InvalidIdException("HostID Invalid");
		return optional.get();
	}

       //DELETE AN HOST
	
	public void deleteHost(Host host) {
		hostRepository.delete(host);
		
	}
 
	}

	
 
	

