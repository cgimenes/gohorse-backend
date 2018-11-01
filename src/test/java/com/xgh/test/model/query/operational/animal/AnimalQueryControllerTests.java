package com.xgh.test.model.query.operational.animal;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.operational.animal.Animal;
import com.xgh.test.model.query.Page;
import java.io.IOException;
import java.time.LocalDate;
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
public class AnimalQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AnimalSampleData animalSampleData;

    @Test
    public void findById() {
        UUID animalId = animalSampleData.getSample().getId();

        ResponseEntity<Animal> response = restTemplate.getForEntity("/animals/{id}", Animal.class,
                animalId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(animalId, response.getBody().getId());
        assertEquals("Farelo", response.getBody().getName());
        assertEquals("Tremedeira", response.getBody().getBreed().getName());
        assertEquals("Catioro", response.getBody().getSpecie().getName());
        assertEquals("Dono Master", response.getBody().getOwner().getName());
        assertEquals("M", response.getBody().getSex().toString());
        assertEquals(LocalDate.of(2012, 12, 12), response.getBody().getBirthDate());
        assertEquals(new Float(100.50), response.getBody().getWeight());
        assertEquals(false, response.getBody().isCastrated());
    }

    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> animals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            animals.add(animalSampleData.getSample().getId());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/animals", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Animal> response = new ObjectMapper().findAndRegisterModules().readValue(
                responseEntity.getBody(),
                new TypeReference<Page<Animal>>() {
                });
        for (int i = 0; i < 5; i++) {
            assertEquals(animals.get(i), response.getContent().get(i).getId());
        }
    }
}
