/**
 * Copyright (c) 2020 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.controller;

import de.htwg.retroweb.entities.User;
import de.htwg.retroweb.exception.LoginFailedException;
import de.htwg.retroweb.service.SessionService;
import de.htwg.retroweb.service.UserService;

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
public class LoginController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoginController.class);

    UserService userService;
    SessionService sessionService;
    
    @Autowired
    public LoginController(UserService userService, SessionService sessionService) {
    	this.userService = userService;
    	this.sessionService = sessionService;
    }

    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Map<String, Object> model) {

        LOG.debug("--> {}", ControllerConstants.LOGIN);
        //Kommentar3
        //Kommentar1
        LOG.debug("<-- {}", ControllerConstants.LOGIN);
        return ControllerConstants.LOGIN;
    }
    
    /**
     * ControllerConstants.LOGIN controller 
     * @param username
     * @param password
     * @param model
     * @param request
     * @return 
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginCheck(@RequestParam String username, @RequestParam String password, Map<String, Object> model, HttpServletRequest request) {

        LOG.debug("--> loginCheck username={}", username);
        String page = null;
        HttpSession session = request.getSession();
        
        try {
        	User user = userService.checkLogin(username, password);
        	sessionService.addSession(session, user);
            page = ControllerConstants.REDIRECT_HOME;
        } catch(LoginFailedException e) {  
        	sessionService.removeSession(session);
            String msg = "Please check your input.";
            model.put("msg", msg);
            page = ControllerConstants.LOGIN;
        }
        
        LOG.debug("<-- loginCheck");
        return page;
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Map<String, Object> model, HttpServletRequest request) {
    	
        LOG.debug("--> logout");
        
        sessionService.removeSession(request.getSession());
        
        String msg = "You are logged out.";
        model.put("msg", msg);
        
        LOG.debug("<-- logout");
        return ControllerConstants.LOGIN;
    }
}
