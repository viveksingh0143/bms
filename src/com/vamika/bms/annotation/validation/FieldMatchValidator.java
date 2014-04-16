package com.vamika.bms.annotation.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	private String firstFieldName;
	private String secondFieldName;
	private FieldMatchType type;

	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		type = constraintAnnotation.type();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		try {
			final Object firstObj = PropertyUtils.getProperty(value, firstFieldName);
			final Object secondObj = PropertyUtils.getProperty(value, secondFieldName);

			return type.isValid(firstObj, secondObj);
		} catch (final Exception ignore) {
			// ignore
			return false;
		}
	}
}