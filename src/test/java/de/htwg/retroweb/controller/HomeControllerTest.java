package de.htwg.retroweb.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import de.htwg.retroweb.service.SessionService;


/**
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

@WebMvcTest(HomeController.class)
@AutoConfigureMockMvc
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private SessionService sessionService;
	
	private MockHttpSession mockSession = new MockHttpSession();
	
	@BeforeAll
	public static void initializeData(){
	}
	
	/**
	 * Test method for {@link de.htwg.retroweb.controller.HomeController#home()}.
	 * @throws Exception 
	 */
	@Test
	public void testHome() throws Exception {
		when(sessionService.isLoggedIn(mockSession)).thenReturn(true);
		when(sessionService.getUserName(mockSession)).thenReturn("test");
		when(sessionService.isAdmin(mockSession)).thenReturn(false);
		
		mvc.perform(MockMvcRequestBuilders.get("/home").session(mockSession))
		.andExpect(cookie().exists("foo"))
		.andExpect(model().size(2))
        .andExpect(model().attribute("userName", "test"))
        .andExpect(model().attribute("isAdmin", false))
		.andExpect(status().isOk())
        .andExpect(view().name("home"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/home.jsp"));
	}
	
	/**
	 * Test method for {@link de.htwg.retroweb.controller.HomeController#home()}.
	 * @throws Exception 
	 */
	@Test
	public void testHomeWithoutLogin() throws Exception {
		when(sessionService.isLoggedIn(mockSession)).thenReturn(false);
		
		mvc.perform(MockMvcRequestBuilders.get("/home").session(mockSession))
		.andExpect(model().size(0))
		.andExpect(status().is(302))
		.andExpect(view().name("redirect:/login"))
		.andExpect(redirectedUrl("/login"));
	}

}