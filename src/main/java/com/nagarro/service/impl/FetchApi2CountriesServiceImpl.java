package com.nagarro.service.impl;
//importing packages
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.dto.CountryResponse;
import com.nagarro.service.FetchApi2CountriesService;
import com.nagarro.service.ParallelTasksService;

@Service
public class FetchApi2CountriesServiceImpl implements FetchApi2CountriesService{
	private final ParallelTasksService parallelService;
	
	@Autowired
	public FetchApi2CountriesServiceImpl(ParallelTasksService parallelService) {
		this.parallelService=parallelService;
	}

	//function to extract list of country ids from API2
	@Override
	public List<String> getApi2CountryIds() {
		// TODO Auto-generated method stub
		return parallelService.getApi2Result().getCountry().stream()
                .map(CountryResponse.CountryInfo::getCountry_id)
                .collect(Collectors.toList());
	}

}
