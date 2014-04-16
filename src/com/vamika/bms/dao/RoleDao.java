package com.vamika.bms.dao;

import java.util.Collection;

import com.vamika.bms.model.Role;
import com.vamika.bms.model.enums.EnableDisableStatus;
import com.vamika.bms.view.FullRole;

public interface RoleDao {
	public final static int TRANSFORM_NONE = 0;
	public final static int TRANSFORM_FULL = 2;
	
	public Collection findByStatus(EnableDisableStatus status);
	public Collection findByStatus(final int transform, EnableDisableStatus status);
	public Collection loadAll();
	public Collection loadAll(final int transform);
	
	public Role load(Integer id);
	public Object load(int transform, Integer id);
	public Role load(String name);
	public Object load(int transform, String name);
	
	public Role save(Role role);
	public Object save(int transform, Role role);
	public Collection save(Collection entities);
	public Collection save(int transform, Collection entities);

    public void update(Role role);
    public void update(Collection entities);
    
	public void remove(Role role);
	public void remove(Integer id);
	public void remove(Collection entities);
	
	public void toFullVO(Role role, FullRole target);
	public FullRole toFullVO(Role entity);
	public void toFullVOCollection(Collection entities);
	public void fullVOToEntity(FullRole role, Role target, boolean copyIfNull);
	public Role fullVOToEntity(FullRole role);
	public void fullVOToEntityCollection(Collection instances);
	
	
	
}
