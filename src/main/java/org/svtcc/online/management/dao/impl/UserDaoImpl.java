package org.svtcc.online.management.dao.impl;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.svtcc.online.management.dao.UserDao;
import org.svtcc.online.management.dao.util.PageSupport;
import org.svtcc.online.management.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    
    @Override
    public User getUserByUserName(String username) {
    	List<User> users = findByProperty("username", username);
    	if(users == null || users.size() < 1) {
    		return null;
    	}
    	
        return users.get(0);
    }

    public boolean addUser(User user) {
        try {
            insert(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public PageSupport<User> listUsers(int pageNo, int pageSize,String type) {
		return findPagination("from User user where user.type=?", pageNo, pageSize,type);
	}



	@Override
	public User findUserById(Integer id) {
		return find(id);
	}

	@Override
	public void deleteUser(Integer id) {
		deleteById(id);
	}


	@Override
	public PageSupport<User> searchUsers(int pageNo, int pageSize, String search) {
		if(StringUtils.isEmpty(search)) {
			return findPagination("from User", pageNo, pageSize);
		}
		return findPagination("from User user where user.name like ? or user.username like ?", pageNo, pageSize, "%" +search + "%", "%" + search + "%");
	}
	
	@Override
	public PageSupport<User> listUsersbyRole(int pageNo, int pageSize, String roleCode) {
		return findPagination("select user from User user inner join user.roles role where role.roleCode = ?", pageNo, pageSize, roleCode);
	}
}
