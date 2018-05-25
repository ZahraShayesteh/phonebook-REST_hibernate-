package ir.maktabsharif.api.user.dto;

import ir.maktabsharif.api.role.dto.RoleNameDTO;
import ir.maktabsharif.model.entity.User;

public class UserFullDTO {
	private String userName;
	private String password;
	private String role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserFullDTO(String userName,String password, String role) {
		this.userName = userName;
		setPassword(password);
		this.role = role;
	}

	public UserFullDTO() {
	}

	public UserFullDTO convertToDto(User user) {
		if(user!=null){
			this.userName = user.getUserName();
			this.password=user.getPassword();
			this.role = user.getRole().getName();
			return this;
		}return null;
	}

	public User convertToObject() {
		User user = new User();
		user.setUserName(this.getUserName());
		user.setPassword(this.getPassword());
		user.setRole(new RoleNameDTO(this.getRole()).convertToObject());
		return user;
	}
}
