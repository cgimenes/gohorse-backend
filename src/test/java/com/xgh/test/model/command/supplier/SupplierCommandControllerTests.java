package com.xgh.test.model.command.supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.supplier.Supplier;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;
import com.xgh.model.command.valueobjects.PostalCode;
import java.net.URI;
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

// TODO: comparar endereço
// TODO: criar teste de falha de bad request e entity not found
// TODO: verificar se os snapshots estão sendo salvos/deletados corretamente
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class SupplierCommandControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Test
    public void registerWithSuccess() {
        Supplier entity = new Supplier();
        entity.register(new SupplierId(), new Name("Nestle"), new Phone("44998015821"), "00000000191",
                new Address(new PostalCode("87005-140", "Rua", "Ruazera", "Barro", "Maringá", "PR", "Brasil"), 117,
                        null),
                new Name("Ração"));

        ResponseEntity<Void> response = restTemplate.postForEntity("/suppliers", entity, Void.class);

        Supplier entityFromStore = eventStore.pull(Supplier.class, entity.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Nestle", entityFromStore.getName().toString());
        assertEquals("44998015821", entityFromStore.getPhone().toString());
        assertEquals("00000000191", entityFromStore.getDocument().toString());
        assertEquals("/suppliers/" + entity.getId(), response.getHeaders().getLocation().getPath());
        assertEquals("1", entityFromStore.getVersion().toString());
    }

    @Test
    public void updateWithSuccess() {
        Supplier entity = new Supplier();
        entity.register(new SupplierId(), new Name("Nestle"), new Phone("44998015822"), "00000000191",
                new Address(new PostalCode("87005-140", "Rua", "Ruazera", "Barro", "Maringá", "PR", "Brasil"), 117,
                        null),
                new Name("Ração"));
        eventStore.push(entity);

        entity.update(entity.getName(), new Phone("44998731154"), entity.getDocument(), entity.getAddress(),
                entity.getDistributionType());

        RequestEntity<Supplier> request = RequestEntity.put(URI.create("/suppliers")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Supplier entityFromStore = eventStore.pull(Supplier.class, entity.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Nestle", entityFromStore.getName().toString());
        assertEquals("44998731154", entityFromStore.getPhone().toString());
        assertEquals("00000000191", entityFromStore.getDocument().toString());
        assertEquals("2", entityFromStore.getVersion().toString());
    }

    @Test
    public void deleteWithSuccess() {
        Supplier entity = new Supplier();
        entity.register(new SupplierId(), new Name("Nestle"), new Phone("44998015822"), "00000000191",
                new Address(new PostalCode("87005-140", "Rua", "Ruazera", "Barro", "Maringá", "PR", "Brasil"), 117,
                        null),
                new Name("Ração"));

        eventStore.push(entity);

        HttpEntity<Supplier> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/suppliers"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Supplier entityFromStore = eventStore.pull(Supplier.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
