package com.nagarro.validators.impl;

import com.nagarro.validators.Validator;

public class NumericValidator implements Validator{

	private static NumericValidator obj;
    
	private NumericValidator() {}
	
    public static NumericValidator getObject() {
        if (obj == null) {
            obj = new NumericValidator();
        }
        return obj;
    }

    @Override
    public boolean validate(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
