package com.nagarro.service.impl;
//importing packages
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.dto.RandomUserDetails;
import com.nagarro.exceptions.RandomUserFetchException;
import com.nagarro.service.ApiService;
import com.nagarro.service.RandomUserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RandomUserServiceImpl implements RandomUserService{
	private final ApiService apiService;
	
	@Autowired
	public RandomUserServiceImpl(ApiService apiService) {
		this.apiService=apiService;
	}

	//Function to fetch single Random User details
	@Override
	public Mono<RandomUserDetails> generateRandomUser() {
	    return apiService.getRandomUserDetails()
	            .map(userDetailsResponse -> userDetailsResponse.getResults())
	            .flatMapMany((List<RandomUserDetails> userDetailsList) -> Flux.fromIterable(userDetailsList))
	            .next(); 
	}

	//Function for converting mono object to simple object
	@Override
	public RandomUserDetails getRandomUser() {
		// TODO Auto-generated method stub
		Mono<RandomUserDetails> user=generateRandomUser();
		if(user==null) {
			throw new RandomUserFetchException("Random User fetched is null! Try Again");
		}
		return user.block();
	}

	
}
