package ir.maktabsharif.api.user.dto;

import ir.maktabsharif.model.entity.Role;
import ir.maktabsharif.model.entity.User;

public class UserLoginDTO {
	private String userName;
	private String password;

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

	public UserLoginDTO(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public UserLoginDTO() {
	}

	public UserLoginDTO convertToDto(User user) {
		if(user!=null){
			this.userName = user.getUserName();
			this.password = user.getPassword();
			return this;
		}return null;
	}

	public User convertToObject() {
		User user = new User();
		user.setUserName(this.getUserName());
		user.setPassword(this.getPassword());
		user.setRole(new Role());
		return user;
	}
}
