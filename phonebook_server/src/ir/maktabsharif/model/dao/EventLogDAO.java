package ir.maktabsharif.model.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ir.maktabsharif.model.entity.EventLog;

public class EventLogDAO {
	private static EventLogDAO eventLogDAOInstance = new EventLogDAO();

	private EventLogDAO() {

	}

	public static EventLogDAO getInstance() {
		return eventLogDAOInstance;
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
	public boolean add(EventLog e) {
		Session session = getSession();
		try {
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

	public boolean delete(EventLog e) {
		Session session = getSession();
		try {
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

	public boolean update(EventLog e) {
		return false;
	}

	public List<EventLog> getAll() {
		List<EventLog> events = null;
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from EventLog");
			events = q.list();
			tx.commit();
		} catch (HibernateException e) {

			session.close();
		}
		session.close();
		return events;
	}

}
