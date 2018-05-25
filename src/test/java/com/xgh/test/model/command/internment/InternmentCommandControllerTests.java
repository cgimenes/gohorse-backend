package com.xgh.test.model.command.internment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.net.URI;
import org.junit.Before;
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
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.internment.Internment;
import com.xgh.model.command.internment.InternmentId;
import com.xgh.model.command.valueobjects.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class InternmentCommandControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Before
    public void before() {

    }

    @Test
    public void registerWithSuccess() {
        Internment entity = new Internment();
        entity.register(new InternmentId(), new BedId(), new AnimalId(), new Date("2018-04-20"),
                new Date("2018-04-25"));

        ResponseEntity<Void> response = restTemplate.postForEntity(URI.create("/internments"), entity, Void.class);

        Internment entityFromStore = eventStore.pull(Internment.class, entity.getId());

        assertTrue(entity.equals(entityFromStore));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(entity.getId(), entityFromStore.getId());
        assertEquals(entity.getBedId(), entityFromStore.getBedId());
        assertEquals(entity.getAnimalId(), entityFromStore.getAnimalId());
        assertEquals(new Date("2018-04-20"), entityFromStore.getBusyAt());
        assertEquals(new Date("2018-04-25"), entityFromStore.getBusyUntil());
        assertEquals("/internment/" + entity.getId(), response.getHeaders().getLocation().getPath());
    }

    @Test
    public void updateWithSuccess() {
        Internment entity = new Internment();
        entity.register(new InternmentId(), new BedId(), new AnimalId(), new Date("2018-04-20"),
                new Date("2018-04-25"));
        eventStore.push(entity);

        BedId newBed = new BedId();

        entity.update(newBed, entity.getAnimalId(), entity.getBusyAt(), new Date("2018-04-28"));

        RequestEntity<Internment> request = RequestEntity.put(URI.create("/internments")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Internment entityFromStore = eventStore.pull(Internment.class, entity.getId());

        assertTrue(entity.equals(entityFromStore));
        assertEquals(newBed, entityFromStore.getBedId());
        assertEquals(new Date("2018-04-28"), entityFromStore.getBusyUntil());
        assertEquals(entity.getAnimalId(), entityFromStore.getAnimalId());
        assertEquals(new Date("2018-04-20"), entityFromStore.getBusyAt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteWithSuccess() {
        Internment entity = new Internment();
        entity.register(new InternmentId(), new BedId(), new AnimalId(), new Date("2018-04-20"),
                new Date("2018-04-25"));
        eventStore.push(entity);

        HttpEntity<Internment> requestEntity = new HttpEntity<>(entity);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/internments"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Internment entityFromStore = eventStore.pull(Internment.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
