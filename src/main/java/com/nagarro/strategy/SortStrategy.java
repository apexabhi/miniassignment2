package com.nagarro.strategy;

import java.util.List;

import com.nagarro.entity.User;

public interface SortStrategy {
	List<User> sort(List<User> users);

}
