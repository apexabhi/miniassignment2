//Controller to handle mappings
package com.nagarro.controller;
//importing packages
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.dto.PaginationDetail;
import com.nagarro.entity.User;
import com.nagarro.enums.SortOrder;
import com.nagarro.enums.SortType;
import com.nagarro.exceptions.RequestParametersException;
import com.nagarro.service.UserService;
import com.nagarro.service.ValidationService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ValidationService validService;
	
	@Autowired
	public UserController(UserService userService, ValidationService validService) {
		this.userService=userService;
		this.validService=validService;
	}
	
	@PostMapping
	public ResponseEntity<List<User>> createUser(@RequestBody Map<String, String> requestBody){
		String size = requestBody.getOrDefault("size", "1");
		validService.postRequestParameterValidation(size);
		return ResponseEntity.ok().body(userService.addUser(Integer.parseInt(size)));
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> fetchUsers(@RequestParam(defaultValue = "5") String limit,
            @RequestParam(defaultValue = "0") String offset,
            @RequestParam(defaultValue = "Age") String sortType,
            @RequestParam(defaultValue = "Even") String sortOrder){
		validService.requestParameterValidation(limit, offset, sortType, sortOrder);
        SortType st;
        SortOrder so;
        try {
			st=SortType.valueOf(sortType.toUpperCase());
		}
		catch(Exception e) {
			throw new RequestParametersException("SortType can be namd and age only");
		}
        try {
			so=SortOrder.valueOf(sortOrder.toUpperCase());
		}
		catch(Exception e) {
			throw new RequestParametersException("SortOrder can be even and odd only");
		}
		List<User> users = userService.fetchUsersFromDb(Integer.parseInt(limit), Integer.parseInt(offset), st, so);
		long total = userService.getTotalUsersCountFromDb();
		Map<String, Object> response = new HashMap<>();
        response.put("data", users);
        response.put("pageInfo", new PaginationDetail(total, Integer.parseInt(limit), Integer.parseInt(offset)));
        return ResponseEntity.ok().body(response);
	}

}
