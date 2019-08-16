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
import org.springframework.transaction.annotation.Transactional;

import de.htwg.retroweb.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	List<Project> findByName(String name);
	List<Project> findByUsersId(long id);
	
	@Transactional
	@Modifying
	@Query("update Project p set p.name = ?1, p.active = ?2, p.updated = ?3 where p.id = ?4")
	int update(String name, boolean isActive, Date updated, long id);	
}
