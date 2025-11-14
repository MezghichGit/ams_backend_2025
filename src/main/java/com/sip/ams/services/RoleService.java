package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import com.sip.ams.entities.Role;

public interface RoleService {
	public List<Role> getAllRoles();
	public Role saveRole(Role role);
	public void deleteRoleById(int id);
	public Optional<Role> getRoleById(int id);
}