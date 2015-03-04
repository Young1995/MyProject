package org.svtcc.online.management.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 定义资源
 * 
 * @author troyyang
 * 
 */

@SuppressWarnings("serial")
@Entity(name = "Resource")
@Table(name = "svtcc_resource")
public class Resource implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "resource_name")
	private String resourceName;

	@Column(name = "resource_uri")
	private String resourceURI;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "svtcc_role_resource", joinColumns = { @JoinColumn(name = "resource_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
