package com.nagarro.service;


public interface ValidationService {
	public void requestParameterValidation(String limit, String offset, String sortType, String sortOrder);
	
	public void postRequestParameterValidation(String size);
}
