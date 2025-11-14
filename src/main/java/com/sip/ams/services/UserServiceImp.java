package com.sip.ams.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sip.ams.entities.User;
import com.sip.ams.repositories.UserRepository;

@Service
public class UserServiceImp  implements UserService{

	private final UserRepository userRepository;
	
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return this.userRepository.findById(id).get();
	}
}