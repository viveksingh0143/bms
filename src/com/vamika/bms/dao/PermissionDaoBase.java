package com.vamika.bms.dao;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vamika.bms.model.Permission;
import com.vamika.bms.view.FullPermission;

public abstract class PermissionDaoBase implements PermissionDao {
	@Autowired
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Object transformEntity(final int transform, final Permission entity) {
		Object target = null;
		if (entity != null) {
			switch (transform) {
				case TRANSFORM_NONE:
					target = entity;
					break;
				case TRANSFORM_FULL:
				default:
					target = toFullVO(entity);
			}
		}
		return target;
	}

	public void toFullVO(Permission permission, FullPermission target) {
		target.setId(permission.getId());
		target.setName(permission.getName());
	}

	public FullPermission toFullVO(final Permission entity) {
		final FullPermission target = new FullPermission();
		this.toFullVO(entity, target);
		return target;
	}

	protected FullPermission toFullVO(Object[] row) {
		FullPermission target = null;
		if (row != null) {
			final int numberOfObjects = row.length;
			for (int ctr = 0; ctr < numberOfObjects; ctr++) {
				final Object object = row[ctr];
				if (object instanceof Permission) {
					target = this.toFullVO((Permission) object);
					break;
				}
			}
		}
		return target;
	}

	protected void transformEntities(final int transform,
			final Collection entities) {
		switch (transform) {
			case TRANSFORM_NONE:
				break;
			case TRANSFORM_FULL:
			default:
				toFullVOCollection(entities);
		}
	}

	public final void toFullVOCollection(Collection entities) {
		if (entities != null) {
			CollectionUtils.transform(entities, FULLVO_TRANSFORMER);
		}
	}

	private Transformer FULLVO_TRANSFORMER = new Transformer() {
		public Object transform(Object input) {
			Object result = null;
			if (input instanceof Permission) {
				result = toFullVO((Permission) input);
			} else if (input instanceof Object[]) {
				result = toFullVO((Object[]) input);
			}
			return result;
		}
	};

	public final void fullVOToEntityCollection(Collection instances) {
		if (instances != null) {
			for (final Iterator iterator = instances.iterator(); iterator
					.hasNext();) {
				if (!(iterator.next() instanceof FullPermission)) {
					iterator.remove();
				}
			}
			CollectionUtils.transform(instances, FullVOToEntityTransformer);
		}
	}

	private final Transformer FullVOToEntityTransformer = new Transformer() {
		public Object transform(Object input) {
			return fullVOToEntity((FullPermission) input);
		}
	};

	public Permission fullVOToEntity(FullPermission permissionVO) {
		Permission entity = Permission.Factory.newInstance();
		this.fullVOToEntity(permissionVO, entity, Boolean.FALSE);
		return entity;
	}

	public void fullVOToEntity(FullPermission permissionVO, Permission target, boolean copyIfNull) {
		if ((copyIfNull || permissionVO.getId() != null)) {
			target.setId(permissionVO.getId());
		}
		if (copyIfNull || permissionVO.getName() != null) {
			target.setName(permissionVO.getName());
		}
	}
}