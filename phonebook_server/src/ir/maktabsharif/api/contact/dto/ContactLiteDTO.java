package ir.maktabsharif.api.contact.dto;

import ir.maktabsharif.model.entity.Contact;

public class ContactLiteDTO {
	private String name;
	private String surname;
	private String homeNumber;

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

	public ContactLiteDTO() {

	}

	public ContactLiteDTO(String name, String surname, String homeNumber) {
		this.name = name;
		this.surname = surname;
		this.homeNumber = homeNumber;
	}

	public ContactLiteDTO convertToDto(Contact contact) {
		if(contact!=null){
			this.name = contact.getName();
			this.surname = contact.getSurname();
			this.homeNumber = contact.getHomeNumber();
			return this;
		}
		return null;
	}

	public Contact convertToObject() {
		Contact contact = new Contact();
		contact.setName(this.getName());
		contact.setSurname(this.getSurname());
		contact.setHomeNumber(this.getHomeNumber());
		return contact;
	}
}
