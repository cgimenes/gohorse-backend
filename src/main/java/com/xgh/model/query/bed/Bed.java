package com.xgh.model.query.bed;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bed")
public final class Bed {

	@Id
	private UUID id;
	
	private String code;
	
	private Boolean busy;
	
	@JsonIgnore
	private Boolean deleted = false;
	
	protected Bed() {}
	
	public Bed (UUID id, String code, Boolean busy, Boolean deleted) {
		this.id = id;
		this.code = code;
		this.busy = busy;
		this.deleted = deleted;
	}

	public UUID getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public Boolean getBusy() {
		return busy;
	}

	public Boolean isDeleted() {
		return deleted;
	}
	
	
}
