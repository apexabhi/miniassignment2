package com.nagarro.service;

import java.util.List;

import com.nagarro.entity.User;
import com.nagarro.enums.SortOrder;
import com.nagarro.enums.SortType;

public interface UserService {
	
	public List<User> addUser(int size);
	
	public List<User> fetchUsersFromDb(int limit, int offset, SortType sortType, SortOrder sortOrder);
	
	public long getTotalUsersCountFromDb();

}
