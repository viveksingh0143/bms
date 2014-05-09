package com.vamika.bms.dao;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vamika.bms.model.User;
import com.vamika.bms.model.enums.UsersStatus;

@Repository
public class UserDaoImpl extends UserDaoBase {

	@Override
	public Collection findByStatus(UsersStatus status) {
		return findByStatus(TRANSFORM_NONE, status);
	}

	@Override
	public Collection findByStatus(int transform, UsersStatus status) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(User.class);
		query.add(Restrictions.eq("status", status));
		query.addOrder(Order.asc("username").ignoreCase());
		final Collection entities = query.list();
		transformEntities(transform, entities);
		return entities;
	}

	@Override
	public Collection loadAll() {
		return loadAll(TRANSFORM_NONE);
	}

	@Override
	public Collection loadAll(int transform) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(User.class);
		query.addOrder(Order.asc("username").ignoreCase());
		final Collection entities = query.list();
		transformEntities(transform, entities);
		return entities;
	}

	@Override
	public User load(Integer id) {
		return (User) load(TRANSFORM_NONE, id);
	}

	@Override
	public Object load(int transform, Integer id) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(User.class);
		query.add(Restrictions.eq("id", id));
		final User entity = (User) query.uniqueResult();
		return transformEntity(transform, entity);
	}

	@Override
	public User load(String userName) {
		return (User) load(TRANSFORM_NONE, userName);
	}

	@Override
	public Object load(int transform, String userName) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(User.class);
		query.add(Restrictions.eq("username", userName));
		final User entity = (User) query.uniqueResult();
		return transformEntity(transform, entity);
	}

	@Override
	public User save(User user) {
		getSessionFactory().getCurrentSession().save(user);
		return user;
	}

	@Override
	public Object save(int transform, User user) {
		getSessionFactory().getCurrentSession().save(user);
		return transformEntity(transform, user);
	}

	@Override
	public Collection save(Collection entities) {
		getSessionFactory().getCurrentSession().save(entities);
		return entities;
	}

	@Override
	public Collection save(int transform, Collection entities) {
		getSessionFactory().getCurrentSession().save(entities);
		transformEntities(transform, entities);
		return entities;
	}

	@Override
	public void update(User user) {
		getSessionFactory().getCurrentSession().update(user);
	}

	@Override
	public void update(Collection entities) {
		getSessionFactory().getCurrentSession().update(entities);
	}

	@Override
	public void remove(User user) {
		user.getRoles().clear();
		getSessionFactory().getCurrentSession().delete(user);
	}

	@Override
	public void remove(Integer id) {
		User user = load(id);
		remove(user);
	}

	@Override
	public void remove(Collection entities) {
		getSessionFactory().getCurrentSession().delete(entities);
	}
}
