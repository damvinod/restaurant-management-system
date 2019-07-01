
package com.media.restaurant.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="USERS")
@SequenceGenerator(
		  name = "USERS_SEQ_GENERATOR",
		  sequenceName = "USERS_SEQ",
		  initialValue = 1, allocationSize = 1)
public class Users {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ_GENERATOR")
	private Long userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="active_flag")
	private String activeFlag;
	
	@Column(name="CREATED_TS")
	private Timestamp createdTs;
	
	@Column(name="MODIFIED_TS")
	private Timestamp modifiedTs;

	public String getActiveFlag() {
		return activeFlag;
	}

	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public Timestamp getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(Timestamp modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", role=" + role + ", activeFlag="
				+ activeFlag + "]";
	}
	
}
