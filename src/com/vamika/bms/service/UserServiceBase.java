package com.vamika.bms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vamika.bms.dao.PermissionDao;
import com.vamika.bms.dao.RoleDao;
import com.vamika.bms.dao.UserDao;

public abstract class UserServiceBase implements UserService, ApplicationStartupService {
	@Autowired
	protected UserDao userDao;
	@Autowired
	protected RoleDao roleDao;
	@Autowired
	protected PermissionDao permissionDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
}
