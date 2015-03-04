package org.svtcc.online.management.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
@Entity(name = "CompanySetting")
@Table(name = "svtcc_company_setting")
public class CompanySetting implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "setting_name")
	private String settingName; // 方案、标准、效果、资源名称

	@Column(name = "description")
	private String description; // 方案、标准、效果、资源描述

	@Column(name = "enabled")
	private boolean enabled; // 是否启用

	@Column(name = "type")
	private int type; // 1. 方案类型 2. 课程标准 3. 教学效果 4. 企业资源

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

	// 合作项目级联
	@OneToMany(mappedBy = "projectType")
	@JsonIgnore
	private List<CoProject> projectType;

	@OneToMany(mappedBy = "coCompany")
	@JsonIgnore
	private List<CoProject> coCompany;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public List<CoProject> getProjectType() {
		return projectType;
	}

	public void setProjectType(List<CoProject> projectType) {
		this.projectType = projectType;
	}

	public List<CoProject> getCoCompany() {
		return coCompany;
	}

	public void setCoCompany(List<CoProject> coCompany) {
		this.coCompany = coCompany;
	}

}
