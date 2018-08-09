package com.xgh.test.model.command.appointment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.appointment.Appointment;
import com.xgh.model.command.appointment.AppointmentId;
import com.xgh.model.command.appointment.AppointmentPlace;
import com.xgh.model.command.appointment.AppointmentStatus;
import com.xgh.model.command.appointment.AppointmentType;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.veterinary.VeterinaryId;
import com.xgh.test.model.command.animal.AnimalSampleData;
import com.xgh.test.model.command.valueobjects.AddressSampleData;
import com.xgh.test.model.command.veterinary.VeterinarySampleData;
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
public class AppointmentCommandControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Autowired
    private AnimalSampleData animalSampleData;

    @Autowired
    private AppointmentSampleData appointmentSampleData;

    @Autowired
    private VeterinarySampleData veterinarySampleData;

    @Autowired
    private AddressSampleData addressSampleData;

    @Test
    public void registerWithSuccess() {
        Appointment entity = new Appointment();

        AnimalId animalId = animalSampleData.getSample().getId();
        VeterinaryId veterinaryId = veterinarySampleData.getSample().getId();
        LocalDateTime dateTime = LocalDateTime.now();

        entity.register(new AppointmentId(),
                animalId,
                veterinaryId,
                dateTime,
                AppointmentType.FIRST,
                AppointmentPlace.CLINIC,
                null);

        ResponseEntity<Void> response = restTemplate.postForEntity("/appointments", entity, Void.class);

        Appointment entityFromStore = eventStore.pull(Appointment.class, entity.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/appointments/" + entity.getId(), response.getHeaders().getLocation().getPath());
        assertTrue(entity.equals(entityFromStore));
        assertEquals(animalId, entityFromStore.getAnimal());
        assertEquals(veterinaryId, entityFromStore.getVeterinary());
        assertEquals(dateTime, entityFromStore.getDateTime());
        assertEquals(AppointmentType.FIRST, entityFromStore.getAppointmentType());
        assertEquals(AppointmentPlace.CLINIC, entityFromStore.getPlace());
        assertEquals(AppointmentStatus.SCHEDULED, entityFromStore.getStatus());
        assertNull(entityFromStore.getAddress());
        assertEquals("1", entityFromStore.getVersion().toString());
    }

    @Test
    public void updateWithSuccess() {
        Appointment entity = appointmentSampleData.getSample();

        Address address = addressSampleData.getSample();

        entity.update(veterinarySampleData.getSample().getId(),
                LocalDateTime.now(),
                AppointmentPlace.OTHER,
                address);

        RequestEntity<Appointment> request = RequestEntity.put(URI.create("/appointments")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Appointment entityFromStore = eventStore.pull(Appointment.class, entity.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals(AppointmentPlace.OTHER, entityFromStore.getPlace());
        assertEquals(address.getNumber(), entityFromStore.getAddress().getNumber());
        assertEquals("2", entityFromStore.getVersion().toString());
    }

    @Test
    public void deleteWithSuccess() {
        Appointment entity = appointmentSampleData.getSample();

        HttpEntity<Appointment> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/appointments"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Appointment entityFromStore = eventStore.pull(Appointment.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }

    @Test
    public void cancelWithSuccess() {
        Appointment entity = appointmentSampleData.getSample();

        HttpEntity<Appointment> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/appointments/cancel"), HttpMethod.PUT,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Appointment entityFromStore = eventStore.pull(Appointment.class, entity.getId());

        assertEquals(AppointmentStatus.CANCELLED, entityFromStore.getStatus());
    }

    @Test
    public void finishWithSuccess() {
        Appointment entity = appointmentSampleData.getSample();

        HttpEntity<Appointment> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/appointments/finish"), HttpMethod.PUT,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Appointment entityFromStore = eventStore.pull(Appointment.class, entity.getId());

        assertEquals(AppointmentStatus.FINISHED, entityFromStore.getStatus());
    }
}
