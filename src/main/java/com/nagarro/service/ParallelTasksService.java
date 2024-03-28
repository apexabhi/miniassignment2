package com.nagarro.service;

import java.util.concurrent.CompletableFuture;

import com.nagarro.dto.CountryResponse;
import com.nagarro.dto.GenderResponse;

public interface ParallelTasksService {
	public CompletableFuture<Void>  runApi2AndApi3InParallel(String name);
	
	public CountryResponse getApi2Result();
	
	public void setApi2Result(CountryResponse api2Result);

	public GenderResponse getApi3Result();

	public void setApi3Result(GenderResponse api3Result);
	
//	public List<String> getApi2CountryIds();

}
