package com.vamika.bms.dao;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vamika.bms.model.Permission;

@Repository
public class PermissionDaoImpl extends PermissionDaoBase {

	@Override
	public Collection loadAll() {
		return loadAll(TRANSFORM_NONE);
	}

	@Override
	public Collection loadAll(int transform) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(Permission.class);
		query.addOrder(Order.asc("name").ignoreCase());
		final Collection entities = query.list();
		transformEntities(transform, entities);
		return entities;
	}

	@Override
	public Permission load(Integer id) {
		return (Permission) load(TRANSFORM_NONE, id);
	}

	@Override
	public Object load(int transform, Integer id) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(Permission.class);
		query.add(Restrictions.eq("id", id));
		final Permission entity = (Permission) query.uniqueResult();
		return transformEntity(transform, entity);
	}

	@Override
	public Permission load(String name) {
		return (Permission) load(TRANSFORM_NONE, name);
	}

	@Override
	public Object load(int transform, String name) {
		Criteria query = getSessionFactory().getCurrentSession()
				.createCriteria(Permission.class);
		query.add(Restrictions.eq("name", name));
		final Permission entity = (Permission) query.uniqueResult();
		return transformEntity(transform, entity);
	}

	@Override
	public Permission save(Permission permission) {
		return (Permission)save(PermissionDao.TRANSFORM_NONE, permission);
	}

	@Override
	public Object save(int transform, Permission permission) {
		getSessionFactory().getCurrentSession().save(permission);
		return transformEntity(transform, permission);
	}
	
	@Override
	public Permission saveOrUpdate(Permission permission) {
		return (Permission)saveOrUpdate(PermissionDao.TRANSFORM_NONE, permission);
	}
	
	@Override
	public Object saveOrUpdate(int transform, Permission permission) {
		getSessionFactory().getCurrentSession().saveOrUpdate(permission);
		return transformEntity(transform, permission);
	}

	@Override
	public Collection save(Collection entities) {
		return save(PermissionDao.TRANSFORM_NONE, entities);
	}

	@Override
	public Collection save(int transform, Collection entities) {
		getSessionFactory().getCurrentSession().save(entities);
		transformEntities(transform, entities);
		return entities;
	}

	@Override
	public void remove(Permission permission) {
		getSessionFactory().getCurrentSession().delete(permission);

	}

	@Override
	public void remove(Integer id) {
		Permission permission = load(id);
		getSessionFactory().getCurrentSession().delete(permission);
	}

	@Override
	public void remove(Collection entities) {
		getSessionFactory().getCurrentSession().delete(entities);
	}
}
