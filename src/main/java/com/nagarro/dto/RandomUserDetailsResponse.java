//RandomUser DTO to handle the JSON response of random user from API1
package com.nagarro.dto;
//importing packages
import java.util.List;

public class RandomUserDetailsResponse {
	//List of Random User Details as JSOn response is coming under results object
	private List<RandomUserDetails> results;

	public List<RandomUserDetails> getResults() {
		return results;
	}

	public void setResults(List<RandomUserDetails> results) {
		this.results = results;
	}

}
