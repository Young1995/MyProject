package org.svtcc.online.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.svtcc.online.management.dao.RoleDAO;
import org.svtcc.online.management.domain.Role;
import org.svtcc.online.management.service.RoleService;

/**
 * 角色管理service类
 * @author troy
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;
	
	/* (non-Javadoc)
	 * @see org.svtcc.online.management.service.impl.RoleService#addRoles(org.svtcc.online.management.domain.Role)
	 */
	@Override
	public void addRoles(Role role) {
		roleDAO.saveAll(role);
	}
	
	/* (non-Javadoc)
	 * @see org.svtcc.online.management.service.impl.RoleService#findAllRoles()
	 */
	@Override
	public List<Role> findAllRoles() {
		return roleDAO.findAllRoles();
	}
	
	public Role findRoleById(Integer id) {
		return roleDAO.findRoleById(id);
	}

	@Override
	public void updateRole(Role role) {
		roleDAO.updateRole(role);
	}

	@Override
	public Role findRoleByRoleName(String roleName) {
		return roleDAO.findRoleByName(roleName);
	}

	@Override
	public void deleteRole(Integer id) {
		roleDAO.deleteRole(id);
	}
}
