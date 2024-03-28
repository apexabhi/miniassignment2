//WebClient Configuration for configuring all three external APIs
package com.nagarro.config;
//importing packages
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
	//API1-To generate random users
	@Bean(name = "api1")
    public WebClient.Builder api1WebClientBuilder() {
		HttpClient client = HttpClient.create()
				  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
				  .doOnConnected(conn -> conn
			            .addHandlerLast(new ReadTimeoutHandler(2000, TimeUnit.MILLISECONDS))
			             .addHandlerLast(new WriteTimeoutHandler(2000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl("https://randomuser.me/api/")
                .clientConnector(new ReactorClientHttpConnector(client))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                
    }
	
	//API2-To generate possible nationalities
	@Bean(name = "api2")
    public WebClient.Builder api2WebClientBuilder() {
		HttpClient client = HttpClient.create()
				  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
				  .doOnConnected(conn -> conn
			            .addHandlerLast(new ReadTimeoutHandler(2000, TimeUnit.MILLISECONDS))
			             .addHandlerLast(new WriteTimeoutHandler(2000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl("https://api.nationalize.io/")
                .clientConnector(new ReactorClientHttpConnector(client))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

	//API3-To predict gender
    @Bean(name = "api3")
    public WebClient.Builder api3WebClientBuilder() {
    	HttpClient client = HttpClient.create()
				  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
				  .doOnConnected(conn -> conn
			            .addHandlerLast(new ReadTimeoutHandler(2000, TimeUnit.MILLISECONDS))
			             .addHandlerLast(new WriteTimeoutHandler(2000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl("https://api.genderize.io/")
                .clientConnector(new ReactorClientHttpConnector(client))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

}
