package com.nagarro.service.impl;
//importing packages
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.dto.CountryResponse;
import com.nagarro.dto.GenderResponse;
import com.nagarro.exceptions.Api2AndApi3FetchException;
import com.nagarro.service.ApiService;
import com.nagarro.service.ParallelTasksService;

@Service
public class ParallelTasksServiceImpl implements ParallelTasksService {
	private final ApiService apiService;
	private CountryResponse api2Result;
    private GenderResponse api3Result;
    
	@Autowired
	public ParallelTasksServiceImpl(ApiService apiService) {
		this.apiService=apiService;
	}

	//function to execute API2 and API3 in parallel
	@Override
	public CompletableFuture<Void>  runApi2AndApi3InParallel(String name) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<CountryResponse> api2Obj = apiService.getApi2Data(name);
        CompletableFuture<GenderResponse> api3Obj = apiService.getApi3Data(name);
       
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(api2Obj, api3Obj);

        return combinedFuture.thenRunAsync(() -> {
            try {
                api2Result = api2Obj.join();
                api3Result = api3Obj.join();
                if(api2Result==null || api3Result==null) {
                	throw new Api2AndApi3FetchException("Issue in fetching Api2 and Api3! Try Again");
                }
                List<CountryResponse.CountryInfo> countries = api2Result.getCountry();
                for (CountryResponse.CountryInfo country : countries) {
                    System.out.println("API 2 Country ID: " + country.getCountry_id());
                    System.out.println("API 2 Probability: " + country.getProbability());
                }
                System.out.println("API 3 Result: " + api3Result.getGender());
            } catch (Exception e) {
            	 throw new Api2AndApi3FetchException("Error processing Api2 and Api3 results");
            } finally {
                executorService.shutdown();
            }
        }, executorService).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }

	public CountryResponse getApi2Result() {
		return api2Result;
	}

	public void setApi2Result(CountryResponse api2) {
		this.api2Result = api2;
	}

	public GenderResponse getApi3Result() {
		return api3Result;
	}

	public void setApi3Result(GenderResponse api3) {
		this.api3Result = api3;
	}
	
}
