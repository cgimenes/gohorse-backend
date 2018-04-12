package com.xgh.test.model.query.owner;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.owner.Owner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OwnerQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    protected JdbcTemplate connection;

    @Autowired
    private PostgresEventStore eventStore;

    @Before
    public void before() {
        connection.update("truncate table owner");
    }

    @Test
    public void findById() {
        UUID ownerId = createSampleEntity();

        ResponseEntity<Owner> response = restTemplate.getForEntity("/owners/{id}", Owner.class,
                ownerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ownerId, response.getBody().getId().getValue());
        assertEquals("Dono Master", response.getBody().getName().toString());
        assertEquals("44313371337", response.getBody().getPhone().toString());
        assertEquals("09450600929", response.getBody().getCpf().toString());
        assertEquals("1911-01-01", response.getBody().getBirthDate().toString());
    }

    @Test
    public void findAllWithOnePage() throws JsonParseException, JsonMappingException, IOException {
        List<UUID> owners = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            owners.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/owners", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Owner> response = new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<Page<Owner>>() {});
        for (int i = 0; i < 5; i++) {
            assertEquals(owners.get(i), response.getContent().get(i).getId());
        }
    }

    private UUID createSampleEntity() {
        Owner owner = new Owner();
        Address address = new Address(new PostalCode("11111-222", "Rua", "Das gaivotas", "Jardim dos Passaros", "Maringá", "PR", "Brasil"),
                389,null);

        owner.register(new OwnerId(), new Name("Dono Master"), new Phone("44313371337"), new Cpf("09450600929"), new Date(LocalDate.of(1911, 1, 1)), address);
        eventStore.push(owner);
        return owner.getId().getValue();
    }

}
