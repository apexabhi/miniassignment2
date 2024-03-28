package com.nagarro.service;

import com.nagarro.dto.RandomUserDetails;

import reactor.core.publisher.Mono;

public interface RandomUserService {
	public Mono<RandomUserDetails> generateRandomUser();
	public RandomUserDetails getRandomUser();

}
