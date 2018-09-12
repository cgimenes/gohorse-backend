package com.xgh.test.model.query.operational.bed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.operational.bed.Bed;
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
public class BedQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BedSampleData bedSampleData;

    @Test
    public void findById() {
        UUID bedId = bedSampleData.getSample().getId();

        ResponseEntity<Bed> response = restTemplate.getForEntity("/bed/{id}", Bed.class, bedId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bedId, response.getBody().getId());
        assertEquals("021", response.getBody().getCode());
    }

    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> bed = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bed.add(bedSampleData.getSample().getId());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/bed", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Bed> response = new ObjectMapper().readValue(responseEntity.getBody(),
                new TypeReference<Page<Bed>>() {
                });
        for (int i = 0; i < 5; i++) {
            assertEquals(bed.get(i), response.getContent().get(i).getId());
        }
    }

    @Test
    public void findAllWithManyPages() {
        // TODO criar
    }
}
