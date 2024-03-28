package com.nagarro.validators.impl;

import com.nagarro.validators.Validator;

public class EnglishAlphabetsValidator implements Validator{

	private static EnglishAlphabetsValidator obj;
    private EnglishAlphabetsValidator() {
    }

    public static EnglishAlphabetsValidator getObject() {
        if (obj == null) {
            obj = new EnglishAlphabetsValidator();
        }
        return obj;
    }

    @Override
    public boolean validate(String input) {
        return input.matches("[a-zA-Z]+");
    }

}
