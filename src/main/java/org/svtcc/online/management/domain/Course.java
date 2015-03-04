package org.svtcc.online.management.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created on 1/18/15.
 */
@SuppressWarnings("serial")
@Entity(name = "Course")
@Table(name = "svtcc_course")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class Course implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "supplement")
	private String supplement;

	@Column(name = "enabled")
	private Integer enabled = 0; // 该课程是否可用 0.停用 1.启用 2.审核中 3.未通过

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "svtcc_major_courses", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = { @JoinColumn(name = "major_id") })
	private Major major;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "svtcc_course_experts", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = { @JoinColumn(name = "expert_id") })
	private List<Expert> companyExpertList;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "svtcc_course_experts", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = { @JoinColumn(name = "expert_id") })
	private List<Expert> schoolExpertList;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "svtcc_course_course_standard", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = { @JoinColumn(name = "course_standard_id") })
	private List<CompanySetting> courseStandardList;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "svtcc_course_teaching_effect", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = { @JoinColumn(name = "teaching_effect_id") })
	private List<CompanySetting> teachingEffectList;

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

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public List<Expert> getCompanyExpertList() {
		return companyExpertList;
	}

	public void setCompanyExpertList(List<Expert> companyExpertList) {
		this.companyExpertList = companyExpertList;
	}

	public List<Expert> getSchoolExpertList() {
		return schoolExpertList;
	}

	public void setSchoolExpertList(List<Expert> schoolExpertList) {
		this.schoolExpertList = schoolExpertList;
	}

	public List<CompanySetting> getCourseStandardList() {
		return courseStandardList;
	}

	public void setCourseStandardList(List<CompanySetting> courseStandardList) {
		this.courseStandardList = courseStandardList;
	}

	public List<CompanySetting> getTeachingEffectList() {
		return teachingEffectList;
	}

	public void setTeachingEffectList(List<CompanySetting> teachingEffectList) {
		this.teachingEffectList = teachingEffectList;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getSupplement() {
		return supplement;
	}

	public void setSupplement(String supplement) {
		this.supplement = supplement;
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
