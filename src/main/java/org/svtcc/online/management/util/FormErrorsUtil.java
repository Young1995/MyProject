package org.svtcc.online.management.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class FormErrorsUtil {
	public static Map<String, String> getErrorMap(BindingResult result) {
		List<FieldError> errors = result.getFieldErrors();
		Map<String, String> fieldErrorMap = new HashMap<String, String>();
		for (FieldError fieldError : errors) {
			fieldErrorMap.put(fieldError.getField(),
					fieldError.getDefaultMessage());
		}

		return fieldErrorMap;
	}
}
