package com.xgh.test.model.query.internment;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.animal.Animal;
import com.xgh.model.query.bed.Bed;
import com.xgh.model.query.internment.Internment;
import com.xgh.model.query.internment.InternmentRepository;
import com.xgh.test.model.query.Page;
import com.xgh.test.model.query.animal.AnimalSampleData;
import com.xgh.test.model.query.bed.BedSampleData;
import java.io.IOException;
import java.time.LocalDateTime;
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
public class InternmentQueryControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InternmentRepository repository;

    @Autowired
    private BedSampleData bedSampleData;

    @Autowired
    private AnimalSampleData animalSampleData;

    @Test
    public void findById() {
        UUID internmentId = createSampleEntity();
        ResponseEntity<Internment> response = restTemplate.getForEntity("/internments/{id}", Internment.class,
                internmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(LocalDateTime.of(2018, 10, 10, 10, 10, 10), response.getBody().getBusyAt());
        assertEquals(LocalDateTime.of(2018, 10, 10, 12, 10, 10), response.getBody().getBusyUntil());
    }

    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> internments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            internments.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/internments", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Internment> response = new ObjectMapper().findAndRegisterModules().readValue(responseEntity.getBody(),
                new TypeReference<Page<Internment>>() {
                });
        for (int i = 0; i < 5; i++) {
            assertEquals(internments.get(i), response.getContent().get(i).getId());
        }
    }

    private UUID createSampleEntity() {
        Bed bed = bedSampleData.getSample();
        Animal animal = animalSampleData.getSample();

        Internment internment = new Internment(UUID.randomUUID(),
                bed,
                animal,
                LocalDateTime.of(2018, 10, 10, 10, 10, 10),
                LocalDateTime.of(2018, 10, 10, 12, 10, 10),
                false);
        repository.save(internment);
        return internment.getId();
    }
}
