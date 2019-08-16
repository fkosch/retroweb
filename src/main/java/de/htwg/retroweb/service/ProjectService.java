/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.service;

import java.util.List;

import de.htwg.retroweb.entities.Project;

public interface ProjectService extends CRUDable<Project> {
	
    public List<Project> getByName(String name);
    public List<Project> getByUserId(long id);

}
