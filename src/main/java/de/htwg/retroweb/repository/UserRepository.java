/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.htwg.retroweb.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByName(String name);
	
	@Transactional
	@Modifying
	@Query("update User u set u.name = ?1, u.email = ?2, u.admin = ?3, u.updated = ?4 where u.id = ?5")
	int updateWithoutPassword(String name, String email, boolean isAdmin, Date updated, long id);
	
	@Transactional
	@Modifying
	@Query("update User u set u.name = ?1, u.password = ?2, u.email = ?3, u.admin = ?4, u.updated = ?5 where u.id = ?6")
	int update(String name, String password, String email, boolean isAdmin, Date updated, long id);	
}