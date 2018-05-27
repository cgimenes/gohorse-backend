package com.xgh.test.model.command.internment;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.internment.Internment;
import com.xgh.model.command.internment.InternmentId;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.test.model.command.animal.AnimalSampleData;
import com.xgh.test.model.command.bed.BedSampleData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        entity.register(new InternmentId(), bedId, animalId, new Date("2018-04-20"), new Date("2018-04-25"));

        ResponseEntity<Void> response = restTemplate.postForEntity(URI.create("/internments"), entity, Void.class);

        Internment entityFromStore = eventStore.pull(Internment.class, entity.getId());

        assertTrue(entity.equals(entityFromStore));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(entity.getId(), entityFromStore.getId());
        assertEquals(entity.getBedId(), entityFromStore.getBedId());
        assertEquals(entity.getAnimalId(), entityFromStore.getAnimalId());
        assertEquals(new Date("2018-04-20"), entityFromStore.getBusyAt());
        assertEquals(new Date("2018-04-25"), entityFromStore.getBusyUntil());
        assertEquals("/internments/" + entity.getId(), response.getHeaders().getLocation().getPath());
    }

    @Test
    public void updateWithSuccess() {
        BedId bedId = bedSampleData.getSample().getId();
        AnimalId animalId = animalSampleData.getSample().getId();

        Internment entity = new Internment();
        entity.register(new InternmentId(), bedId, animalId, new Date("2018-04-20"), new Date("2018-04-25"));
        eventStore.push(entity);

        BedId newBedId = bedSampleData.getSample().getId();
        entity.update(newBedId, entity.getAnimalId(), entity.getBusyAt(), new Date("2018-04-28"));

        RequestEntity<Internment> request = RequestEntity.put(URI.create("/internments")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Internment entityFromStore = eventStore.pull(Internment.class, entity.getId());

        assertTrue(entity.equals(entityFromStore));
        assertEquals(newBedId, entityFromStore.getBedId());
        assertEquals(new Date("2018-04-28"), entityFromStore.getBusyUntil());
        assertEquals(entity.getAnimalId(), entityFromStore.getAnimalId());
        assertEquals(new Date("2018-04-20"), entityFromStore.getBusyAt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteWithSuccess() {
        BedId bedId = bedSampleData.getSample().getId();
        AnimalId animalId = animalSampleData.getSample().getId();

        Internment entity = new Internment();
        entity.register(new InternmentId(), bedId, animalId, new Date("2018-04-20"), new Date("2018-04-25"));
        eventStore.push(entity);

        HttpEntity<Internment> requestEntity = new HttpEntity<>(entity);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/internments"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Internment entityFromStore = eventStore.pull(Internment.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
