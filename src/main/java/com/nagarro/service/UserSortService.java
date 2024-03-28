package com.nagarro.service;

import java.util.List;

import com.nagarro.entity.User;
import com.nagarro.enums.SortOrder;
import com.nagarro.enums.SortType;

public interface UserSortService {
	public List<User> sortDbUsers(List<User> users, SortType sortType, SortOrder sortOrder);

}
