package org.svtcc.online.management.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Created on 1/15/15.
 */
@SuppressWarnings("serial")
@Entity(name = "Clazz")
@Table(name = "svtcc_class")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class Clazz implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "class_name")
	private String name;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = true)
	@JoinTable(name = "svtcc_major_class", joinColumns = { @JoinColumn(name = "class_id") }, inverseJoinColumns = { @JoinColumn(name = "major_id") })
    @JsonIgnore
    private Major major;

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

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

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

}
