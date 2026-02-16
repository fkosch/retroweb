package de.htwg.retroweb.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.*;

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

	@Column(name = "created", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime created;

	@Column(name = "updated", nullable = false)
	@LastModifiedDate
	private LocalDateTime updated;

	public LocalDateTime getCreated() {
		return created;
	}
	
	public String getFormattedcreated() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") ;
		return created.format(df);
	}
	
	public void setCreated(LocalDateTime created) {
		if(this.created == null) {
			this.created = created;
		}
		if(this.updated == null) {
			this.updated = this.created;
		}
	}

	public LocalDateTime getUpdated() {
		return updated;
	}
	
	public String getFormattedupdated() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") ;
		return updated.format(df);
	}
	
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
}

