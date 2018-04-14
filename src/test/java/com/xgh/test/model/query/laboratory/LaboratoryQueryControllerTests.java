package com.xgh.test.model.query.laboratory;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.xgh.model.query.address.Address;
import com.xgh.model.query.laboratory.LaboratoryRepository;
import com.xgh.test.model.query.address.AddressSampleData;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.laboratory.Laboratory;

// TODO: testar address
// TODO: criar teste de falha de bad request e entity not found
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class LaboratoryQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LaboratoryRepository repository;

    @Autowired
    private AddressSampleData addressSampleData;

    @Before
    public void before() {
        repository.deleteAll();
    }

    @Test
    public void findById() {
        UUID laboratoryId = createSampleEntity();

        ResponseEntity<Laboratory> response = restTemplate.getForEntity("/laboratories/{id}", Laboratory.class,
                laboratoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(laboratoryId, response.getBody().getId());
        assertEquals("Laboratório dos Hackers", response.getBody().getCompanyName());
        assertEquals("44313371337", response.getBody().getPhone());
    }

    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> laboratories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            laboratories.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/laboratories", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Laboratory> response = new ObjectMapper().readValue(responseEntity.getBody(),
                new TypeReference<Page<Laboratory>>() {
                });
        for (int i = 0; i < 5; i++) {
            assertEquals(laboratories.get(i), response.getContent().get(i).getId());
        }
    }

    @Test
    public void findAllWithManyPages() {
        // TODO criar
    }

    private UUID createSampleEntity() {
        Address address = addressSampleData.getSampleAddress();
        Laboratory laboratory = new Laboratory(UUID.randomUUID(), "Laboratório dos Hackers", "44313371337", address, false);
        repository.save(laboratory);
        return laboratory.getId();
    }
}
