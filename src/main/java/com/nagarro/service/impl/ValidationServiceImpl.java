package com.nagarro.service.impl;
//importing packages
import org.springframework.stereotype.Service;
import com.nagarro.exceptions.RequestParametersException;
import com.nagarro.factory.ValidateFactory;
import com.nagarro.service.ValidationService;
import com.nagarro.validators.Validator;

@Service
public class ValidationServiceImpl implements ValidationService{

	//to perform validation on request params
	@Override
	public void requestParameterValidation(String limit, String offset, String sortType, String sortOrder) {
		// TODO Auto-generated method stub
		Validator limitValidator = ValidateFactory.getValidator("limit");
		Validator offsetValidator = ValidateFactory.getValidator("offset");
		Validator sortTypeValidator = ValidateFactory.getValidator("sortType");
		Validator sortOrderValidator = ValidateFactory.getValidator("sortOrder");
        if (!limitValidator.validate(limit) || !offsetValidator.validate(offset) || 
        		!sortTypeValidator.validate(sortType) || !sortOrderValidator.validate(sortOrder)) {
        	throw new RequestParametersException("Invalid Request Parameters- (size,limit,offset should be numeric)(sortType and sortOrder should be alphabets)");
        }
		
	}

	@Override
	public void postRequestParameterValidation(String size) {
		// TODO Auto-generated method stub
		Validator sizeValidator = ValidateFactory.getValidator("size");
		if (!sizeValidator.validate(size)) {
        	throw new RequestParametersException("Invalid Request Parameters- (size,limit,offset should be numeric)(sortType and sortOrder should be alphabets)");
        }
		
	}

}
