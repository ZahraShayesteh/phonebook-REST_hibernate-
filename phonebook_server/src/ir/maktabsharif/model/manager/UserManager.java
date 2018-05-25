package ir.maktabsharif.model.manager;

import java.util.List;

import ir.maktabsharif.api.role.dto.RoleFullDTO;
import ir.maktabsharif.model.dao.UserDAO;
import ir.maktabsharif.model.entity.User;

public class UserManager {
	private static UserManager userManagerInstance=new UserManager();

	public static UserManager getInstance(){
		return userManagerInstance;
	}

	private UserManager(){

	}

	public boolean add(User e) throws Exception {//only simple users will add to the system
		if(e!=null)
			if(e.getUserName() != null)
				if(e.getPassword() != null){
					e.setRole(RoleFullDTO.simpleUserRole());
					if (e.getUserName().equals("guest") || e.getUserName().equals("Guest")) {//if the username is same as fixed guest role its not allowed
						return false;
					}

					if (e.getUserName().equals("mainAdmin") || e.getUserName().equals("mainAdmin")) {//if the username is same as fixed main admin role its not allowed
						return false;
					}
					return UserDAO.getInstance().add(e);
				}
				else
					throw new Exception("password is null!");
			else
				throw new Exception("username is null!");
		else
			throw new Exception("user is null!");
	}

	public User signIn(User e) throws Exception{
		if(e!=null)
			return UserDAO.getInstance().signIn(e);
		else
			throw new Exception("user is null!");
	}

	public boolean update(User e) throws Exception {
		if(e !=null){
			if (e.getRole().equals(RoleFullDTO.mainAdminRole()) && !e.getUserName().equals("mainAdmin")) {//admin role is fixed in system and can not update
				return false;
			}
			if (e.getRole().equals(RoleFullDTO.guestRole()) && !e.getUserName().equals("guest")) {//guest role is fixed in system and can not update
				return false;
			}
			if (!e.getRole().equals(RoleFullDTO.guestRole()) && e.getUserName().equals("guest")) {//guest role is fixed in system and can not update
				return false;
			}
			if (!e.getRole().equals(RoleFullDTO.mainAdminRole()) && e.getUserName().equals("mainAdmin")) {//admin role is fixed in system and can not update
				return false;
			}

			if(e.getRole()==null){//if role of the user object is null
				e.setRole(this.getByUserName(e.getUserName()).getRole());
			}
			if(UserDAO.getInstance().update(e)){//if updating was successful
				return true;
			}
			else{
				return false;
			}
		}else
			throw new Exception("user is null!");
	}

	public boolean delete(User e) throws Exception {
		if(e!=null)
			return UserDAO.getInstance().delete(e);
		else
			throw new Exception("user is null!");
	}

	public List<User> list() {
		return UserDAO.getInstance().getAll();
	}

	public User getByUserName(String userName) {
		return UserDAO.getInstance().getByUserName(userName);
	}
}
