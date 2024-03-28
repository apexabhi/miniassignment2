package com.nagarro.service.impl;
//importing packages
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.nagarro.dto.RandomUserDetails;
import com.nagarro.entity.User;
import com.nagarro.enums.SortOrder;
import com.nagarro.enums.SortType;
import com.nagarro.exceptions.RandomUserFetchException;
import com.nagarro.exceptions.RequestParametersException;
import com.nagarro.repository.UserRepository;
import com.nagarro.service.RandomUserService;
import com.nagarro.service.UserService;
import com.nagarro.service.UserSortService;
import com.nagarro.service.VerifyUserService;

@Service
public class UserServiceImpl implements UserService {
	private final RandomUserService randomUserService;
	private final VerifyUserService verifyUserService;
	private final UserRepository userRepository;
	private final UserSortService userSortService;

	@Autowired
	public UserServiceImpl(VerifyUserService verifyUserService, RandomUserService randomUserService,
			UserRepository userRepository, UserSortService userSortService) {
		this.verifyUserService = verifyUserService;
		this.randomUserService = randomUserService;
		this.userRepository = userRepository;
		this.userSortService= userSortService;
	}

	//function to store random user in database
	@Override
	public List<User> addUser(int size) {
		if (size > 5 || size<1) {
			throw new RequestParametersException("Size should not exceed 5 and not less than 1");
		}
		List<User> userList = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			RandomUserDetails randomUser = randomUserService.getRandomUser();
			if (randomUser != null ) {
				String vstatus = verifyUserService.verifyUser(randomUser);
				User user = new User();
				user.setAge(randomUser.getDob().getAge());
				user.setDob(randomUser.getDob().getDate());
				user.setGender(randomUser.getGender());
				user.setName(randomUser.getName().getFirst() + " " + randomUser.getName().getLast());
				user.setNationality(randomUser.getNat());
				user.setVerificationStatus(vstatus);
				user.setPhone(randomUser.getPhone());
				userRepository.save(user);
				userList.add(user);
			} else {
				throw new RandomUserFetchException("Random User fetched is null! Try Again");
			}
		}
		return userList;

	}
	
	//function to fetch users from db as per require parameters
	@Override
	public List<User> fetchUsersFromDb(int limit, int offset, SortType sortType, SortOrder sortOrder) {
		if (limit > 5 || limit<1) {
			throw new RequestParametersException("Limit should not exceed 5 and not less than 1");
		}
		Pageable pageDetails = PageRequest.of(0, offset + limit);
	    Page<User> pages = userRepository.findAll(pageDetails);
	    List<User> allUsers = pages.getContent();
	    int start = Math.min(offset, allUsers.size());
	    int end = Math.min(offset + limit, allUsers.size());
	    allUsers=allUsers.subList(start, end);
	    return userSortService.sortDbUsers(allUsers, sortType, sortOrder);
	    //return allUsers;
	}

	//function to return total count of users stored in db
	@Override
	public long getTotalUsersCountFromDb() {
		// TODO Auto-generated method stub
		return userRepository.count();
	}

}
