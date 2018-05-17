package com.xgh.test.model.query.owner;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xgh.model.query.address.Address;
import com.xgh.model.query.owner.Owner;
import com.xgh.model.query.owner.OwnerRepository;
import com.xgh.test.model.query.Page;
import com.xgh.test.model.query.address.AddressSampleData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OwnerQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OwnerRepository repository;

    @Autowired
    private AddressSampleData addressSampleData;

    @Before
    public void before() {
        repository.deleteAll();
    }

    @Test
    public void findById() {
        UUID ownerId = createSampleEntity();

        ResponseEntity<Owner> response = restTemplate.getForEntity("/owners/{id}", Owner.class,
                ownerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ownerId, response.getBody().getId());
        assertEquals("Dono Master", response.getBody().getName());
        assertEquals("44313371337", response.getBody().getPhone());
        assertEquals("09450600929", response.getBody().getCpf());
        assertEquals("1911-01-01", response.getBody().getBirthDate().toString());
    }

    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> owners = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            owners.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/owners", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Owner> response = new ObjectMapper().findAndRegisterModules().readValue(
                responseEntity.getBody(), new TypeReference<Page<Owner>>() {});
        for (int i = 0; i < 5; i++) {
            assertEquals(owners.get(i), response.getContent().get(i).getId());
        }
    }

    private UUID createSampleEntity() {
        Address address = addressSampleData.getSample();
        Owner owner = new Owner(UUID.randomUUID(), "Dono Master", "09450600929", "44313371337", LocalDate.parse("1911-01-01"), address, false);
        repository.save(owner);
        return owner.getId();
    }

}
