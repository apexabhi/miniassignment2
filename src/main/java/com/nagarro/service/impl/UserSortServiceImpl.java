package com.nagarro.service.impl;
//importing packages
import java.util.List;
import org.springframework.stereotype.Service;
import com.nagarro.context.SortContext;
import com.nagarro.entity.User;
import com.nagarro.enums.SortOrder;
import com.nagarro.enums.SortType;
import com.nagarro.service.UserSortService;
import com.nagarro.strategy.impl.EvenAgeSortStrategy;
import com.nagarro.strategy.impl.EvenNameSortStrategy;
import com.nagarro.strategy.impl.OddAgeSortStrategy;
import com.nagarro.strategy.impl.OddNameSortStrategy;

@Service
public class UserSortServiceImpl implements UserSortService{

	//to sort users
	@Override
	public List<User> sortDbUsers(List<User> users, SortType sortType, SortOrder sortOrder) {
		// TODO Auto-generated method stub
		SortContext sortContext= new SortContext();
		if(sortType.toString().equalsIgnoreCase("AGE")) {
			if(sortOrder.toString().equalsIgnoreCase("EVEN")) {
				sortContext.setSortStrategy(new EvenAgeSortStrategy());
			}
			else if(sortOrder.toString().equalsIgnoreCase("ODD")) {
				sortContext.setSortStrategy(new OddAgeSortStrategy());
			}
		}
		else if(sortType.toString().equalsIgnoreCase("NAME")) {
			if(sortOrder.toString().equalsIgnoreCase("EVEN")) {
				sortContext.setSortStrategy(new EvenNameSortStrategy());
			}
			else if(sortOrder.toString().equalsIgnoreCase("ODD")) {
				sortContext.setSortStrategy(new OddNameSortStrategy());
			}
			
		}
		return sortContext.sortUser(users);
	}

}
