package org.svtcc.online.management.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 管理员添加用户时所需要的DTO
 * 
 * @author troy
 *
 */
public class RegisterUserDTO {
	private int id;

	@NotNull(message = "用户名不能为空")
	@Size(min = 6, message = "用户名至少为6位")
	private String username;

	@NotNull(message = "姓名不能为空")
	private String name;

	@NotNull(message = "密码不能为空")
	@Size(min = 6, message = "密码至少为6位")
	private String password;

	private String cpassword;

	private String email;

	private boolean gender;

	private String phoneNumber;

	private int department;

	private List<Integer> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
}
