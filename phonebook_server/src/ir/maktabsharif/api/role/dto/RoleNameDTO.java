package ir.maktabsharif.api.role.dto;

import java.util.HashSet;

import ir.maktabsharif.model.entity.Feature;
import ir.maktabsharif.model.entity.Role;

public class RoleNameDTO {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleNameDTO() {
	}

	public RoleNameDTO(String name) {
		this.name = name;
	}

	public RoleNameDTO convertToDto(Role role) {
		if(role!=null){
			this.name = role.getName();
			return this;
		}
		return null;
	}

	public Role convertToObject() {
		Role role = new Role();
		role.setName(this.getName());
		role.setFeatures(new HashSet<Feature>());
		return role;
	}
}
