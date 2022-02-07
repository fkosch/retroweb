/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.htwg.retroweb.service.SessionService;

@Controller
public class HomeController {
	
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    SessionService sessionService;
      
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
    	
    	LOG.debug("--> home");
    	
    	HttpSession session = request.getSession();
    	Cookie foo = new Cookie("foo", "bar");
    	foo.setMaxAge(3000);
    	foo.setHttpOnly(true);
    	foo.setSecure(true);
    	response.addCookie(foo);
    	String page = ControllerConstants.HOME;
    	
        if (sessionService.isLoggedIn(session)) {
            model.put("userName", sessionService.getUserName(session));
        	model.put("isAdmin", sessionService.isAdmin(session));
            LOG.debug("<-- home");
        } else {
            LOG.debug("<-- login");
            page = ControllerConstants.REDIRECT_LOGIN;
        }
        return page;
    }

}
