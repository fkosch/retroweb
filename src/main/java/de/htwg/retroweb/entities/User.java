/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8284859716864340744L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "username", nullable = false)
	@Size(max = 255)
	private String name;
	
	@Column(name = "userpw", nullable = false)
	@Size(max = 255)
	private String password;
	
	@Column(name = "email", nullable = false)
	@Size(max = 255)
	private String email;
	
	@Column(name = "isadmin", nullable = false)
	private boolean admin;

    @ManyToMany(mappedBy = "users") //inverse many-to-many (not owner)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference //important to avoid infinity loop when serializing with json, see also Projects.java
    private Set<Project> projects = new HashSet<>();

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public Set<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	@Override
	public boolean equals(Object o) {
		if ( o == null) {
			return false;
		} else if (o.getClass().equals(getClass())) {
			User u = (User) o;
			return u.getName().equals(getName()); //name is unique, do not use id, id is null when not saved, after saving id is a number, -> hashcode would be changed
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() { //always override hashCode, when overriding equals!!!
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
	            // if deriving: appendSuper(super.hashCode()).
	            append(getName()).toHashCode();
	}
	
}
