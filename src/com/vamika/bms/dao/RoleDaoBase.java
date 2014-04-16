package com.vamika.bms.dao;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vamika.bms.model.Role;
import com.vamika.bms.view.FullRole;

public abstract class RoleDaoBase implements RoleDao {
	@Autowired
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Object transformEntity(final int transform, final Role entity) {
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

	public void toFullVO(Role role, FullRole target) {
		target.setId(role.getId());
		target.setName(role.getName());
		target.setStatus(role.getStatus());
	}

	public FullRole toFullVO(final Role entity) {
		final FullRole target = new FullRole();
		this.toFullVO(entity, target);
		return target;
	}

	protected FullRole toFullVO(Object[] row) {
		FullRole target = null;
		if (row != null) {
			final int numberOfObjects = row.length;
			for (int ctr = 0; ctr < numberOfObjects; ctr++) {
				final Object object = row[ctr];
				if (object instanceof Role) {
					target = this.toFullVO((Role) object);
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
			if (input instanceof Role) {
				result = toFullVO((Role) input);
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
				if (!(iterator.next() instanceof FullRole)) {
					iterator.remove();
				}
			}
			CollectionUtils.transform(instances, FullVOToEntityTransformer);
		}
	}

	private final Transformer FullVOToEntityTransformer = new Transformer() {
		public Object transform(Object input) {
			return fullVOToEntity((FullRole) input);
		}
	};

	public Role fullVOToEntity(FullRole roleVO) {
		Role entity = Role.Factory.newInstance();
		this.fullVOToEntity(roleVO, entity, Boolean.FALSE);
		return entity;
	}

	public void fullVOToEntity(FullRole roleVO, Role target, boolean copyIfNull) {
		if ((copyIfNull || roleVO.getId() != null)) {
			target.setId(roleVO.getId());
		}
		if (copyIfNull || roleVO.getName() != null) {
			target.setName(roleVO.getName());
		}
		if (copyIfNull || roleVO.getStatus() != null) {
			target.setStatus(roleVO.getStatus());
		}
	}
}