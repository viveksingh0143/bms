package com.vamika.bms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vamika.bms.dao.RoleDao;
import com.vamika.bms.dao.UserDao;
import com.vamika.bms.model.Role;
import com.vamika.bms.model.User;
import com.vamika.bms.model.enums.EnableDisableStatus;
import com.vamika.bms.model.enums.UsersStatus;
import com.vamika.bms.view.FullRole;
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
		if(fullUser.getRoles_id() != null) {
			List<Role>roles = new ArrayList<Role>();
			for(Integer roleId: fullUser.getRoles_id()) {
				roles.add(getRoleDao().load(roleId));
			}
			user.setRoles(roles);
		}
		getUserDao().save(user);
	}

	@Override
	@Transactional
	public void updateUser(FullUser fullUser) {
		User user = getUserDao().load(fullUser.getId());
		getUserDao().fullVOToEntity(fullUser, user, Boolean.FALSE);
		if(fullUser.getRoles_id() != null) {
			List<Role>roles = new ArrayList<Role>();
			for(Integer roleId: fullUser.getRoles_id()) {
				roles.add(getRoleDao().load(roleId));
			}
			user.setRoles(roles);
		}
		getUserDao().update(user);
	}

	@Override
	@Transactional
	public void deleteUser(String userName) {
		User user = getUserDao().load(userName);
		getUserDao().remove(user);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<FullRole> getAllActiveRoles() {
		return new ArrayList<FullRole>(getRoleDao().findByStatus(RoleDao.TRANSFORM_FULL, EnableDisableStatus.ENABLE));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<FullRole> getAllRoles() {
		return new ArrayList<FullRole>(getRoleDao().loadAll(RoleDao.TRANSFORM_FULL));
	}

	@Override
	@Transactional
	public FullRole loadRole(String name) {
		return (FullRole)getRoleDao().load(RoleDao.TRANSFORM_FULL, name);
	}

	@Override
	@Transactional
	public void saveRole(FullRole fullRole) {
		Role role = getRoleDao().fullVOToEntity(fullRole);
		getRoleDao().save(role);
	}

	@Override
	@Transactional
	public void updateRole(FullRole fullRole) {
		Role role = getRoleDao().load(fullRole.getId());
		getRoleDao().fullVOToEntity(fullRole, role, Boolean.FALSE);
		getRoleDao().update(role);
	}

	@Override
	@Transactional
	public void deleteRole(String name) {
		Role role = getRoleDao().load(name);
		getRoleDao().remove(role);
	}
}