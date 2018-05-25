package ir.maktabsharif.model.manager;

import java.util.List;

import ir.maktabsharif.model.dao.RoleDAO;
import ir.maktabsharif.model.entity.Role;

public class RoleManager {
	private static RoleManager roleManagerInstance = new RoleManager();

	private RoleManager() {

	}

	public static RoleManager getInstance() {
		return roleManagerInstance;
	}

	public boolean add(Role e) throws Exception {
		if (e != null)
			return RoleDAO.getInstance().add(e);
		else
			throw new Exception("Role is null!");
	}

	public boolean update(Role e) throws Exception {
		if (e != null)
			return RoleDAO.getInstance().update(e);
		else
			throw new Exception("Role is null!");
	}

	public boolean delete(Role e) throws Exception {
		if (e != null)
			return RoleDAO.getInstance().delete(e);
		else
			throw new Exception("Role is null!");
	}

	public List<Role> list() {
		return RoleDAO.getInstance().getAll();
	}

	public Role getByName(String name) {
		return RoleDAO.getInstance().getByName(name);

	}
}
