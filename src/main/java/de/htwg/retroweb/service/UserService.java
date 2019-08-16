package de.htwg.retroweb.service;

import java.util.List;

import de.htwg.retroweb.entities.User;
import de.htwg.retroweb.exception.LoginFailedException;
import de.htwg.retroweb.exception.ResourceNotFoundException;

public interface UserService extends CRUDable<User> {

	public User checkLogin(String name, String password) throws LoginFailedException;
	public void updateWithoutPassword(User domainObject) throws ResourceNotFoundException;
	public List<User> getByName(String name);
	
}
