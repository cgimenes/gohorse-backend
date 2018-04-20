package com.xgh.model.query.internment;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.query.bed.Bed;

@Entity
@Table(name = "internment")
public class Internment {

	@Id
	@Column(name = "id")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "bed_id")
	private Bed bed;

	@Column(name = "animal_id")
	private UUID animalId;

	@Column(name = "busy_at")
	private Date busyAt;

	@Column(name = "busy_until")
	private Date busyUntil;

	@JsonIgnore
	@Column(name = "deleted")
	private Boolean deleted = false;

	public Internment() {

	}

	public Internment(UUID id, Bed bed, UUID animalId, Date busyAt, Date busyUntil, Boolean deleted) {
		this.id = id;
		this.bed = bed;
		this.animalId = animalId;
		this.busyAt = busyAt;
		this.busyUntil = busyUntil;
		this.deleted = deleted;
	}

	public UUID getId() {
		return id;
	}

	public Bed getBed() {
		return bed;
	}
	
	public UUID getAnimalId() {
		return animalId;
	}

	public Date getBusyAt() {
		return busyAt;
	}

	public Date getBusyUntil() {
		return busyUntil;
	}

	public Boolean isDeleted() {
		return deleted;
	}

}
