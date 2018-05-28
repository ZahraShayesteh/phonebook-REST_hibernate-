package ir.maktabsharif.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ir.maktabsharif.model.entity.Contact;

public class ContactDAO {
	private static ContactDAO contactDAOInstance = new ContactDAO();

	private ContactDAO() {

	}

	public static ContactDAO getInstance() {
		return contactDAOInstance;
	}
	
	private static final SessionFactory mySessionFactory;

	static {
		try {
			mySessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession() throws HibernateException {
		return mySessionFactory.openSession();
	}
	
	public boolean add(Contact c) {// inserting a new contact
		Session session = getSession();

		try {
			session.beginTransaction();
			session.save(c);

			session.getTransaction().commit();
		} catch (HibernateException x) {
			session.close();
			return false;
		}
		session.close();
		return true;
	}

	public boolean delete(Contact e) {// deleting a contact
		Session session = getSession();
		try{
			session.beginTransaction();
			session.remove(e);
			session.getTransaction().commit();
		} catch (HibernateException x) {
			session.close();
			return false;
		}
		session.close();
		return true;
	}

	public boolean update(Contact e) {// updating a contact
		Session session = getSession();
		try{
			session.beginTransaction();
			session.update(e);
			session.getTransaction().commit();
		} catch (HibernateException x) {
			session.close();
			return false;
		}
		session.close();
		return true;
	}

	public Contact getById(int id) {// get contact by its ID
		Contact contact = null;
		Session session = getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			contact = session.get(Contact.class, id);
			tx.commit();
		} catch (HibernateException x) {
			session.close();
			return null;
		}
		session.close();
		return contact;
	}

	public List<Contact> getAll() {// get the list of all contacts
		List<Contact> contacts = null;
		Session session = getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query q = session.createQuery("from Contact");
			contacts = q.list();
			tx.commit();
		} catch (HibernateException x) {
			session.close();
		}
		session.close();
		return contacts;
	}

}
