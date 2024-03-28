package com.nagarro.strategy.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.nagarro.entity.User;
import com.nagarro.strategy.SortStrategy;

public class EvenNameSortStrategy implements SortStrategy{

	@Override
	public List<User> sort(List<User> users) {
		// TODO Auto-generated method stub
		return users.stream()
                .sorted(Comparator.comparingInt((User user) -> user.getName().length() % 2)
                 .thenComparing(Comparator.comparing(User::getName))) 
                .collect(Collectors.toList());
	}

}
