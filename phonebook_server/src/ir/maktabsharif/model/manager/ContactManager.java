package ir.maktabsharif.model.manager;

import java.util.List;

import ir.maktabsharif.model.dao.ContactDAO;
import ir.maktabsharif.model.entity.Contact;

public class ContactManager {
	private static ContactManager contactManagerInstance = new ContactManager();

	private ContactManager() {

	}

	public static ContactManager getInstance() {
		return contactManagerInstance;
	}

	public boolean add(Contact e) throws Exception {
		if (e != null)
			if (e.getName() != null)//checking to enter the name for contact
				if (e.getHomeNumber().matches("[0-9]+") | e.getHomeNumber() == null)//checking to insert just digits for home number
					if (e.getMobile().matches("^09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}$") | e.getMobile() == null)//checking the mobile format
						if (e.getEmail().toUpperCase().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$") | e.getEmail() == null)//checking the email format
							return ContactDAO.getInstance().add(e);
						else
							throw new Exception("email pattern doesn't match!");
					else
						throw new Exception("mobile pattern doesn't match!");
				else 
					throw new Exception("home number pattern doesn't match!");
			else
				throw new Exception("contact name is null!");
		else
			throw new Exception("contact is null!");
	}

	public boolean update(Contact e) throws Exception {
		if (e != null)
			if (e.getHomeNumber().matches("[0-9]+") | e.getHomeNumber() == null)//checking to insert just digits for home number
				if (e.getMobile().matches("^09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}$") | e.getMobile() == null)//checking the mobile format
					if (e.getEmail().toUpperCase().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
							| e.getEmail() == null)//checking the email format
						return ContactDAO.getInstance().update(e);
					else
						throw new Exception("email pattern doesn't match!");
				else
					throw new Exception("mobile pattern doesn't match!");
			else 
				throw new Exception("home number pattern doesn't match!");
		else
			throw new Exception("contact is null!");

	}

	public boolean delete(Contact e) throws Exception {
		if (e != null)
			return ContactDAO.getInstance().delete(e);
		else
			throw new Exception("contact is null!");
	}

	public boolean delete(int id) {
		return ContactDAO.getInstance().delete(ContactDAO.getInstance().getById(id));
	}

	public List<Contact> list() {//get all
		return ContactDAO.getInstance().getAll();
	}

	public Contact get(int id){
		return ContactDAO.getInstance().getById(id);
	}
	
}
