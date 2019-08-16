package de.htwg.retroweb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

	@MappedSuperclass
	@EntityListeners(AuditingEntityListener.class)
	
public abstract class AbstractEntity implements Serializable {
	
	/**
	* 
	*/
	private static final long serialVersionUID = 5994288598031975413L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, updatable = false)
	@CreatedDate
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false)
	@LastModifiedDate
	private Date updated;

	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		if(this.created == null) {
			this.created = created;
		}
		if(this.updated == null) {
			this.updated = this.created;
		}
	}

	public Date getUpdated() {
		return updated;
	}
	
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}

