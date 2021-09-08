/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.controller;

import de.htwg.retroweb.entities.User;
import de.htwg.retroweb.exception.ResourceAlreadyExistsException;
import de.htwg.retroweb.exception.ResourceNotFoundException;
import de.htwg.retroweb.service.SessionService;
import de.htwg.retroweb.service.UserService;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private SessionService sessionService;
    
    @Autowired
    public UserController(UserService userService, SessionService sessionService) {
    	this.userService = userService;
    	this.sessionService = sessionService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String showUser(Map<String, Object> model, HttpServletRequest request) {
        LOG.debug("--> user");
        HttpSession session = request.getSession();
        String userName = sessionService.getUserName(session);
        String page = checkLoginAndAdmin(session, "user");
        
        if("user".equals(page)) {
        	List<User> users = userService.listAll();
        	model.put("users", users);
        	model.put("userName", userName);
        	model.put(ControllerConstants.ERROR_MSG, session.getAttribute(ControllerConstants.ERROR_MSG));
        	session.removeAttribute(ControllerConstants.ERROR_MSG);
        }
        return page;
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
    public String newUser(Map<String, Object> model, HttpServletRequest request) {
    	LOG.debug("--> newUser");
    	
        return checkLoginAndAdmin(request.getSession(), "newuser");
    }
    
    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String saveNewUser(@RequestParam String name, @RequestParam String password, @RequestParam String email, @RequestParam(defaultValue = "false") boolean admin, Map<String, Object> model, HttpServletRequest request) {
        LOG.debug("--> saveNewUser");
        HttpSession session = request.getSession();
        String page = checkLoginAndAdmin(session, ControllerConstants.REDIRECT_USER);
        
        if(ControllerConstants.REDIRECT_USER.equals(page)) { 
        	User user = new User();
        	user.setName(name);
        	user.setPassword(password);
        	user.setEmail(email);
        	user.setAdmin(admin);
        
        	try {
        		userService.save(user);
        	} catch (ResourceAlreadyExistsException e) {
        		session.setAttribute(ControllerConstants.ERROR_MSG, e.getMessage());
        		LOG.error("UserController->saveNewUser", e);
        	}
        }
    	return page;
    }
    
    @RequestMapping(value = "/updateuser", method = RequestMethod.GET)
    public String updateUserShow(@RequestParam long id, Map<String, Object> model, HttpServletRequest request) {
    	String page = checkLoginAndAdmin(request.getSession(),"updateuser");
    	if("updateuser".equals(page)) {
    		try {
    			User user = userService.getById(id);
    			if(model.containsKey("user")) {
    				model.replace("user", user);
    			} else {
    				model.put("user", user);
    			}
    		} catch(ResourceNotFoundException e) {
    			LOG.error("UserController->updateUserShow", e);
    			model.put(ControllerConstants.ERROR_MSG, e.getMessage());
    		}
    	}
    	return page;
    }
    
    
    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String updateUser(@RequestParam long id, @RequestParam String name, @RequestParam String password, @RequestParam String email, @RequestParam(defaultValue = "false") boolean admin, Map<String, Object> model, HttpServletRequest request) {
    	LOG.debug("--> updateUser");
    	HttpSession session = request.getSession();
    	String page = checkLoginAndAdmin(session,ControllerConstants.REDIRECT_USER);
    	
    	if(ControllerConstants.REDIRECT_USER.equals(page)) {
    		User user = new User();
    		user.setId(id);
    		user.setName(name);
    		user.setPassword(password);
    		user.setAdmin(admin);
    		user.setEmail(email);

    		try {
    			if("".equals(user.getPassword())) {
    				userService.updateWithoutPassword(user);
    			} else {
    				userService.update(user);
    			}
    		} catch(ResourceNotFoundException e) {
    			LOG.error("UserController->updateUser", e);
    			session.setAttribute(ControllerConstants.ERROR_MSG, e.getMessage());
    		}
    	}
    	return page;
    }

    private String checkLoginAndAdmin(HttpSession session, String returnPage) {
    	String page = returnPage;
    	
    	if(sessionService.isLoggedIn(session)) {
        	if(!sessionService.isAdmin(session)) {
         		page = ControllerConstants.REDIRECT_HOME;
        	}
        } else {
        	page = ControllerConstants.REDIRECT_LOGIN;  	
        }
    	LOG.debug("<-- {}", page);
        return page;
    }
}
