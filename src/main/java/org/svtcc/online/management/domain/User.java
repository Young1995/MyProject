package org.svtcc.online.management.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
@Entity(name = "User")
@Table(name = "svtcc_userinfo")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 6, message = "用户名至少为6位字母、数字、下划线等")
    @Column(name = "username")
    private String username; // 用户名

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, message = "密码至少为6位字母、数字、下划线等")
    @Column(name = "password")
    private String password; // 密码

    @Column(name = "enabled")
    private boolean enabled = false; // 该用户是否可用

    @Column(name = "salt_value")
    private String salt_value; // 密码盐值

    // 学生，教师信息
    @Transient
    private String cpassword; // 重复密码，不存入数据库

    @NotEmpty(message = "姓名不能为空")
    @Column(name = "name")
    private String name; // 学生姓名 或者 教师姓名

    @Column(name = "email")
    private String email; // 用户邮箱

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "svtcc_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        for (Role role : getRoles()) {
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt_value() {
        return salt_value;
    }

    public void setSalt_value(String salt_value) {
        this.salt_value = salt_value;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRolesSample() {
        StringBuffer rolesTemp = new StringBuffer();
        for (Role role : this.getRoles()) {
            rolesTemp.append(role.getRoleName() + " ");
        }
        return rolesTemp.toString();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
