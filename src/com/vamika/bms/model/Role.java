package com.vamika.bms.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.vamika.bms.model.enums.EnableDisableStatus;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(nullable=false, unique=true)
	private String name;
	
	@Column(nullable = false, columnDefinition = "varchar(255) default 'ENABLE'")
	@Enumerated(EnumType.STRING)
	private EnableDisableStatus status = EnableDisableStatus.ENABLE;

	//bi-directional many-to-many association to Permission
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
		name="roles_permissions"
		, joinColumns={
			@JoinColumn(name="roles_id", nullable = false, updatable = false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="permissions_id", nullable = false, updatable = false)
			}
		)
	private List<Permission> permissions;

	//bi-directional many-to-many association to User
//	@ManyToMany(mappedBy="roles", cascade=CascadeType.ALL)
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "roles_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "users_id", nullable = false, updatable = false, insertable = false) })
	private List<User> users;

	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public EnableDisableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableDisableStatus status) {
		this.status = status;
	}
	
	public static final class Factory {
		public static Role newInstance() {
			return new Role();
		}
	}
}