/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.service;

import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.htwg.retroweb.entities.Project;
import de.htwg.retroweb.exception.ResourceAlreadyExistsException;
import de.htwg.retroweb.exception.ResourceNotFoundException;
import de.htwg.retroweb.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static final String PROJECT = "Project";

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectRepository projectRepository;
	
    @Override
    public List<Project> listAll() {
    	LOG.debug("listAll");
    	return projectRepository.findAll();
    }

    @Override
    public Project getById(Long id) throws ResourceNotFoundException {
    	return projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(PROJECT, "id", id));
    }

    @Override
    public Project save(Project domainObject) throws ResourceAlreadyExistsException {
    	LOG.debug("--> save");
    	List<Project> projects = projectRepository.findByName(domainObject.getName());
    	if(projects.isEmpty()) {
    		domainObject.setCreated(new Date());
    		projectRepository.save(domainObject);
    	} else {
    		LOG.debug("<-- save, ResourceAlreadyExistsException");
    		throw new ResourceAlreadyExistsException(PROJECT, "name", domainObject.getName());
    	}
    	LOG.debug("<-- save");
    	return domainObject;
    }

    @Override
    public Project update(Project domainObject) throws ResourceNotFoundException {
    	LOG.debug("--> update");
    	
    	Project project = domainObject;
    	project.setUpdated(new Date());
    	int result = projectRepository.update(project.getName(), project.isActive(), project.getUpdated(), project.getId());
    	
    	if(result < 1) {
    		LOG.debug("<-- update, ResourceNotFoundException");
    		throw new ResourceNotFoundException(PROJECT, "id", domainObject.getId());
    	}
    		LOG.debug("<-- update");
    		return project;

    }
    
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
    	LOG.debug("--> delete");
    	Project project = projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(PROJECT, "id", id));
    	projectRepository.delete(project);
    	LOG.debug("<-- delete");
    }

    @Override
    public List<Project> getByName(String name) {
    	LOG.debug("getByName");
    	return projectRepository.findByName(name);
    }
    
    @Override
    public List<Project> getByUserId(long id) {
    	LOG.debug("getById");
    	return projectRepository.findByUsersId(id);
    }

}