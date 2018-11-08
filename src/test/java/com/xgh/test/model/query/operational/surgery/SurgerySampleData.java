package com.xgh.test.model.query.operational.surgery;

import com.xgh.model.query.operational.surgery.Surgery;
import com.xgh.model.query.operational.surgery.SurgeryRepository;
import com.xgh.model.query.operational.veterinary.Veterinary;
import com.xgh.test.model.query.operational.animal.AnimalSampleData;
import com.xgh.test.model.query.operational.appointment.AppointmentSampleData;
import com.xgh.test.model.query.operational.veterinary.VeterinarySampleData;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QuerySurgerySampleData")
public class SurgerySampleData {
	private final SurgeryRepository repository;
	private final AnimalSampleData animalSampleData;
	private final VeterinarySampleData veterinarySampleData;
	private final AppointmentSampleData appointmentSampleData;

	@Autowired
	protected SurgerySampleData(SurgeryRepository repository, AnimalSampleData animalSampleData,
			VeterinarySampleData veterinarySampleData, AppointmentSampleData appointmentSampleData) {
		this.repository = repository;
		this.animalSampleData = animalSampleData;
		this.veterinarySampleData = veterinarySampleData;
		this.appointmentSampleData = appointmentSampleData;
	}

	public Surgery getSample() {
		Veterinary veterinary = veterinarySampleData.getSample();

		Surgery entity = new Surgery(UUID.randomUUID(), animalSampleData.getSample(), veterinary,
				appointmentSampleData.getSample(), false, LocalDateTime.of(2018, 10, 10, 10, 10, 10), "Ortopédica",
				null, "SCHEDULED");
		repository.save(entity);
		return entity;
	}
}
