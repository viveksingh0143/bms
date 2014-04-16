package com.vamika.bms.annotation.validation;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UploadedFileValidator implements
		ConstraintValidator<UploadedFile, Object> {

	@Override
	public void initialize(final UploadedFile constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Object value,
			final ConstraintValidatorContext context) {

		final String methodIsEmpty = "isEmpty";
		final String methodGetOriginalName = "getOriginalFilename";

		// This class should be "CommonMultipartFile"
		final Class<? extends Object> clazz = value.getClass();

		// Minimum size for the file, at least the extension.
		final long minLength = 3;

		try {

			if ((Boolean) clazz.getMethod(methodIsEmpty).invoke(value)) {
				return false;
			}

		} catch (final IllegalAccessException e) { // NOPMD - ignore this exception
			// ignore
		} catch (final InvocationTargetException e) { // NOPMD - ignore this exception
			// ignore
		} catch (final NoSuchMethodException e) { // NOPMD - ignore this exception
			// ignore
		}

		try {

			if (((String) clazz.getMethod(methodGetOriginalName).invoke(value))
					.length() >= minLength) {
				return true;
			}

		} catch (final IllegalAccessException e) { // NOPMD - ignore this exception
			// ignore
		} catch (final InvocationTargetException e) { // NOPMD - ignore this exception
			// ignore
		} catch (final NoSuchMethodException e) { // NOPMD - ignore this exception
			// ignore
		}

		return false;
	}

}
