package ir.maktabsharif.api.contact.dto;

import ir.maktabsharif.model.entity.Contact;

public class ContactLiteDTO {
	private int id;
	private String name;
	private String surname;
	private String homeNumber;

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
	public ContactLiteDTO(){
		
	}
	
	public ContactLiteDTO(int id, String name, String surname, String homeNumber) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.homeNumber = homeNumber;
	}

	public ContactLiteDTO convertToDto(Contact contact) {
		if(contact!=null){
			this.id=contact.getId();
			this.name = contact.getName();
			this.surname = contact.getSurname();
			this.homeNumber = contact.getHomeNumber();
			return this;
		}
		return null;
	}

	public Contact convertToObject() {
		Contact contact = new Contact();
		contact.setId(this.getId());
		contact.setName(this.getName());
		contact.setSurname(this.getSurname());
		contact.setHomeNumber(this.getHomeNumber());
		return contact;
	}
}
