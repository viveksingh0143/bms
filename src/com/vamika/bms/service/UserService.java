package com.vamika.bms.service;

import java.util.List;

import com.vamika.bms.model.User;
import com.vamika.bms.view.FullRole;
import com.vamika.bms.view.FullUser;

public interface UserService {
	public List<FullUser> getAllActiveUsers();
	public List<FullUser> getAllUsers();
    public FullUser loadUser(String userName);
    public void saveUser(FullUser user);
    public void updateUser(FullUser user);
	public void deleteUser(String userName);

	public List<FullRole> getAllActiveRoles();
	public List<FullRole> getAllRoles();
    public FullRole loadRole(String name);
    public void saveRole(FullRole role);
    public void updateRole(FullRole role);
	public void deleteRole(String name);
}