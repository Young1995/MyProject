package org.svtcc.online.management.domain;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Entity(name = "Department")
@Table(name = "svtcc_department")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class Department implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "department_name")
	private String name;

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.MERGE }, fetch = FetchType.LAZY, optional = true)
	@JoinTable(name = "svtcc_department_user", joinColumns = { @JoinColumn(name = "department_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private User dean;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Major> majorList;

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

	// 校企合作项目
	@OneToMany(mappedBy = "department")
	@JsonIgnore
	private List<CoProject> coProject;

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

	public User getDean() {
		return dean;
	}

	public void setDean(User dean) {
		this.dean = dean;
	}

	public List<Major> getMajorList() {
		return majorList;
	}

	public void setMajorList(List<Major> majorList) {
		this.majorList = majorList;
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

	public List<CoProject> getCoProject() {
		return coProject;
	}

	public void setCoProject(List<CoProject> coProject) {
		this.coProject = coProject;
	}

}
