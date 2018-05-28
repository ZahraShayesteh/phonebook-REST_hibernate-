package ir.maktabsharif.api.contact.dto;

import ir.maktabsharif.model.entity.Contact;

public class ContactFullDTO {
	private int id;
	private String name;
	private String surname;
	private String homeNumber;
	private String mobile;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ContactFullDTO() {

	}

	public ContactFullDTO(int iD, String name, String surname, String homeNumber, String mobile, String email) {
		id = iD;
		this.name = name;
		this.surname = surname;
		this.homeNumber = homeNumber;
		this.mobile = mobile;
		this.email = email;
	}

	public ContactFullDTO convertToDto(Contact contact) {
		if(contact!=null){
			this.id = contact.getId();
			this.name = contact.getName();
			this.surname = contact.getSurname();
			this.homeNumber = contact.getHomeNumber();
			this.mobile = contact.getMobile();
			this.email = contact.getEmail();
			return this;
		}return null;
	}

	public Contact convertToObject() {
		Contact contact = new Contact();
		contact.setId(this.getId());
		contact.setName(this.getName());
		contact.setSurname(this.getSurname());
		contact.setHomeNumber(this.getHomeNumber());
		contact.setMobile(this.getMobile());
		contact.setEmail(this.getEmail());
		return contact;
	}
}
