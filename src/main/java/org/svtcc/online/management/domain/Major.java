package org.svtcc.online.management.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created on 1/11/15.
 */
@SuppressWarnings("serial")
@Entity(name = "Major")
@Table(name = "svtcc_major")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class Major implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "major_name")
	private String name;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = true)
	@JoinTable(name = "svtcc_major_department", joinColumns = { @JoinColumn(name = "major_id") }, inverseJoinColumns = { @JoinColumn(name = "department_id") })
	@JsonIgnore
	private Department department;

	@OneToMany(mappedBy = "major", cascade = { CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Clazz> clazzList;

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

	@OneToMany(mappedBy = "major")
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Clazz> getClazzList() {
		return clazzList;
	}

	public void setClazzList(List<Clazz> clazzList) {
		this.clazzList = clazzList;
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
