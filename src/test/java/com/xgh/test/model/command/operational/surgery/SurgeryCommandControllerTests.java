package com.xgh.test.model.command.operational.surgery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.appointment.AppointmentId;
import com.xgh.model.command.operational.surgery.*;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import com.xgh.test.model.command.operational.animal.AnimalSampleData;
import com.xgh.test.model.command.operational.appointment.AppointmentSampleData;
import com.xgh.test.model.command.operational.veterinary.VeterinarySampleData;
import java.net.URI;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class SurgeryCommandControllerTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PostgresEventStore eventStore;

	@Autowired
	private AnimalSampleData animalSampleData;

	@Autowired
	private AppointmentSampleData appointmentSampleData;

	@Autowired
	private SurgerySampleData surgerySampleData;

	@Autowired
	private VeterinarySampleData veterinarySampleData;

	@Test
	public void registerWithSuccess() {
		Surgery entity = new Surgery();
		AnimalId animalId = animalSampleData.getSample().getId();
		LocalDateTime dateTime = LocalDateTime.now();
		AppointmentId appointmentId = appointmentSampleData.getSample().getId();
		VeterinaryId veterinary = veterinarySampleData.getSample().getId();

		entity.register(new SurgeryId(), animalId, veterinary, dateTime, "Ortopédica", null, appointmentId);

		ResponseEntity<Void> response = restTemplate.postForEntity("/surgeries", entity, Void.class);

		Surgery entityFromStore = eventStore.pull(Surgery.class, entity.getId());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("/surgeries/" + entity.getId(), response.getHeaders().getLocation().getPath());
		assertTrue(entity.equals(entityFromStore));
		assertEquals(animalId, entityFromStore.getAnimal());
		assertEquals(veterinary, entityFromStore.getVeterinary());
		assertEquals(dateTime, entityFromStore.getDateTime());
		assertEquals(SurgeryStatus.SCHEDULED, entityFromStore.getStatus());
		assertEquals("1", entityFromStore.getVersion().toString());
	}

	@Test
	public void updateWithSuccess() {
		Surgery entity = surgerySampleData.getSample();
		VeterinaryId veterinary = veterinarySampleData.getSample().getId();

		entity.update(veterinary, LocalDateTime.now(), "Castração", null);

		RequestEntity<Surgery> request = RequestEntity.put(URI.create("/surgeries")).body(entity);
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		Surgery entityFromStore = eventStore.pull(Surgery.class, entity.getId());

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertTrue(entity.equals(entityFromStore));
		assertEquals("2", entityFromStore.getVersion().toString());
	}

	@Test
	public void deleteWithSuccess() {
		Surgery entity = surgerySampleData.getSample();

		HttpEntity<Surgery> requestEntity = new HttpEntity<>(entity);

		ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/surgeries"), HttpMethod.DELETE,
				requestEntity, Void.class);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		Surgery entityFromStore = eventStore.pull(Surgery.class, entity.getId());

		assertTrue(entityFromStore.isDeleted());
	}

	@Test
	public void cancelWithSuccess() {
		Surgery entity = surgerySampleData.getSample();

		HttpEntity<Surgery> requestEntity = new HttpEntity<>(entity);

		ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/surgeries/cancel"), HttpMethod.PUT,
				requestEntity, Void.class);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		Surgery entityFromStore = eventStore.pull(Surgery.class, entity.getId());

		assertEquals(SurgeryStatus.CANCELLED, entityFromStore.getStatus());
	}

	@Test
	public void finishWithSuccess() {
		Surgery entity = surgerySampleData.getSample();

		HttpEntity<Surgery> requestEntity = new HttpEntity<>(entity);

		ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/surgeries/finish"), HttpMethod.PUT,
				requestEntity, Void.class);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		Surgery entityFromStore = eventStore.pull(Surgery.class, entity.getId());

		assertEquals(SurgeryStatus.FINISHED, entityFromStore.getStatus());
	}

	@Test
	public void registerConflicted() {
		Surgery firstEntity = surgerySampleData.getSample();
		Surgery entity = new Surgery();
		AnimalId animalId = animalSampleData.getSample().getId();
		AppointmentId appointmentId = appointmentSampleData.getSample().getId();
		VeterinaryId veterinary = veterinarySampleData.getSample().getId();

		entity.register(new SurgeryId(), animalId, veterinary, firstEntity.getDateTime(), "Ortopédica", null,
				appointmentId);

		ResponseEntity<Void> response = restTemplate.postForEntity("/surgeries", entity, Void.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
