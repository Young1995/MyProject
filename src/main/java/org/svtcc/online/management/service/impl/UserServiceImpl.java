package org.svtcc.online.management.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.svtcc.online.management.constant.RoleConst;
import org.svtcc.online.management.dao.UserDao;
import org.svtcc.online.management.dao.util.PageSupport;
import org.svtcc.online.management.domain.Role;
import org.svtcc.online.management.domain.User;
import org.svtcc.online.management.dto.ChangePasswordDTO;
import org.svtcc.online.management.exception.ChangePasswordException;
import org.svtcc.online.management.service.RoleService;
import org.svtcc.online.management.service.UserService;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleService roleService;

	@Resource
	private ReflectionSaltSource saltSource;

	@Resource
	private ShaPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.getUserByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("Can't find the username.");
		}

		LOG.debug(user.getUsername() + ": " + user.getPassword() + "| "
				+ user.isEnabled());

		return user;
	}

	public boolean registerUser(User user, String roleName) {
		try {
			Role role = roleService.findRoleByRoleName(roleName);
			if (role != null) {
				List<Role> roles = new ArrayList<Role>();
				roles.add(role);
				user.setRoles(roles);
			}

			user.setType(RoleConst.valueOf(roleName).getValue());

			// set the password
			user.setSalt_value(System.currentTimeMillis() + "");
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
					saltSource.getSalt(user)));
			// 默认为false，需要等待审核
			// user.setEnabled(true);

			userDao.saveOrUpdate(user);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * 检查用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean isUserExist(String username) {
		User user = userDao.getUserByUserName(username);

		if (user == null) {
			return true;
		}

		return false;
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public User saveOrupdate(User user) {
		return userDao.saveOrUpdate(user);
	}

	/**
	 * 更新用户密码
	 * 
	 * @param changePasswordDTO
	 * @param user
	 * @return
	 * @throws ChangePasswordException
	 */
	public boolean updatePassword(ChangePasswordDTO changePasswordDTO, User user)
			throws ChangePasswordException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(changePasswordDTO.toString());
		}
		// 检查旧密码是否正确
		String oldPassword = passwordEncoder.encodePassword(
				changePasswordDTO.getOldPassword(), saltSource.getSalt(user));
		if (!oldPassword.equals(user.getPassword())) {
			throw new ChangePasswordException("旧密码不正确");
		}

		// 修改密码
		String newPassword = passwordEncoder.encodePassword(
				changePasswordDTO.getNewPassword(), saltSource.getSalt(user));

		user.setPassword(newPassword);
		return userDao.updateUser(user);
	}

	@Override
	public PageSupport<User> findUsers(int pageNo, int pageSize, String type) {
		return userDao.listUsers(pageNo, pageSize, type);
	}

	@Override
	public boolean saveUser(User user) {
		user.setSalt_value(System.currentTimeMillis() + "");
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
				saltSource.getSalt(user)));
		return userDao.addUser(user);
	}

	@Override
	public User findUserById(Integer id) {
		return userDao.findUserById(id);
	}

	@Override
	public void deleteUserById(Integer id) {
		userDao.deleteById(id);
	}

	@Override
	public PageSupport<User> searchUsers(int pageNo, int pageSize, String search) {
		return userDao.searchUsers(pageNo, pageSize, search);
	}

	@Override
	public PageSupport<User> searchPTTeacher(int pageNo, int pageSize,
			String type) {
		return userDao.listUsers(pageNo, pageSize, type);
	}

	@Override
	public PageSupport<User> searchUserByRole(int pageNo, int pageSize, String roleCode) {
		return userDao.listUsersbyRole(pageNo, pageSize, roleCode);
	}
}
