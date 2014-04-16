package com.vamika.bms.dao;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vamika.bms.model.User;
import com.vamika.bms.view.FullUser;
import com.vamika.bms.view.ShortUser;

public abstract class UserDaoBase implements UserDao {
	@Autowired
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Object transformEntity(final int transform, final User entity) {
		Object target = null;
		if (entity != null) {
			switch (transform) {
			case TRANSFORM_NONE:
				target = entity;
				break;
			case TRANSFORM_FULL:
				target = toFullVO(entity);
				break;
			case TRANSFORM_SHORT:
			default:
				target = toShortVO(entity);
			}
		}
		return target;
	}

	public void toShortVO(User user, ShortUser target) {
		target.setId(user.getId());
		target.setUsername(user.getUsername());
		target.setName(user.getName());
		target.setStatus(user.getStatus());
		target.setEmail(user.getEmail());
	}

	public ShortUser toShortVO(final User entity) {
		final ShortUser target = new ShortUser();
		this.toShortVO(entity, target);
		return target;
	}

	protected ShortUser toShortVO(Object[] row) {
		ShortUser target = null;
		if (row != null) {
			final int numberOfObjects = row.length;
			for (int ctr = 0; ctr < numberOfObjects; ctr++) {
				final Object object = row[ctr];
				if (object instanceof User) {
					target = this.toShortVO((User) object);
					break;
				}
			}
		}
		return target;
	}

	public void toFullVO(User user, FullUser target) {
		target.setId(user.getId());
		target.setUsername(user.getUsername());
		target.setName(user.getName());
		target.setStatus(user.getStatus());
		target.setEmail(user.getEmail());
		target.setAdmin(user.getAdmin());
		target.setPassword(user.getPassword());
	}

	public FullUser toFullVO(final User entity) {
		final FullUser target = new FullUser();
		this.toFullVO(entity, target);
		return target;
	}

	protected FullUser toFullVO(Object[] row) {
		FullUser target = null;
		if (row != null) {
			final int numberOfObjects = row.length;
			for (int ctr = 0; ctr < numberOfObjects; ctr++) {
				final Object object = row[ctr];
				if (object instanceof User) {
					target = this.toFullVO((User) object);
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
			toFullVOCollection(entities);
			break;
		case TRANSFORM_SHORT:
		default:
			toShortVOCollection(entities);
		}
	}

	public final void toShortVOCollection(Collection entities) {
		if (entities != null) {
			CollectionUtils.transform(entities, SHORTVO_TRANSFORMER);
		}
	}

	private Transformer SHORTVO_TRANSFORMER = new Transformer() {
		public Object transform(Object input) {
			Object result = null;
			if (input instanceof User) {
				result = toShortVO((User) input);
			} else if (input instanceof Object[]) {
				result = toShortVO((Object[]) input);
			}
			return result;
		}
	};

	public final void toFullVOCollection(Collection entities) {
		if (entities != null) {
			CollectionUtils.transform(entities, FULLVO_TRANSFORMER);
		}
	}

	private Transformer FULLVO_TRANSFORMER = new Transformer() {
		public Object transform(Object input) {
			Object result = null;
			if (input instanceof User) {
				result = toFullVO((User) input);
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
				if (!(iterator.next() instanceof FullUser)) {
					iterator.remove();
				}
			}
			CollectionUtils.transform(instances, FullVOToEntityTransformer);
		}
	}

	private final Transformer FullVOToEntityTransformer = new Transformer() {
		public Object transform(Object input) {
			return fullVOToEntity((FullUser) input);
		}
	};

	public User fullVOToEntity(FullUser userVO) {
		User entity = User.Factory.newInstance();
		this.fullVOToEntity(userVO, entity, Boolean.FALSE);
		return entity;
	}

	public void fullVOToEntity(FullUser userVO, User target, boolean copyIfNull) {
		if ((copyIfNull || userVO.getId() != null)) {
			target.setId(userVO.getId());
		}
		if (copyIfNull || userVO.getUsername() != null) {
			target.setUsername(userVO.getUsername());
		}
		if (copyIfNull || userVO.getEmail() != null) {
			target.setEmail(userVO.getEmail());
		}
		if (copyIfNull || userVO.getName() != null) {
			target.setName(userVO.getName());
		}
		if (copyIfNull || userVO.getStatus() != null) {
			target.setStatus(userVO.getStatus());
		}
		if (copyIfNull || userVO.getAdmin() != null) {
			target.setAdmin(userVO.getAdmin());
		}
		if (copyIfNull || userVO.getPassword() != null) {
			target.setPassword(userVO.getPassword());
		}
	}

	public final void shortVOToEntityCollection(Collection instances) {
		if (instances != null) {
			for (final Iterator iterator = instances.iterator(); iterator
					.hasNext();) {
				if (!(iterator.next() instanceof ShortUser)) {
					iterator.remove();
				}
			}
			CollectionUtils.transform(instances, ShortVOToEntityTransformer);
		}
	}

	private final Transformer ShortVOToEntityTransformer = new Transformer() {
		public Object transform(Object input) {
			return shortVOToEntity((ShortUser) input);
		}
	};

	public User shortVOToEntity(ShortUser userVO) {
		User entity = User.Factory.newInstance();
		this.shortVOToEntity(userVO, entity, Boolean.FALSE);
		return entity;
	}

	public void shortVOToEntity(ShortUser userVO, User target,
			boolean copyIfNull) {
		if ((copyIfNull || userVO.getId() != null)) {
			target.setId(userVO.getId());
		}
		if (copyIfNull || userVO.getUsername() != null) {
			target.setUsername(userVO.getUsername());
		}
		if (copyIfNull || userVO.getEmail() != null) {
			target.setEmail(userVO.getEmail());
		}
		if (copyIfNull || userVO.getName() != null) {
			target.setName(userVO.getName());
		}
		if (copyIfNull || userVO.getStatus() != null) {
			target.setStatus(userVO.getStatus());
		}
	}
}
