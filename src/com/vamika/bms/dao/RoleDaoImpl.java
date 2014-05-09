package com.vamika.bms.dao;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vamika.bms.model.Role;
import com.vamika.bms.model.enums.EnableDisableStatus;

@Repository
public class RoleDaoImpl extends RoleDaoBase {

	@Override
	public Collection findByStatus(EnableDisableStatus status) {
		return findByStatus(TRANSFORM_NONE, status);
	}

	@Override
	public Collection findByStatus(int transform, EnableDisableStatus status) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(Role.class);
		query.add(Restrictions.eq("status", status));
		query.addOrder(Order.asc("name").ignoreCase());
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
				.createCriteria(Role.class);
		query.addOrder(Order.asc("name").ignoreCase());
		final Collection entities = query.list();
		transformEntities(transform, entities);
		return entities;
	}

	@Override
	public Role load(Integer id) {
		return (Role) load(TRANSFORM_NONE, id);
	}

	@Override
	public Object load(int transform, Integer id) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(Role.class);
		query.add(Restrictions.eq("id", id));
		final Role entity = (Role) query.uniqueResult();
		return transformEntity(transform, entity);
	}

	@Override
	public Role load(String name) {
		return (Role) load(TRANSFORM_NONE, name);
	}

	@Override
	public Object load(int transform, String name) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(Role.class);
		query.add(Restrictions.eq("name", name));
		final Role entity = (Role) query.uniqueResult();
		return transformEntity(transform, entity);
	}

	@Override
	public Role save(Role role) {
		getSessionFactory().getCurrentSession().save(role);
		return role;
	}

	@Override
	public Object save(int transform, Role role) {
		getSessionFactory().getCurrentSession().save(role);
		return transformEntity(transform, role);
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
	public void update(Role role) {
		getSessionFactory().getCurrentSession().update(role);
	}

	@Override
	public void update(Collection entities) {
		getSessionFactory().getCurrentSession().update(entities);
	}

	@Override
	public void remove(Role role) {
		role.getUsers().clear();
		role.getPermissions().clear();
		getSessionFactory().getCurrentSession().delete(role);
	}

	@Override
	public void remove(Integer id) {
		Role role = load(id);
		remove(role);
	}

	@Override
	public void remove(Collection entities) {
		getSessionFactory().getCurrentSession().delete(entities);
	}
}
