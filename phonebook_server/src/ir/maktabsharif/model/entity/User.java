package ir.maktabsharif.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class User {
	@Id
	private String userName;
	@Column
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Role role;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(String userName, String password, Role role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public User() {
	}
}
