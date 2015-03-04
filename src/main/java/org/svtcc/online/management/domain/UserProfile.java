package org.svtcc.online.management.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 用户头像
 * 
 * @author troyyang
 * 
 */
@SuppressWarnings("serial")
@Entity(name = "UserProfile")
@Table(name = "svtcc_user_profile")
public class UserProfile implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Column(name = "store_folder")
	private String storeFolder; // 保存文件的文件夹 [用于页面显示]

	@Column(name = "origin_name")
	private String originName; // 文件原始名字

	@Column(name = "real_name")
	private String realName; // 文件存放在文件夹中的真实名字 [用于页面显示]

	@Column(name = "file_type")
	private String type; // 文件类型

	@Column(name = "file_size")
	private Long size; // 文件大小

	@OneToOne(mappedBy = "userProfile")
	private User user; // 关联用户

	@Transient
	private String storePath; // 拼装显示图片地址
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStoreFolder() {
		return storeFolder;
	}

	public void setStoreFolder(String storeFolder) {
		this.storeFolder = storeFolder;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStorePath() {
		return this.storeFolder + "/" + this.realName;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
	

}
