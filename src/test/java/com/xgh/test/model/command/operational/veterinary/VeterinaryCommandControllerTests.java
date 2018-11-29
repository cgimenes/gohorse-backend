package com.xgh.test.model.command.operational.veterinary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Crmv;
import com.xgh.model.command.operational.valueobjects.Email;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import com.xgh.model.command.operational.valueobjects.PostalCode;
import com.xgh.model.command.operational.veterinary.Veterinary;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.net.URI;
import java.time.LocalDate;
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

public class VeterinaryCommandControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Test
    public void registerWithSuccess() {
        Veterinary entity = new Veterinary();
        entity.register(new VeterinaryId(), new Name("Ricardo Requena"),
                new Address(new PostalCode("87043-050", "Rio Andaraí", "Oásis", "Maringá", "PR", "Brasil"), 374,
                        null),
                new Phone("44998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
                LocalDate.of(1986, 10, 03));

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
                new Address(new PostalCode("87043-050", "Rio Andaraí", "Oásis", "Maringá", "PR", "Brasil"), 374,
                        null),
                new Phone("44998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
                LocalDate.of(1986, 10, 03));
        eventStore.push(entity);

        entity.update(entity.getName(), entity.getAddress(), new Phone("44998731154"), entity.getCrmv(),
                new Email("ricardo.requena@hotmail.com"),
                LocalDate.of(1986, 10, 03));

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
                new Address(new PostalCode("87043-050", "Rio Andaraí", "Oásis", "Maringá", "PR", "Brasil"), 374,
                        null),
                new Phone("44998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
                LocalDate.of(1986, 10, 03));

        eventStore.push(entity);

        HttpEntity<Veterinary> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/veterinarians"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Veterinary entityFromStore = eventStore.pull(Veterinary.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());

    }
}