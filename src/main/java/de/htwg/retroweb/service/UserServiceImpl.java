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

import de.htwg.retroweb.entities.User;
import de.htwg.retroweb.exception.LoginFailedException;
import de.htwg.retroweb.exception.ResourceAlreadyExistsException;
import de.htwg.retroweb.exception.ResourceNotFoundException;
import de.htwg.retroweb.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userRepository;
	private Encryptable encryptionService;
	
    @Autowired
	public UserServiceImpl(UserRepository userRepository, Encryptable encryptionService) {
		this.userRepository = userRepository;
		this.encryptionService = encryptionService;
	}

	
	/**
     * method to check login 
     * @param name
     * @param password
     * @return boolean
	 * @throws LoginFailedException 
	 **/
    @Override
    public User checkLogin(String name, String password) throws LoginFailedException {
    	LOG.debug("--> checkLogin");

    	List<User> users = getByName(name);
    	User user = null;

    	if( users.size() == 1 ) {
    		user = users.get(0);
    	}

    	if (user != null && encryptionService.passwordMatches(password, user.getPassword())){
    		LOG.debug("<-- checkLogin, user is logged in");
    		return user;
    	} else {
    		LOG.debug("<-- checkLogin, LoginFailedException");
    		throw new LoginFailedException("User", "name", null);
    	}
    }

    @Override
    public List<User> listAll() {
    	LOG.debug("listAll");
    	return userRepository.findAll();
    }

    @Override
    public User getById(Long id) throws ResourceNotFoundException {
    	return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User save(User domainObject) throws ResourceAlreadyExistsException {
    	LOG.debug("--> save");
    	List<User> users = userRepository.findByName(domainObject.getName());
    	if(users.isEmpty()) {
    		domainObject.setCreated(new Date());
    		userRepository.save(encryptPassword(domainObject));
    	} else {
    		LOG.debug("<-- save, ResourceAlreadyExistsException");
    		throw new ResourceAlreadyExistsException("User", "name", domainObject.getName());
    	}
    	LOG.debug("<-- save");
    	return domainObject;
    }

    @Override
    public User update(User domainObject) throws ResourceNotFoundException {
    	LOG.debug("--> update");
    	
    	User user = encryptPassword(domainObject);
    	user.setUpdated(new Date());
    	int result = userRepository.update(user.getName(), user.getPassword(), user.getEmail(), user.isAdmin(), user.getUpdated(), user.getId());
    	
    	if(result < 1) {
    		LOG.debug("<-- update, ResourceNotFoundException");
    		throw new ResourceNotFoundException("User", "id", domainObject.getId());
    	}
    		LOG.debug("<-- update");
    		return user;

    }
    
    @Override
    public void updateWithoutPassword(User domainObject) throws ResourceNotFoundException {
    	int result;
    	LOG.debug("updateAdmin -->");
    	result = userRepository.updateWithoutPassword(domainObject.getName(), domainObject.getEmail(), domainObject.isAdmin(), new Date() , domainObject.getId());
    	
    	if(result < 1) {
    		LOG.debug("<-- updateAdmin, ResourceNotFoundException");
    		throw new ResourceNotFoundException("User", "id", domainObject.getId());
    	}
    	LOG.debug("<-- updateAdmin");
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
    	LOG.debug("--> delete");
    	User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    	userRepository.delete(user);
    	LOG.debug("<-- delete");
    }

    @Override
    public List<User> getByName(String name) {
    	LOG.debug("getByName");
    	return userRepository.findByName(name);
    }

    private User encryptPassword(User user) {
    	String encryptedPassword = encryptionService.encryptPassword(user.getPassword());
    	user.setPassword(encryptedPassword);
    	return user;
    }
}