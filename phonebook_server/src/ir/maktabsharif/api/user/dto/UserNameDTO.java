package ir.maktabsharif.api.user.dto;

import ir.maktabsharif.model.entity.User;

public class UserNameDTO {
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserNameDTO(String userName) {
		this.userName = userName;
	}

	public UserNameDTO() {
	}

	public UserNameDTO convertToDto(User user) {
		if(user!=null){
			this.userName = user.getUserName();
			return this;
		}return null;
	}

	public User convertToObject() {
		User user = new User();
		user.setUserName(this.getUserName());
		return user;
	}
}
