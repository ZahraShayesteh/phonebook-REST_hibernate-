package ir.maktabsharif.api.user.dto;

import ir.maktabsharif.api.role.dto.RoleNameDTO;
import ir.maktabsharif.model.entity.User;
public class UserLiteDTO {
	private String userName;
	private String role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserLiteDTO(String userName, String role) {
		this.userName = userName;
		this.role = role;
	}

	public UserLiteDTO() {
	}

	public UserLiteDTO convertToDto(User user) {
		if(user!=null){
			this.userName = user.getUserName();
			this.role = user.getRole().getName();
			return this;
		}return null;
	}

	public User convertToObject() {
		User user = new User();
		user.setUserName(this.getUserName());
		user.setRole(new RoleNameDTO(this.getRole()).convertToObject());
		return user;
	}
}
