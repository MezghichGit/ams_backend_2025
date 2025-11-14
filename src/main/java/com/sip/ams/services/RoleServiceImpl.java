package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.ams.entities.Provider;
import com.sip.ams.entities.Role;
import com.sip.ams.repositories.ProviderRepository;
import com.sip.ams.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return (List<Role>) this.roleRepository.findAll();
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return this.roleRepository.save(role);
	}

	@Override
	public void deleteRoleById(int id) {
		// TODO Auto-generated method stub
		this.roleRepository.deleteById(id);
	}

	@Override
	public Optional<Role> getRoleById(int id) {
		// TODO Auto-generated method stub
		return this.roleRepository.findById(id);
	}
}