package com.xgh.test.model.query.operational.surgery;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.operational.surgery.Surgery;
import com.xgh.test.model.query.Page;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SurgeryQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SurgerySampleData surgerySampleData;

    @Test
    public void findById() {
    	Surgery entity = surgerySampleData.getSample();

        ResponseEntity<Surgery> response = restTemplate.getForEntity("/surgeries/{id}", Surgery.class,
                entity.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entity.getId(), response.getBody().getId());
        assertEquals(entity.getAnimal().getId(), response.getBody().getAnimal().getId());
        assertEquals(entity.getDateTime(), response.getBody().getDateTime());
        assertEquals(entity.getVeterinary().getId(), 
        		response.getBody().getVeterinary().getId());
        assertEquals(entity.getStatus(), response.getBody().getStatus());
        assertEquals(entity.getDeleted(), response.getBody().getDeleted());
    }

    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> surgeries = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
        	surgeries.add(surgerySampleData.getSample().getId());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/surgeries", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Surgery> response = new ObjectMapper().findAndRegisterModules().readValue(
                responseEntity.getBody(),
                new TypeReference<Page<Surgery>>() {
                });
        for (int i = 0; i < 5; i++) {
            assertEquals(surgeries.get(i), response.getContent().get(i).getId());
        }
    }
}
