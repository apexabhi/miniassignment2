package com.nagarro.service.impl;
//importing packages
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.dto.RandomUserDetails;
import com.nagarro.exceptions.Api2AndApi3FetchException;
import com.nagarro.service.FetchApi2CountriesService;
import com.nagarro.service.ParallelTasksService;
import com.nagarro.service.VerifyUserService;

@Service
public class VerifyUserServiceImpl implements VerifyUserService{
	private final ParallelTasksService parallelService;	
	private final FetchApi2CountriesService fetchService;
	@Autowired
	public VerifyUserServiceImpl(ParallelTasksService parallelService, FetchApi2CountriesService fetchService) {
		this.parallelService=parallelService;
		this.fetchService=fetchService;
	}

	//function to find the verification status of user
	@Override
	public String verifyUser(RandomUserDetails user) {
		// TODO Auto-generated method stub
		CompletableFuture<Void> parallelFuture = parallelService.runApi2AndApi3InParallel(user.getName().getFirst());
        parallelFuture.join();
        if(parallelService.getApi2Result()==null || parallelService.getApi3Result()==null) {
        	throw new Api2AndApi3FetchException("Issue in fetching Api2 and Api3! Try Again");
        }
        List<String> api2Countries=fetchService.getApi2CountryIds();
        String api3Gender=parallelService.getApi3Result().getGender();
        if (parallelService.getApi2Result()!=null && parallelService.getApi3Result().getGender()!=null && api2Countries.contains(user.getNat()) && api3Gender.equalsIgnoreCase(user.getGender())) {
            return "VERIFIED";
        } else {
            return "TO_BE_VERIFIED";
        }
		
	}
	
}
