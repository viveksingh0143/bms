package com.vamika.bms.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.vamika.bms.view.FullPermission;
import com.vamika.bms.view.FullRole;
import com.vamika.bms.view.FullUser;

public interface UserService {
	
	public final static List<String>PERMISSIONS = Arrays.asList("User List", "User Show", "User Show Own", "User Create", "User Update", "User Update Own", "User Delete", "Role List", "Role Show", "Role Create", "Role Update", "Role Delete");

	public List<FullUser> getAllActiveUsers();
	public List<FullUser> getAllUsers();
    public FullUser loadUser(String userName);
    public FullUser saveUser(FullUser user);
    public void updateUser(FullUser user);
	public void deleteUser(String userName);

	public List<FullRole> getAllActiveRoles();
	public List<FullRole> getAllRoles();
    public FullRole loadRole(String name);
    public FullRole saveRole(FullRole role);
    public void updateRole(FullRole role);
	public void deleteRole(String name);
	
	public List<FullPermission> getAllPermissions();
    public FullPermission loadPermission(String name);
    public FullPermission savePermission(FullPermission permission);
    public void saveOrUpdatePermission(FullPermission permission);
	public void deletePermission(String name);
}