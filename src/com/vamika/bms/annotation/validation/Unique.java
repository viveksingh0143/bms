package com.vamika.bms.annotation.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueConstraintValidator.class)
@Documented
public @interface Unique {
	String message() default "{Unique}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	Class<?> entity();
	String uniqueField() default "id"; //Annotation Attribute Name
	String[]uniqueFields() default {"id"};
	String[]ignoreFields() default {};
}