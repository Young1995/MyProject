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
import java.util.Date;
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

	// @NotEmpty(message = "学号/工号不能为空")
	// @Size(min = 6, message = "学号/工号至少为6位")
	@Column(name = "position_number")
	private String positionNo; // 学生学号 或 教师工号

	@Column(name = "gender")
	private boolean gender; // 用户性别

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@OneToMany(mappedBy = "dean", cascade = { CascadeType.PERSIST,
			CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private List<Department> departments; // 用户所属学院

	@Column(name = "department")
	private Integer department;

	// @NotEmpty(message = "专业不能为空")
	@Column(name = "major", nullable = true)
	private String major; // 用户所属专业

	@Column(name = "email")
	private String email; // 用户邮箱

	@Column(name = "user_type")
	private String type; // 用户类型：教师，学生

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "svtcc_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<Role> roles;

	// 用于展示权限，不存到数据库
	@Transient
	private String rolesSample;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "user_description")
	private String description;

	// 添加用户头像
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;
	
	/**
	 * 操作记录的时间和用户
	 */
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "update_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "create_user")
	private String createUser;

	@Column(name = "update_user")
	private String updateUser;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
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

	public String getPositionNo() {
		return positionNo;
	}

	public void setPositionNo(String positionNo) {
		this.positionNo = positionNo;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRolesSample() {
		StringBuffer rolesTemp = new StringBuffer();
		for (Role role : this.getRoles()) {
			rolesTemp.append(role.getRoleName() + " ");
		}
		return rolesTemp.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public void setRolesSample(String rolesSample) {
		this.rolesSample = rolesSample;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
