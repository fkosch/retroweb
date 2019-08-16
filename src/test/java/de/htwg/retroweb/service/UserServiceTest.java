package de.htwg.retroweb.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import de.htwg.retroweb.entities.User;
import de.htwg.retroweb.exception.LoginFailedException;
import de.htwg.retroweb.repository.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	@MockBean
	private Encryptable encryptionService;
	
	private UserService userService;
	private List<User> users = null;
	private UserRepository userRepo;

    @Before
    public void setUp() {
        userRepo = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepo, encryptionService);
		users = new ArrayList<User>();
    }

	@Test
	public void testCheckLoginPassed() throws LoginFailedException {
		User user = new User();
		user.setAdmin(false);
		user.setName("test");
		user.setPassword("test");
		user.setId((long) 1); 
		users.add(user);
		
		when(userRepo.findByName("test")).thenReturn(users);
		when(encryptionService.passwordMatches("test", "test")).thenReturn(true);
		User testUser = userService.checkLogin("test", "test");
		assertEquals(false, testUser.isAdmin());
		assertEquals(1, testUser.getId());
		assertEquals("test", testUser.getName());
	}
	
	@Test
	public void testCheckLoginAdminPassed() throws LoginFailedException {
		User user = new User();
		user.setAdmin(true);
		user.setName("admin");
		user.setPassword("admin");
		user.setId(1);
		users.add(user);
		
		when(userRepo.findByName("admin")).thenReturn(users);
		when(encryptionService.passwordMatches("admin", "admin")).thenReturn(true);
		User testUser = userService.checkLogin("admin", "admin");
		assertEquals(true, testUser.isAdmin());
		assertEquals(1, testUser.getId());
		assertEquals("admin", testUser.getName());
	}	
	
	@Test(expected = LoginFailedException.class)
	public void testCheckLoginFailedUserNotFound() throws LoginFailedException {
		when(userRepo.findByName("XXX")).thenReturn(users);
		userService.checkLogin("XXX", "XXX");
	}
	
	@Test(expected = LoginFailedException.class)
	public void testCheckLoginFailedPasswordWrong() throws LoginFailedException {
		User user = new User();
		user.setAdmin(false);
		user.setName("test");
		user.setPassword("test");
		user.setId((long) 1); 
		users.add(user);
		
		when(userRepo.findByName("test")).thenReturn(users);
		when(encryptionService.passwordMatches("test", "test")).thenReturn(true);
		userService.checkLogin("test", "test1");
	}
}
