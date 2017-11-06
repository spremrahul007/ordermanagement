package com.ebrordermanagement.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ebrordermanagement.model.Password;


public class PasswordValidator implements Validator {

	public boolean supports(Class<?> paramClass) {
		return Password.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailid", "valid.emailid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConf", "valid.passwordConf");
		Password password = (Password) obj;
		if (!password.getPassword().equals(password.getPasswordConf())) {
			errors.rejectValue("passwordConf", "valid.passwordConfDiff");
		}
	}
}