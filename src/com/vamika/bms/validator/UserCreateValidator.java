package com.vamika.bms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vamika.bms.model.enums.UsersStatus;
import com.vamika.bms.model.enums.UsersType;
import com.vamika.bms.view.FullUser;

@Component
public class UserCreateValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return FullUser.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "admin", "admin.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "status.required", "Field name is required.");
		FullUser user = (FullUser) target;
		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			errors.rejectValue("confirmPassword", "password.mismatched", "Password not matched");
		}
		if(user.getAdmin() != null && !user.getAdmin().equals("")) {
			Boolean notFound = Boolean.TRUE;
			for (UsersType userType : UsersType.values()) {
				if(userType.equals(user.getAdmin())) {
					notFound = Boolean.FALSE;
					break;
				}
			}
			if(notFound) {
				errors.rejectValue("admin", "admin.mismatched", "Type not matched");
			}
		}
		
		if(user.getStatus() != null && !user.getStatus().equals("")) {
			Boolean notFound = Boolean.TRUE;
			for (UsersStatus userStatus : UsersStatus.values()) {
				if(userStatus.equals(user.getStatus())) {
					notFound = Boolean.FALSE;
					break;
				}
			}
			if(notFound) {
				errors.rejectValue("status", "status.mismatched", "Type not matched");
			}
		}
	}
}