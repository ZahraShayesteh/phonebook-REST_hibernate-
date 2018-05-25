package ir.maktabsharif.model.manager;

import java.util.List;

import ir.maktabsharif.model.dao.EventLogDAO;
import ir.maktabsharif.model.entity.EventLog;

public class EventLogManager {
	private static EventLogManager eventManagerInstance = new EventLogManager();

	private EventLogManager() {

	}

	public static EventLogManager getInstance() {
		return eventManagerInstance;
	}

	public boolean add(EventLog e) throws Exception {
		if (e != null)
			return EventLogDAO.getInstance().add(e);
		else
			throw new Exception("eventLog is null!");
	}

	public boolean update(EventLog e) throws Exception {
		if (e != null)
			return EventLogDAO.getInstance().update(e);
		else
			throw new Exception("eventLog is null!");
	}

	public boolean delete(EventLog e) throws Exception {
		if (e != null)
			return EventLogDAO.getInstance().delete(e);
		else
			throw new Exception("eventLog is null!");
	}

	public List<EventLog> list() {
		return EventLogDAO.getInstance().getAll();
	}
}
