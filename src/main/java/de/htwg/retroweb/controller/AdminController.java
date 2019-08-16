/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import de.htwg.retroweb.service.SessionService;

@Controller
public class AdminController {
	
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    SessionService sessionService;
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Map<String, Object> model, HttpServletRequest request) {
    	
    	LOG.debug("--> admin");
    	HttpSession session = request.getSession();
    	
    	String user = sessionService.getUserName(session);
    	boolean isAdmin = sessionService.isAdmin(session);
    	        
        if (!sessionService.isLoggedIn(session)) {
            LOG.debug("<-- admin, redirect:/login");
            return ControllerConstants.REDIRECT_LOGIN;
        } else if(!isAdmin) {
            LOG.debug("<-- admin, redirect:/home");
            return ControllerConstants.REDIRECT_HOME;        	
        }
        
        model.put("userName", user);
        model.put("isAdmin", isAdmin);
        LOG.debug("<-- admin");
        return ControllerConstants.ADMIN;
    }

}
