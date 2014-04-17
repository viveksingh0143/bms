package com.vamika.bms.dao;

import java.util.Collection;

import com.vamika.bms.model.Permission;
import com.vamika.bms.view.FullPermission;

public interface PermissionDao {
	public final static int TRANSFORM_NONE = 0;
	public final static int TRANSFORM_FULL = 2;
	
	public Collection loadAll();
	public Collection loadAll(final int transform);
	
	public Permission load(Integer id);
	public Object load(int transform, Integer id);
	public Permission load(String name);
	public Object load(int transform, String name);
	
	public Permission saveOrUpdate(Permission permission);
	public Object saveOrUpdate(int transform, Permission permission);
	
	public Permission save(Permission permission);
	public Object save(int transform, Permission permission);
	public Collection save(Collection entities);
	public Collection save(int transform, Collection entities);

	public void remove(Permission permission);
	public void remove(Integer id);
	public void remove(Collection entities);
	
	public void toFullVO(Permission permission, FullPermission target);
	public FullPermission toFullVO(Permission entity);
	public void toFullVOCollection(Collection entities);
	public void fullVOToEntity(FullPermission permission, Permission target, boolean copyIfNull);
	public Permission fullVOToEntity(FullPermission permission);
	public void fullVOToEntityCollection(Collection instances);
}
