package com.vamika.bms.annotation.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UniqueConstraintValidator implements ConstraintValidator<Unique, Object> {

	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	private Class<?>entityClass;
	private String uniqueField;
	private String[]uniqueFields;
	private String[]ignoreFields;
	
	@Override
	public void initialize(Unique unique) {
		entityClass = unique.entity();
        uniqueFields = unique.uniqueFields();
        ignoreFields = unique.ignoreFields();
        uniqueField = unique.uniqueField();
	}

	@Override
	@Transactional
	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
			for(String uniqueField: uniqueFields) {
				Object uniqueFieldValue = PropertyUtils.getProperty(value, uniqueField);
				criteria.add(Restrictions.eq(uniqueField, uniqueFieldValue));
				this.uniqueField = uniqueField;
			}
			if(ignoreFields != null && ignoreFields.length > 0) {
				for(String ignoreField: ignoreFields) {
					Object ignoreFieldValue = PropertyUtils.getProperty(value, ignoreField);
					criteria.add(Restrictions.ne(ignoreField, ignoreFieldValue));
				}
			}
	        return criteria.list().isEmpty();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
