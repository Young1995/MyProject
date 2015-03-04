package org.svtcc.online.management.dao;

import org.svtcc.online.management.domain.Role;

import java.util.List;

public interface RoleDAO {

    List<Role> findAllRoles();

    void saveAll(Role... roles);
    
    Role findRoleById(Integer id);
    
    void updateRole(Role role);
    
    Role findRoleByName(String name);
    
    void deleteRole(Integer id);
}