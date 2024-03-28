package com.nagarro;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import com.nagarro.controller.UserController;
import com.nagarro.dto.RandomUserDetails;
import com.nagarro.entity.User;
import com.nagarro.enums.SortOrder;
import com.nagarro.enums.SortType;
import com.nagarro.exceptions.RequestParametersException;
import com.nagarro.repository.UserRepository;
import com.nagarro.service.RandomUserService;
import com.nagarro.service.UserService;
import com.nagarro.service.UserSortService;
import com.nagarro.service.VerifyUserService;
import com.nagarro.service.impl.UserServiceImpl;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class Miniassignment2ApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Mock
	private RandomUserService randomService;

	@Mock
	private VerifyUserService verifyService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserSortService userSortService;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	void testContextLoads() {
	}

	//to test post mapping
	@Test
    public void testAddUser() throws Exception {
        when(userService.addUser(anyInt())).thenReturn(Arrays.asList(new User()));
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"size\": \"1\"}")) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

	//to test add user service function
	@Test
	public void testAddUserImpl() {
		RandomUserDetails rd = new RandomUserDetails();
		rd.setName(new RandomUserDetails.Name());
		rd.setDob(new RandomUserDetails.DateOfBirth());
		when(randomService.getRandomUser()).thenReturn(rd);

		when(verifyService.verifyUser(any(RandomUserDetails.class))).thenReturn("VERIFIED");

		assertEquals(1, userServiceImpl.addUser(1).size());
	}

	//to test get users
	@Test
	public void testGetUsers() throws Exception {
		Page<User> mockPage = new PageImpl<>(Collections.singletonList(new User()));
		when(userRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
		SortType st = SortType.AGE;
		SortOrder so = SortOrder.EVEN;
		when(userSortService.sortDbUsers(anyList(), eq(st), eq(so))).thenReturn(Collections.singletonList(new User()));

		mockMvc.perform(get("/users").param("limit", "5").param("offset", "0").param("sortType", "Name")
				.param("sortOrder", "Odd")).andExpect(status().isOk());
	}

	//to test limit 
	@Test
	public void testInvalidLimit() throws Exception {
	    when(userService.fetchUsersFromDb(anyInt(), anyInt(), any(SortType.class), any(SortOrder.class)))
	            .thenThrow(new RequestParametersException("Limit should not exceed 5 and not less than 1"));

	    mockMvc.perform(get("/users")
	            .param("limit", "10") // Invalid limit
	            .param("offset", "0")
	            .param("sortType", "Name")
	            .param("sortOrder", "Odd"))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.message").value("Limit should not exceed 5 and not less than 1"));
	}
	//to test size
	@Test
	public void testInvalidSize() throws Exception {
		when(userService.addUser(anyInt()))
        .thenThrow(new RequestParametersException("Size should not exceed 5 and not less than 1"));
		 mockMvc.perform(post("/users")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content("{\"size\": \"10\"}")) 
		            .andExpect(status().isBadRequest())
		            .andExpect(jsonPath("$.message").value("Size should not exceed 5 and not less than 1"));	}

	//to test pagination
	@Test
	public void testPaginationLogic() {
		User u = new User();
		List<User> allUsers = Arrays.asList(u, u, u, u, u);
		Page<User> mockPage = new PageImpl<>(allUsers);
		when(userRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
		List<User> result = userServiceImpl.fetchUsersFromDb(3, 1, SortType.NAME, SortOrder.EVEN);
		List<User> expectedSublist = Arrays.asList(u, u, u);
		expectedSublist = userSortService.sortDbUsers(allUsers, SortType.NAME, SortOrder.EVEN);
		assertEquals(expectedSublist, result);
	}

	//to test sorting
	@Test
	public void testSortLogic() throws Exception {
	    User u = new User();
	    List<User> allUsers = Arrays.asList(u, u, u);
	    Page<User> mockPage = new PageImpl<>(allUsers);
	    when(userRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
	    when(userSortService.sortDbUsers(anyList(), any(SortType.class), any(SortOrder.class)))
	            .thenReturn(allUsers);
	    mockMvc.perform(get("/users").param("limit", "3").param("offset", "0").param("sortType", "Age")
	            .param("sortOrder", "ODD")).andExpect(status().isOk());

	}


}
