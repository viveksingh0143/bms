package com.vamika.bms.service;

import java.util.List;

import com.vamika.bms.view.FullRole;

public interface RoleService {
	public List<FullRole> getAllActiveRoles();
	public List<FullRole> getAllRoles();
    public FullRole loadRole(String name);
    public void saveRole(FullRole role);
    public void updateRole(FullRole role);
	public void deleteRole(String name);
}