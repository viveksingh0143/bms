package com.vamika.bms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vamika.bms.dao.RoleDao;

public abstract class RoleServiceBase implements RoleService {
	@Autowired
	protected RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
}
