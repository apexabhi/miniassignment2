//Service to fetch results from external APIs
package com.nagarro.service.impl;
//importing packages
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.codec.DecodingException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.nagarro.dto.CountryResponse;
import com.nagarro.dto.GenderResponse;
import com.nagarro.dto.RandomUserDetailsResponse;
import com.nagarro.service.ApiService;
import reactor.core.publisher.Mono;

@Service
public class ApiServiceImpl implements ApiService{
	private final WebClient.Builder api1WebClientBuilder;
    private final WebClient.Builder api2WebClientBuilder;
    private final WebClient.Builder api3WebClientBuilder;
    
    @Autowired
    public ApiServiceImpl(
            @Qualifier("api1") WebClient.Builder api1WebClientBuilder,
            @Qualifier("api2") WebClient.Builder api2WebClientBuilder,
            @Qualifier("api3") WebClient.Builder api3WebClientBuilder
    ) {
        this.api1WebClientBuilder = api1WebClientBuilder;
        this.api2WebClientBuilder = api2WebClientBuilder;
        this.api3WebClientBuilder = api3WebClientBuilder;
    }

    //API1
    public Mono<RandomUserDetailsResponse> getRandomUserDetails() {
    	return api1WebClientBuilder
                .build()
                .get()
                .retrieve()
                .bodyToMono(RandomUserDetailsResponse.class)
                .onErrorResume(DecodingException.class, ex -> {
                    System.err.println("Problem in decoding JSON- " + ex.getMessage());
                    return Mono.empty();
                });
    }
    
    //API2
    public CompletableFuture<CountryResponse> getApi2Data(String name) {
        String apiUrl = "/?name=" + name;
        return CompletableFuture.supplyAsync(() ->
                api2WebClientBuilder
                        .build()
                        .get()
                        .uri(apiUrl)
                        .retrieve()
                        .bodyToMono(CountryResponse.class)
                        .block()
        );
    }
    
    //API3
    public CompletableFuture<GenderResponse> getApi3Data(String name) {
        String apiUrl = "/?name=" + name;
        return CompletableFuture.supplyAsync(() ->
                api3WebClientBuilder
                        .build()
                        .get()
                        .uri(apiUrl)
                        .retrieve()
                        .bodyToMono(GenderResponse.class)
                        .block()
        );
    }


}
