package com.nagarro.service;

import java.util.concurrent.CompletableFuture;

import com.nagarro.dto.CountryResponse;
import com.nagarro.dto.GenderResponse;
import com.nagarro.dto.RandomUserDetailsResponse;

import reactor.core.publisher.Mono;


public interface ApiService {
	
	public Mono<RandomUserDetailsResponse> getRandomUserDetails();
	
	public CompletableFuture<CountryResponse> getApi2Data(String name);
	
	public CompletableFuture<GenderResponse> getApi3Data(String name);
	
}
