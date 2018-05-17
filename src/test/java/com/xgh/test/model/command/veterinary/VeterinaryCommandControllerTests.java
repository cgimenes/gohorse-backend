package com.xgh.test.model.command.veterinary;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.valueobjects.*;
import com.xgh.model.command.veterinary.Veterinary;
import com.xgh.model.command.veterinary.VeterinaryId;
import org.junit.Before;
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
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")

public class VeterinaryCommandControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Before
    public void before() {

    }

    @Test
    public void registerWithSuccess() {
        Veterinary entity = new Veterinary();
        entity.register(new VeterinaryId(), new Name("Ricardo Requena"),
                new Address(new PostalCode("87043-050", "Rua", "Rio Andaraí", "Oásis", "Maringá", "PR", "Brasil"), 374,
                        null),
                new Phone("44998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
                new Date(LocalDate.of(1986, 10, 03)));

        ResponseEntity<Void> response = restTemplate.postForEntity("/veterinarians", entity, Void.class);

        Veterinary entityFromStore = eventStore.pull(Veterinary.class, entity.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Ricardo Requena", entityFromStore.getName().toString());
        assertEquals("44998015821", entityFromStore.getPhone().toString());
        assertEquals("9375", entityFromStore.getCrmv().toString());
        assertEquals("espacoanimal.vet@hotmail.com", entityFromStore.getEmail().toString());
        assertEquals("/veterinarians/" + entity.getId(), response.getHeaders().getLocation().getPath());
        assertEquals("1", entityFromStore.getVersion().toString());
    }

    @Test
    public void updateWithSuccess() {
        Veterinary entity = new Veterinary();
        entity.register(new VeterinaryId(), new Name("Ricardo Requena"),
                new Address(new PostalCode("87043-050", "Rua", "Rio Andaraí", "Oásis", "Maringá", "PR", "Brasil"), 374,
                        null),
                new Phone("44998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
                new Date(LocalDate.of(1986, 10, 03)));
        eventStore.push(entity);

        entity.update(entity.getName(), entity.getAddress(), new Phone("44998731154"), entity.getCrmv(),
                new Email("ricardo.requena@hotmail.com"),
                new Date(LocalDate.of(1986, 10, 03)));

        RequestEntity<Veterinary> request = RequestEntity.put(URI.create("/veterinarians")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Veterinary entityFromStore = eventStore.pull(Veterinary.class, entity.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Ricardo Requena", entityFromStore.getName().toString());
        assertEquals("44998731154", entityFromStore.getPhone().toString());
        assertEquals("9375", entityFromStore.getCrmv().toString());
        assertEquals("ricardo.requena@hotmail.com", entityFromStore.getEmail().toString());
        assertEquals("2", entityFromStore.getVersion().toString());
    }

    @Test
    public void deleteWithSuccess() {
        Veterinary entity = new Veterinary();
        entity.register(new VeterinaryId(), new Name("Ricardo Requena"),
                new Address(new PostalCode("87043-050", "Rua", "Rio Andaraí", "Oásis", "Maringá", "PR", "Brasil"), 374,
                        null),
                new Phone("44998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
                new Date(LocalDate.of(1986, 10, 03)));

        eventStore.push(entity);

        HttpEntity<Veterinary> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/veterinarians"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Veterinary entityFromStore = eventStore.pull(Veterinary.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());

    }
}