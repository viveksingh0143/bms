package com.vamika.bms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vamika.bms.dao.UserDao;

public abstract class UserServiceBase implements UserService {
	@Autowired
	protected UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
