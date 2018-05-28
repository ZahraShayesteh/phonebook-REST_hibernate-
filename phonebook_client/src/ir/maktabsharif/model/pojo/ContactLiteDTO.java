package ir.maktabsharif.model.pojo;

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


	@Override
	public String toString() {
		return "id:" + getId() + ", name:" + getName() + ", surname:" + getSurname() + ", homeNumber:" + getHomeNumber();
	}
}
