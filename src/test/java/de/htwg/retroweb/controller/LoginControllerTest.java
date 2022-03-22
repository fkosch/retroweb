package de.htwg.retroweb.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import de.htwg.retroweb.exception.LoginFailedException;
import de.htwg.retroweb.service.SessionService;
import de.htwg.retroweb.service.UserService;

/**
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc
public class LoginControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private SessionService sessionService;
	
	
	/**
	 * Test method for {@link de.htwg.retroweb.controller.LoginController#login()}.
	 * @throws Exception 
	 */
	@Test
	public void testLogin() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/login"))
        .andExpect(status().isOk())
        .andExpect(view().name("login"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));
	}

	/**
	 * Test method for {@link de.htwg.retroweb.controller.LoginController#loginCheck(java.lang.String, java.lang.String, java.util.Map, javax.servlet.http.HttpServletRequest)}.
	 * @throws Exception 
	 */
	@Test
	public void testLoginCheckFailed() throws Exception {
		
		when(userService.checkLogin("username", "password")).thenThrow(new LoginFailedException("User", "name", null));
		
		mvc.perform(MockMvcRequestBuilders.post("/login").param("username", "username").param("password", "password"))
        .andExpect(status().isOk())
        .andExpect(view().name("login"))
        .andExpect(model().attribute("msg", "Please check your input."))
        .andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));
	}
	
	/**
	 * Test method for {@link de.htwg.retroweb.controller.LoginController#loginCheck(java.lang.String, java.lang.String, java.util.Map, javax.servlet.http.HttpServletRequest)}.
	 * @throws Exception 
	 */
	@Test
	public void testLoginCheckPassed() throws Exception {
		
	    when(userService.checkLogin("test", "test")).thenReturn(null);
		
		mvc.perform(MockMvcRequestBuilders.post("/login").param("username", "test").param("password", "test"))
        .andExpect(status().is(302))
        .andExpect(view().name("redirect:/home"));		
	}

	/**
	 * Test method for {@link de.htwg.retroweb.controller.LoginController#logout(java.util.Map, javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void testLogout() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/logout"))
        .andExpect(status().isOk())
        .andExpect(view().name("login"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));
	}
}
