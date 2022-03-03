package de.htwg.retroweb.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.htwg.retroweb.entities.User;
import de.htwg.retroweb.exception.LoginFailedException;
import de.htwg.retroweb.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
	
	@Mock
	private Encryptable encryptionService;
	@Mock
	private UserRepository userRepo;
	@InjectMocks
	private UserServiceImpl userService;//Achtung hier Implementierung
	
	private List<User> users = null;
    
	@BeforeEach
    public void setUp() {
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
	
	@Test
	public void testCheckLoginFailedUserNotFound() throws LoginFailedException {
		when(userRepo.findByName("XXX")).thenReturn(users);
		assertThrows(LoginFailedException.class, () -> {userService.checkLogin("XXX", "XXX");});
	}
	
	@Test
	public void testCheckLoginFailedPasswordWrong() throws LoginFailedException {
		User user = new User();
		user.setAdmin(false);
		user.setName("test");
		user.setPassword("test");
		user.setId((long) 1); 
		users.add(user);
		
		when(userRepo.findByName("test")).thenReturn(users);
		when(encryptionService.passwordMatches("test", "test")).thenReturn(true);
		assertThrows(LoginFailedException.class, () -> {userService.checkLogin("test", "test1");});
		
	}
}