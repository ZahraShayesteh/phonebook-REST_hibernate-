package ir.maktabsharif.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String homeNumber;
	@Column
	private String mobile;
	@Column
	private String email;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Contact(int iD, String name, String surname, String homeNumber, String mobile, String email) {
		ID = iD;
		this.name = name;
		this.surname = surname;
		this.homeNumber = homeNumber;
		this.mobile = mobile;
		this.email = email;
	}

	public Contact(String name, String surname, String homeNumber, String mobile, String email) {
		this.name = name;
		this.surname = surname;
		this.homeNumber = homeNumber;
		this.mobile = mobile;
		this.email = email;
	}

	public Contact() {
	}

}
