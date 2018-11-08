package com.xgh.test.model.command.enumerator;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.enumerator.Enumerator;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.valueobjects.Description;
import com.xgh.model.command.operational.valueobjects.Name;

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
public class EnumeratorCommandControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Test
    public void registerWithSuccess() {

        Enumerator entity = new Enumerator();
        entity.register(new EnumeratorId(), new Name("Raça"), new Description("XITZU"));

        ResponseEntity<Void> response = restTemplate.postForEntity("/enumerators/", entity, Void.class);

        Enumerator entityFromStore = eventStore.pull(Enumerator.class, entity.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Raça", entityFromStore.getKind().toString());
        assertEquals("XITZU", entityFromStore.getName().toString());
        assertEquals("1", entityFromStore.getVersion().toString());
    }

    @Test
    public void updateWithSuccess() {
        Enumerator entity = new Enumerator();
        entity.register(new EnumeratorId(), new Name("Raça"), new Description("XITZU"));
        eventStore.push(entity);

        entity.update(entity.getKind(), new Description("CHITISSU"));

        RequestEntity<Enumerator> request = RequestEntity.put(URI.create("/enumerators/")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Enumerator entityFromStore = eventStore.pull(Enumerator.class, entity.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Raça", entityFromStore.getKind().toString());
        assertEquals("CHITISSU", entityFromStore.getName().toString());
        assertEquals("2", entityFromStore.getVersion().toString());
    }

    @Test
    public void deleteWithSuccess() {
        Enumerator entity = new Enumerator();
        entity.register(new EnumeratorId(), new Name("Raça"), new Description("XITZU"));
        eventStore.push(entity);

        HttpEntity<Enumerator> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/enumerators"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Enumerator entityFromStore = eventStore.pull(Enumerator.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
