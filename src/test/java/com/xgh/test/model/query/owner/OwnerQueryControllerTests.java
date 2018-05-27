package com.xgh.test.model.query.owner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.owner.Owner;
import com.xgh.test.model.query.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OwnerQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OwnerSampleData ownerSampleData;

    @Test
    public void findById() {
        UUID ownerId = ownerSampleData.getSample().getId();

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
            owners.add(ownerSampleData.getSample().getId());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/owners", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Owner> response = new ObjectMapper().findAndRegisterModules().readValue(
                responseEntity.getBody(), new TypeReference<Page<Owner>>() {
                });
        for (int i = 0; i < 5; i++) {
            assertEquals(owners.get(i), response.getContent().get(i).getId());
        }
    }
}
