/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.htwg.retroweb.entities.Project;
import de.htwg.retroweb.exception.ResourceNotFoundException;
import de.htwg.retroweb.service.ProjectService;
import de.htwg.retroweb.service.SessionService;

@RestController
public class ProjectRestController {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProjectRestController.class);
    private ProjectService projectService;
    private SessionService sessionService;
    
    @Autowired
    public ProjectRestController(ProjectService projectService, SessionService sessionService) {
    	this.projectService = projectService;
    	this.sessionService = sessionService;
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getAllProjects(HttpServletRequest request, HttpServletResponse response) { //Map<String, Object> model,
        LOG.debug("--> getAllProjects");
        HttpSession session = request.getSession();
        if(sessionService.isLoggedIn(session)) { //this is not compliant to REST, sessions are not allowed, but we do not use an authorization server here!!!
        	response.setHeader("Cache-Control", "no-cache");
        	LOG.debug("<-- getAllProjects");
        	return projectService.getByUserId(sessionService.getUserId(session)); //ArrayList implements Serializable, therefore this will be converted to JSON, automatically
        } else {
        	response.setStatus(401); //http status unauthorized
        	LOG.debug("<-- getAllProjects");
        	return new ArrayList<>(); //return empty List
        }
    }
    
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project getProjectById(@PathVariable(value = "id") Long projectId, HttpServletRequest request, HttpServletResponse response) {
    	response.setHeader("Cache-Control", "no-cache");
    	LOG.debug("--> getProjectById");
        HttpSession session = request.getSession();
        if(sessionService.isLoggedIn(session)) {
        	LOG.debug("<-- getProjectById");
			try {
				return projectService.getById(projectId);
			} catch (ResourceNotFoundException e) {
				LOG.debug("getProjectById",e);
				response.setStatus(404);
				return null;
			}
        	
        } else {
        	response.setStatus(401); //http status unauthorized
        	LOG.debug("<-- getProjectById");
        	return null; //return empty project
        }
    }
}
