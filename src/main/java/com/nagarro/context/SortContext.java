package com.nagarro.context;

import java.util.List;

import com.nagarro.entity.User;
import com.nagarro.strategy.SortStrategy;

public class SortContext {
	private SortStrategy sortStrategy;

    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public List<User> sortUser(List<User> users) {
        return sortStrategy.sort(users);
    }

}
