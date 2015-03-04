package org.svtcc.online.management.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.svtcc.online.management.dao.util.PageSupport;
import org.svtcc.online.management.domain.User;
import org.svtcc.online.management.dto.ChangePasswordDTO;
import org.svtcc.online.management.exception.ChangePasswordException;

public interface UserService extends UserDetailsService {
    /**
     * 注册用户
     *
     * @param user
     * @param roleName
     * @return
     */
    public boolean registerUser(User user, String roleName);

    public boolean isUserExist(String username);

    public User update(User user);

    public User saveOrupdate(User user);
    
    public boolean updatePassword(ChangePasswordDTO changePasswordDTO, User user) throws ChangePasswordException;

    public PageSupport<User> findUsers(int pageNo, int pageSize,String type);
    
    public boolean saveUser(User user);
    
    public User findUserById(Integer id);
    
    public void deleteUserById(Integer id);
    
    public PageSupport<User> searchUsers(int pageNo, int pageSize, String search);
    
    public PageSupport<User> searchPTTeacher(int pageNo, int pageSize, String type);
    
    public PageSupport<User> searchUserByRole(int pageNo, int pageSize, String roleCode);
}
