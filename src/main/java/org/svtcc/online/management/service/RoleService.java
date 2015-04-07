package org.svtcc.online.management.service;

import java.util.List;

import org.svtcc.online.management.domain.Role;

public interface RoleService {

	/**
	 * 添加role
	 * @param role
	 */
	public abstract void addRoles(Role role);

	/**
	 * 查询所有的role
	 * @return
	 */
	public abstract List<Role> findAllRoles();
	
	/**
	 * 根据id查询role
	 * @param id
	 * @return
	 */
	public Role findRoleById(Integer id);
	
	/**
	 * 更新角色
	 * @param role
	 */
	public void updateRole(Role role);
	
	public Role findRoleByRoleName(String roleName);
	
	/**
	 * TODO: 删除的时候级联操作，同时删掉user
	 * @param id
	 */
	public void deleteRole(Integer id);

}