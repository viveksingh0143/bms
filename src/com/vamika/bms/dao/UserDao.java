package com.vamika.bms.dao;

import java.util.Collection;

import com.vamika.bms.model.User;
import com.vamika.bms.model.enums.UsersStatus;
import com.vamika.bms.view.FullUser;
import com.vamika.bms.view.ShortUser;

public interface UserDao {
	public final static int TRANSFORM_NONE = 0;
	public final static int TRANSFORM_SHORT = 1;
	public final static int TRANSFORM_FULL = 2;
	
	public Collection findByStatus(UsersStatus status);
	public Collection findByStatus(final int transform, UsersStatus status);
	public Collection loadAll();
	public Collection loadAll(final int transform);
	
	public User load(Integer id);
	public Object load(int transform, Integer id);
	public User load(String userName);
	public Object load(int transform, String userName);
	
	public User save(User user);
	public Object save(int transform, User user);
	public Collection save(Collection entities);
	public Collection save(int transform, Collection entities);

    public void update(User user);
    public void update(Collection entities);
    
	public void remove(User user);
	public void remove(Integer id);
	public void remove(Collection entities);
	
	
	
	public void toShortVO(User user, ShortUser target);
	public ShortUser toShortVO(User entity);
	public void toShortVOCollection(Collection entities);
	public void shortVOToEntity(ShortUser user, User target, boolean copyIfNull);
	public User shortVOToEntity(ShortUser user);
	public void shortVOToEntityCollection(Collection instances);
	
	public void toFullVO(User user, FullUser target);
	public FullUser toFullVO(User entity);
	public void toFullVOCollection(Collection entities);
	public void fullVOToEntity(FullUser user, User target, boolean copyIfNull);
	public User fullVOToEntity(FullUser user);
	public void fullVOToEntityCollection(Collection instances);
	
	
	
}
