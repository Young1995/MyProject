package org.svtcc.online.management.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 合作项目
 * 
 * @author troy
 * 
 */
@SuppressWarnings("serial")
@Entity(name = "CoProject")
@Table(name = "svtcc_co_project")
public class CoProject implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name")
	private String name; // 项目名称

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "major_id")
	private Major major;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_type_id")
	private CompanySetting projectType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "co_company_id")
	private CompanySetting coCompany;

	@Column(name = "details")
	private String details;

	@Column(name = "status")
	private int status;

	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "terminal_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date terminalDate;

	@Column(name = "auto_sign")
	private boolean autoSign;

	// 评审标准
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

	// 接收参数设置
	@Transient
	private Integer depId;
	@Transient
	private Integer majorId;
	@Transient
	private Integer projectTypeId;
	@Transient
	private Integer coCompanyId;
	@Transient
	private String formatStartDate;
	@Transient
	private String formatTerminalDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public CompanySetting getProjectType() {
		return projectType;
	}

	public void setProjectType(CompanySetting projectType) {
		this.projectType = projectType;
	}

	public CompanySetting getCoCompany() {
		return coCompany;
	}

	public void setCoCompany(CompanySetting coCompany) {
		this.coCompany = coCompany;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isAutoSign() {
		return autoSign;
	}

	public void setAutoSign(boolean autoSign) {
		this.autoSign = autoSign;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getTerminalDate() {
		return terminalDate;
	}

	public void setTerminalDate(Date terminalDate) {
		this.terminalDate = terminalDate;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public Integer getCoCompanyId() {
		return coCompanyId;
	}

	public void setCoCompanyId(Integer coCompanyId) {
		this.coCompanyId = coCompanyId;
	}

	public String getFormatStartDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (getStartDate() != null) {
			return dateFormat.format(getStartDate());
		}
		return "";
	}

	public String getFormatTerminalDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (getTerminalDate() != null) {
			return dateFormat.format(getTerminalDate());
		}
		
		return "";
	}

}
