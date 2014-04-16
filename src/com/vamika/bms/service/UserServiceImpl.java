package com.vamika.bms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vamika.bms.dao.UserDao;
import com.vamika.bms.model.User;
import com.vamika.bms.model.enums.UsersStatus;
import com.vamika.bms.view.FullUser;

@Service
public class UserServiceImpl extends UserServiceBase {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<FullUser> getAllActiveUsers() {
		return new ArrayList<FullUser>(getUserDao().findByStatus(UserDao.TRANSFORM_FULL, UsersStatus.ENABLE));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<FullUser> getAllUsers() {
		return new ArrayList<FullUser>(getUserDao().loadAll(UserDao.TRANSFORM_FULL));
	}

	@Override
	@Transactional
	public FullUser loadUser(String userName) {
		return (FullUser)getUserDao().load(UserDao.TRANSFORM_FULL, userName);
	}

	@Override
	@Transactional
	public void saveUser(FullUser fullUser) {
		User user = getUserDao().fullVOToEntity(fullUser);
		getUserDao().save(user);
	}

	@Override
	@Transactional
	public void updateUser(FullUser fullUser) {
		User user = getUserDao().load(fullUser.getId());
		getUserDao().fullVOToEntity(fullUser, user, Boolean.FALSE);
		getUserDao().update(user);
	}

	@Override
	@Transactional
	public void deleteUser(String userName) {
		User user = getUserDao().load(userName);
		getUserDao().remove(user);
	}
}