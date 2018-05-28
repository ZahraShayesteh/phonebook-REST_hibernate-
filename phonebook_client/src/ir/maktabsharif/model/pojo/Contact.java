package ir.maktabsharif.model.pojo;

public class Contact {
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

	public Contact(int id, String name, String surname, String homeNumber, String mobile, String email) {
		this.id = id;
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

	@Override
	public String toString() {
		return "id:" + getId() + ", name:" + getName() + ", surname:" + getSurname() + ", homeNumber:" + getHomeNumber() + ", mobile:"
				+ getMobile() + ", email:" + getEmail();
	}
}
