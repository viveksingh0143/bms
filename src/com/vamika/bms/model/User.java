package com.vamika.bms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vamika.bms.model.enums.UsersStatus;
import com.vamika.bms.model.enums.UsersType;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, columnDefinition = "varchar(255) default 'NORMAL'")
	@Enumerated(EnumType.STRING)
	private UsersType admin = UsersType.NORMAL;

	@Column(nullable = false, columnDefinition = "varchar(255) default 'PENDINGAPPROVAL'")
	@Enumerated(EnumType.STRING)
	private UsersStatus status = UsersStatus.PENDINGAPPROVAL;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "remember_created_at")
	private Date rememberCreatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reset_password_sent_at")
	private Date resetPasswordSentAt;

	@Column(name = "reset_password_token")
	private String resetPasswordToken;

	@Column(name = "signin_failed_count")
	private Integer signinFailedCount;

	// bi-directional many-to-many association to Role
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "users_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "roles_id", nullable = false, updatable = false, insertable = false) })
	private List<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsersType getAdmin() {
		return admin;
	}

	public void setAdmin(UsersType admin) {
		this.admin = admin;
	}

	public UsersStatus getStatus() {
		return status;
	}

	public void setStatus(UsersStatus status) {
		this.status = status;
	}

	public Date getRememberCreatedAt() {
		return rememberCreatedAt;
	}

	public void setRememberCreatedAt(Date rememberCreatedAt) {
		this.rememberCreatedAt = rememberCreatedAt;
	}

	public Date getResetPasswordSentAt() {
		return resetPasswordSentAt;
	}

	public void setResetPasswordSentAt(Date resetPasswordSentAt) {
		this.resetPasswordSentAt = resetPasswordSentAt;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public Integer getSigninFailedCount() {
		return signinFailedCount;
	}

	public void setSigninFailedCount(Integer signinFailedCount) {
		this.signinFailedCount = signinFailedCount;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static final class Factory {
		public static User newInstance() {
			return new User();
		}
	}
}