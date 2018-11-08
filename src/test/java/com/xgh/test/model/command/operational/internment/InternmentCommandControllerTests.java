package com.xgh.test.model.command.operational.internment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.bed.BedId;
import com.xgh.model.command.operational.internment.Internment;
import com.xgh.model.command.operational.internment.InternmentId;
import com.xgh.test.model.command.operational.animal.AnimalSampleData;
import com.xgh.test.model.command.operational.bed.BedSampleData;
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
public class InternmentCommandControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Autowired
    private BedSampleData bedSampleData;

    @Autowired
    private AnimalSampleData animalSampleData;

    @Test
    public void registerWithSuccess() {
        BedId bedId = bedSampleData.getSample().getId();
        AnimalId animalId = animalSampleData.getSample().getId();

        Internment entity = new Internment();
        entity.register(new InternmentId(), bedId, animalId, LocalDateTime.of(2018, 10, 10, 10, 10, 10), LocalDateTime.of(2018, 10, 10, 12, 10, 10));

        ResponseEntity<Void> response = restTemplate.postForEntity(URI.create("/internments"), entity, Void.class);

        Internment entityFromStore = eventStore.pull(Internment.class, entity.getId());

        assertTrue(entity.equals(entityFromStore));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(entity.getId(), entityFromStore.getId());
        assertEquals(entity.getBedId(), entityFromStore.getBedId());
        assertEquals(entity.getAnimalId(), entityFromStore.getAnimalId());
        assertEquals(LocalDateTime.of(2018, 10, 10, 10, 10, 10), entityFromStore.getBusyAt());
        assertEquals(LocalDateTime.of(2018, 10, 10, 12, 10, 10), entityFromStore.getBusyUntil());
        assertEquals("/internments/" + entity.getId(), response.getHeaders().getLocation().getPath());
    }

    @Test
    public void updateWithSuccess() {
        BedId bedId = bedSampleData.getSample().getId();
        AnimalId animalId = animalSampleData.getSample().getId();

        Internment entity = new Internment();
        entity.register(new InternmentId(), bedId, animalId, LocalDateTime.of(2018, 10, 10, 10, 10, 10), LocalDateTime.of(2018, 10, 10, 12, 10, 10));
        eventStore.push(entity);

        BedId newBedId = bedSampleData.getSample().getId();
        entity.update(newBedId, entity.getAnimalId(), entity.getBusyAt(), LocalDateTime.of(2018, 10, 12, 10, 10, 10));

        RequestEntity<Internment> request = RequestEntity.put(URI.create("/internments")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Internment entityFromStore = eventStore.pull(Internment.class, entity.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals(newBedId, entityFromStore.getBedId());
        assertEquals(entity.getAnimalId(), entityFromStore.getAnimalId());
        assertEquals(LocalDateTime.of(2018, 10, 10, 10, 10, 10), entityFromStore.getBusyAt());
        assertEquals(LocalDateTime.of(2018, 10, 12, 10, 10, 10), entityFromStore.getBusyUntil());
    }

    @Test
    public void deleteWithSuccess() {
        BedId bedId = bedSampleData.getSample().getId();
        AnimalId animalId = animalSampleData.getSample().getId();

        Internment entity = new Internment();
        entity.register(new InternmentId(), bedId, animalId, LocalDateTime.of(2018, 10, 10, 10, 10, 10), LocalDateTime.of(2018, 10, 10, 12, 10, 10));
        eventStore.push(entity);

        HttpEntity<Internment> requestEntity = new HttpEntity<>(entity);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/internments"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Internment entityFromStore = eventStore.pull(Internment.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
