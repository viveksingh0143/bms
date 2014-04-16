package com.vamika.bms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vamika.bms.dao.RoleDao;
import com.vamika.bms.model.Role;
import com.vamika.bms.model.enums.EnableDisableStatus;
import com.vamika.bms.view.FullRole;

@Service
public class RoleServiceImpl extends RoleServiceBase {

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