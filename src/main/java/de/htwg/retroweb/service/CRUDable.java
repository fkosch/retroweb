package de.htwg.retroweb.service;

import java.util.List;

import de.htwg.retroweb.exception.ResourceAlreadyExistsException;
import de.htwg.retroweb.exception.ResourceNotFoundException;

public interface CRUDable<T> {
	List<T> listAll();
	
	T getById(Long id) throws ResourceNotFoundException;
	
	T save(T domainObject) throws ResourceAlreadyExistsException;

	T update(T domainObject) throws ResourceNotFoundException;
	
	void delete(Long id) throws ResourceNotFoundException;
}
