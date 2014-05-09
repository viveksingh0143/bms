package com.vamika.bms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vamika.bms.dao.PermissionDao;
import com.vamika.bms.dao.RoleDao;
import com.vamika.bms.dao.UserDao;
import com.vamika.bms.model.Permission;
import com.vamika.bms.model.Role;
import com.vamika.bms.model.User;
import com.vamika.bms.model.enums.EnableDisableStatus;
import com.vamika.bms.model.enums.UsersStatus;
import com.vamika.bms.model.enums.UsersType;
import com.vamika.bms.view.FullPermission;
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
	public FullUser saveUser(FullUser fullUser) {
		User user = getUserDao().fullVOToEntity(fullUser);
		if(fullUser.getRoles_id() != null) {
			List<Role>roles = new ArrayList<Role>();
			for(Integer roleId: fullUser.getRoles_id()) {
				roles.add(getRoleDao().load(roleId));
			}
			user.setRoles(roles);
		}
		return (FullUser) getUserDao().save(UserDao.TRANSFORM_FULL, user);
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
	public FullRole saveRole(FullRole fullRole) {
		Role role = getRoleDao().fullVOToEntity(fullRole);
		if(fullRole.getPermissions_id() != null) {
			List<Permission>permissions = new ArrayList<Permission>();
			for(Integer permissionId: fullRole.getPermissions_id()) {
				permissions.add(getPermissionDao().load(permissionId));
			}
			role.setPermissions(permissions);
		}
		return (FullRole) getRoleDao().save(RoleDao.TRANSFORM_FULL, role);
	}

	@Override
	@Transactional
	public void updateRole(FullRole fullRole) {
		Role role = getRoleDao().load(fullRole.getId());
		getRoleDao().fullVOToEntity(fullRole, role, Boolean.FALSE);
		if(fullRole.getPermissions_id() != null) {
			List<Permission>permissions = new ArrayList<Permission>();
			for(Integer permissionId: fullRole.getPermissions_id()) {
				permissions.add(getPermissionDao().load(permissionId));
			}
			role.setPermissions(permissions);
		}
		getRoleDao().update(role);
	}

	@Override
	@Transactional
	public void deleteRole(String name) {
		Role role = getRoleDao().load(name);
		getRoleDao().remove(role);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<FullPermission> getAllPermissions() {
		return new ArrayList<FullPermission>(getPermissionDao().loadAll(PermissionDao.TRANSFORM_FULL));
	}

	@Override
	@Transactional
	public FullPermission loadPermission(String name) {
		return (FullPermission)getPermissionDao().load(PermissionDao.TRANSFORM_FULL, name);
	}

	@Override
	@Transactional
	public FullPermission savePermission(FullPermission fullPermission) {
		Permission permission = getPermissionDao().fullVOToEntity(fullPermission);
		return (FullPermission) getPermissionDao().save(PermissionDao.TRANSFORM_FULL, permission);
	}
	
	@Override
	@Transactional
	public void saveOrUpdatePermission(FullPermission fullPermission) {
		Permission permission = getPermissionDao().fullVOToEntity(fullPermission);
		getPermissionDao().saveOrUpdate(permission);
	}
	
	
	
	@Override
	@Transactional
	public void deletePermission(String name) {
		Permission permission = getPermissionDao().load(name);
		getPermissionDao().remove(permission);
	}

	@Override
	@Transactional
	public void initDatabase() {
		FullUser admin = (FullUser)getUserDao().load(UserDao.TRANSFORM_FULL, "admin");
		if(admin == null) {
			List<FullPermission> permissions = (ArrayList)getPermissionDao().loadAll(getPermissionDao().TRANSFORM_FULL);
			
			FullRole adminrole = new FullRole();
			adminrole.setName("Site Administrator");
			adminrole.setPermissions_id(new ArrayList<Integer>());
			for(FullPermission fullPermission: permissions) {
				adminrole.getPermissions_id().add(fullPermission.getId());
			}
			adminrole.setStatus(EnableDisableStatus.ENABLE);
			adminrole = this.saveRole(adminrole);
			
			FullRole editorrole = new FullRole();
			editorrole.setName("Editor");
			editorrole.setPermissions_id(new ArrayList<Integer>());
			for(int i = 0; i < permissions.size()/2; i++) {
				FullPermission fullPermission = permissions.get(i);
				editorrole.getPermissions_id().add(fullPermission.getId());
			}
			editorrole.setStatus(EnableDisableStatus.ENABLE);
			editorrole = this.saveRole(editorrole);
			
			FullRole editoradminrole = new FullRole();
			editoradminrole.setName("Editor Admin");
			editoradminrole.setPermissions_id(new ArrayList<Integer>());
			for(int i = 0; i < permissions.size()/2; i++) {
				FullPermission fullPermission = permissions.get(i);
				editoradminrole.getPermissions_id().add(fullPermission.getId());
			}
			editoradminrole.setStatus(EnableDisableStatus.ENABLE);
			editoradminrole = this.saveRole(editoradminrole);
			
			
			admin = new FullUser();
			admin.setUsername("admin");
			admin.setEmail("viveksingh0143@gmail.com");
			admin.setName("Administrator");
			admin.setPassword("p@ssw0rd");
			admin.setConfirmPassword("p@ssw0rd");
			admin.setAdmin(UsersType.ADMIN);
			admin.setStatus(UsersStatus.ENABLE);
			this.saveUser(admin);
			
			admin = new FullUser();
			admin.setUsername("editor1");
			admin.setEmail("editor1@gmail.com");
			admin.setName("Editor 1");
			admin.setPassword("p@ssw0rd");
			admin.setConfirmPassword("p@ssw0rd");
			admin.setAdmin(UsersType.NORMAL);
			admin.setStatus(UsersStatus.ENABLE);
			admin.setRoles_id(Arrays.asList(editorrole.getId()));
			this.saveUser(admin);
			
			admin = new FullUser();
			admin.setUsername("editor2");
			admin.setEmail("editor2@gmail.com");
			admin.setName("Editor 2");
			admin.setPassword("p@ssw0rd");
			admin.setConfirmPassword("p@ssw0rd");
			admin.setAdmin(UsersType.NORMAL);
			admin.setStatus(UsersStatus.ENABLE);
			admin.setRoles_id(Arrays.asList(editorrole.getId(), editoradminrole.getId()));
			this.saveUser(admin);
			
			admin = new FullUser();
			admin.setUsername("siteadministrator");
			admin.setEmail("siteadministrator@gmail.com");
			admin.setName("Site Administrator");
			admin.setPassword("p@ssw0rd");
			admin.setConfirmPassword("p@ssw0rd");
			admin.setAdmin(UsersType.NORMAL);
			admin.setStatus(UsersStatus.ENABLE);
			admin.setRoles_id(Arrays.asList(adminrole.getId()));
			this.saveUser(admin);
		}
	}
}