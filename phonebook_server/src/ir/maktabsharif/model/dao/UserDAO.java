package ir.maktabsharif.model.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ir.maktabsharif.model.entity.User;

public class UserDAO {
	private static UserDAO userDAOInstance = new UserDAO();

	private UserDAO() {
	}

	public static UserDAO getInstance() {
		return userDAOInstance;
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

	public boolean add(User e) {//inserting a new user
		initializer();//if fixed roles or users don't exist it will create them
		Session session = getSession();
		try{
			session.beginTransaction();
			session.save(e);
			session.getTransaction().commit();
		} catch (HibernateException x) {
			session.close();
			return false;
		}
		session.close();
		return true;
	}

	public User signIn(User e) {

		initializer();//if fixed roles or users don't exist it will create them
		Session session = getSession();
		User user=null;
		try{
			session.beginTransaction();
			Query q = session.createQuery("from User where userName=:userName and password=:password");
			q.setParameter("userName", e.getUserName());
			q.setParameter("password", e.getPassword());
			user = (User) q.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException x) {
			session.close();
			return null;
		}
		session.close();
		return user;
	}

	public boolean delete(User e) {//deleting a user from DB

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
		initializer();//if this user was the last user of the system initializer will initial a new one
		return true;
	}

	public boolean update(User e) {//updating a user using its object
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
		initializer();//if admin change the role of the last guest of the system,it will initial new user
		return true;
	}

	public User getByUserName(String userName) {//getting a user object from DB by its username
		User user = null;
		Session session = getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			user = session.get(User.class, userName);
			tx.commit();
		} catch (HibernateException x) {
			session.close();
			return null;
		}
		session.close();
		return user;
	}

	public List<User> getAll() {//getting all the users from DB and return list of them
		List<User> users = null;
		Session session = getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query q = session.createQuery("from User");
			users = q.list();
			tx.commit();
		} catch (HibernateException x) {
			session.close();
			x.printStackTrace();
		}
		session.close();
		return users;
	}

	public void initializer() {//changing the users may affect on the fixed roles and users of the system,so by this method they will initial again

		RoleDAO.getInstance().initializer();
	}
}
