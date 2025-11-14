package com.sip.ams.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Role;
import com.sip.ams.services.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	@Autowired
	RoleService roleService;
	
	@GetMapping("/")
	@Operation(summary = "Récupération de tous les roles")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Succès de get All Roles"),
			@ApiResponse(responseCode = "500", description = "Problème lors de la récupération") })
	public ResponseEntity<List<Role>> getAllRoles() {
		return new ResponseEntity<>(this.roleService.getAllRoles(), HttpStatus.OK);
	}
	
	@PostMapping("/")
	@Operation(summary = "Ajout d'un nouveau role")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Insertion avec succès"),
			@ApiResponse(responseCode = "500", description = "Problème lors de l'insertion") })
	
	
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		return new ResponseEntity<>(this.roleService.saveRole(role), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Suppression d'un role par son id")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Si provider est trouvé puis supprimé"),
			@ApiResponse(responseCode = "404", description = "Provider inexistant") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Role> deleteRoleById(@PathVariable int id){
		
		Optional<Role> opt = this.roleService.getRoleById(id);
		
		if (opt.isEmpty())
			return ResponseEntity.notFound().build();  // code 404
		else {
			this.roleService.deleteRoleById(id);
			return ResponseEntity.noContent().build();  // code 204
		}
	}
}