//Handler to handle customized exceptions
package com.nagarro.exceptions;
//importing packages
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.nagarro.dto.ErrorResponse;

@ControllerAdvice
public class CustomeExceptionHandler {
	@ExceptionHandler(RequestParametersException.class)
	public ResponseEntity<ErrorResponse> manageRequestParameters(RequestParametersException e) {
		
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(RandomUserFetchException.class)
	public ResponseEntity<ErrorResponse> manageRandomUser(RandomUserFetchException e) {
		
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value()),HttpStatus.EXPECTATION_FAILED);
		
	}
	
	@ExceptionHandler(Api2AndApi3FetchException.class)
	public ResponseEntity<ErrorResponse> manageApi2Api3(Api2AndApi3FetchException e) {
		
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value()),HttpStatus.EXPECTATION_FAILED);
		
	}
	@ExceptionHandler(Exception.class) // 
    public ResponseEntity<ErrorResponse> handleExceptions( Exception e) {
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
	

}
