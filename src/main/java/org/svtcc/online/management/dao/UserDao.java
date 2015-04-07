package org.svtcc.online.management.dao;


import org.svtcc.online.management.dao.util.PageSupport;
import org.svtcc.online.management.domain.User;

public interface UserDao extends BaseDAO<User> {

	public abstract User getUserByUserName(String username);

	public boolean addUser(User user);

	public boolean updateUser(User user);
	
	public PageSupport<User> listUsers(int pageNo, int pageSize,String type);
	
	public User findUserById(Integer id);
	
	public void deleteUser(Integer id);
	
	public PageSupport<User> searchUsers(int pageNo, int pageSize, String search);
	
	public PageSupport<User> listUsersbyRole(int pageNo, int pageSize, String roleCode);

}