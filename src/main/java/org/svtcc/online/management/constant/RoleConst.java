package org.svtcc.online.management.constant;

public enum RoleConst {
	ROLE_STUDENT("ROLE_STUDENT", "学生"),
	ROLE_TEACHER("ROLE_TEACHER", "教师");
	
	private String name;
	private String value;
	
	
	private RoleConst(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getValue() {
		return this.value;
	}
}
