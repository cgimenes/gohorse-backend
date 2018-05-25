package com.xgh.test.model.command.laboratory;

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
import com.xgh.model.command.laboratory.Laboratory;
import com.xgh.model.command.laboratory.LaboratoryId;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;
import com.xgh.model.command.valueobjects.PostalCode;

// TODO: comparar endereço
// TODO: criar teste de falha de bad request e entity not found
// TODO: verificar se os snapshots estão sendo salvos/deletados corretamente
// TODO: refatorar testes dos comandos para reutilizar o registro
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class LaboratoryCommandControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Test
    public void registerWithSuccess() {
        Laboratory entity = new Laboratory();
        entity.register(new LaboratoryId(), new Name("Laboratório dos Hackers"), new Phone("44313371337"), new Address(
                new PostalCode("87020-025", "Avenida", "Avenida Tiradentes", "Centro", "Maringá", "PR", "Brasil"), 587,
                null));

        ResponseEntity<Void> response = restTemplate.postForEntity("/laboratories", entity, Void.class);

        Laboratory entityFromStore = eventStore.pull(Laboratory.class, entity.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/laboratories/" + entity.getId(), response.getHeaders().getLocation().getPath());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Laboratório dos Hackers", entityFromStore.getCompanyName().toString());
        assertEquals("44313371337", entityFromStore.getPhone().toString());
        assertEquals("1", entityFromStore.getVersion().toString());
    }

    @Test
    public void updateWithSuccess() {
        Laboratory entity = new Laboratory();
        entity.register(new LaboratoryId(), new Name("Laboratório dos Hackers"), new Phone("44313371337"), new Address(
                new PostalCode("87020-025", "Avenida", "Avenida Tiradentes", "Centro", "Maringá", "PR", "Brasil"), 587,
                null));
        eventStore.push(entity);

        entity.update(new Name("Laboratório dos Noob"), new Phone("44000000000"), new Address(
                new PostalCode("87020-025", "Avenida", "Avenida Tiradentes", "Centro", "Maringá", "PR", "Brasil"), 587,
                null));

        RequestEntity<Laboratory> request = RequestEntity.put(URI.create("/laboratories")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Laboratory entityFromStore = eventStore.pull(Laboratory.class, entity.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Laboratório dos Noob", entityFromStore.getCompanyName().toString());
        assertEquals("44000000000", entityFromStore.getPhone().toString());
        assertEquals("2", entityFromStore.getVersion().toString());
    }

    @Test
    public void deleteWithSuccess() {
        Laboratory entity = new Laboratory();
        entity.register(new LaboratoryId(), new Name("Laboratório dos Hackers"), new Phone("44313371337"), new Address(
                new PostalCode("87020-025", "Avenida", "Avenida Tiradentes", "Centro", "Maringá", "PR", "Brasil"), 587,
                null));
        eventStore.push(entity);

        HttpEntity<Laboratory> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/laboratories"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Laboratory entityFromStore = eventStore.pull(Laboratory.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
