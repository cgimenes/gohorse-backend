package com.xgh.test.model.query.animal;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.query.animal.Animal;
import com.xgh.model.query.animal.AnimalRepository;
import com.xgh.test.model.query.Page;
import com.xgh.test.model.query.breed.BreedSampleData;
import com.xgh.test.model.query.owner.OwnerSampleData;
import com.xgh.test.model.query.specie.SpecieSampleData;
import org.junit.After;
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

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AnimalQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    protected AnimalRepository repository;

    @Autowired
    private OwnerSampleData ownerSampleData;

    @Autowired
    private BreedSampleData breedSampleData;

    @Autowired
    private SpecieSampleData specieSampleData;

    @After
    @Before
    public void cleanRepository() {
        repository.deleteAll();
    }

    @Test
    public void findById() {
        UUID animalId = createSampleEntity();

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
            animals.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/animals", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Animal> response = new ObjectMapper().findAndRegisterModules().readValue(
                responseEntity.getBody(),
                new TypeReference<Page<Animal>>() {});
        for (int i = 0; i < 5; i++) {
            assertEquals(animals.get(i), response.getContent().get(i).getId());
        }
    }

    private UUID createSampleEntity() {
        Animal entity = new Animal(
                UUID.randomUUID(),
                "Farelo",
                ownerSampleData.getSample(),
                breedSampleData.getSample(),
                specieSampleData.getSample(),
                'M',
                LocalDate.of(2012, 12, 12),
                false,
                new Float(100.50),
                false);
        repository.save(entity);
        return entity.getId();
    }
}
