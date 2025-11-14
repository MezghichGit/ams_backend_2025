package com.sip.ams.services;

import java.util.List;
import com.sip.ams.entities.User;

public interface UserService {
	
	List<User>listUsers();
	
	User getUserById(long id);
}