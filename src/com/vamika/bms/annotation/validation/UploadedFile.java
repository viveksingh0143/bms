package com.vamika.bms.annotation.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = UploadedFileValidator.class)
@Documented
public @interface UploadedFile {

	String message() default "{constraints.uploadedfile}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}