/**
 * Copyright (c) 2023 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.entities;


import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "projects")
public class Project extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8470435536181413339L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = 0L;
	
	@Column(name = "name", nullable = false)
	@Size(max = 255)
	private String name;
	
	@Column(name = "isactive", nullable = false)
	private boolean active;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
    		name = "projects_users", 
    		joinColumns = @JoinColumn(name = "project_id", nullable = false, updatable = true, insertable = true ), 
    		inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = true, insertable = true ))//, updatable = true, insertable = true
	@JsonIgnore //besser fuer REST, z.B. PUT Retro
	private Set<User> users = new HashSet<>();
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public Set<User> getUsers() {
		return users;
	}

	
	@Override
	public boolean equals(Object o) {
		if( o == null) {
			return false;
		}
		if(o.getClass().equals(getClass())) {
			Project p = (Project) o;
			return p.getName().equals(getName()); //name is unique, do not use id, id is null when not saved, after saving id is a number, -> hashcode would be changed
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() { //always override hashCode, when overriding equals!!!
		return new HashCodeBuilder(19, 37). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(getName()).toHashCode();
	}
}
