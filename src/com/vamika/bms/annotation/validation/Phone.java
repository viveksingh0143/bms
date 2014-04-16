package com.vamika.bms.annotation.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Phone {
	String message() default "It is not a phone number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
