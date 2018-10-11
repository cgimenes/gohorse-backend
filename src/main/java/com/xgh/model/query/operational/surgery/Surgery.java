package com.xgh.model.query.operational.surgery;

import com.xgh.model.query.operational.animal.Animal;
import com.xgh.model.query.operational.appointment.Appointment;
import com.xgh.model.query.operational.veterinary.Veterinary;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "surgery")
public final class Surgery {
	@Id
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;
	
	@ManyToOne
	@JoinColumn(name = "veterinary_id")
	private Veterinary veterinary;

	@ManyToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;
	
	@JsonIgnore
    private Boolean deleted = false;
	
	private LocalDateTime dateTime;
	private String surgeryType;
	private String notes;
	private String status;
	
	protected Surgery() {
		
	}
	
	public Surgery(UUID id, Animal animal, Veterinary veterinary, 
			Appointment appointment, Boolean deleted, LocalDateTime dateTime, 
			String surgeryType, String notes, String status) {
		super();
		this.id = id;
		this.animal = animal;
		this.veterinary = veterinary;
		this.appointment = appointment;
		this.deleted = deleted;
		this.dateTime = dateTime;
		this.surgeryType = surgeryType;
		this.notes = notes;
		this.status = status;

	}

	public UUID getId() {
		return id;
	}

	public Animal getAnimal() {
		return animal;
	}

	public Veterinary getVeterinary() {
		return veterinary;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getSurgeryType() {
		return surgeryType;
	}

	public String getNotes() {
		return notes;
	}

	public String getStatus() {
		return status;
	}
	
}
