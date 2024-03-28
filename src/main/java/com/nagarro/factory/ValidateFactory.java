package com.nagarro.factory;

import com.nagarro.validators.Validator;
import com.nagarro.validators.impl.*;

public class ValidateFactory {
	 public static Validator getValidator(String str) {
	        if ("limit".equalsIgnoreCase(str) || "offset".equalsIgnoreCase(str)|| "size".equalsIgnoreCase(str)) {
	            return NumericValidator.getObject();
	        } else {
	            return EnglishAlphabetsValidator.getObject();
	        }
	    }

}
