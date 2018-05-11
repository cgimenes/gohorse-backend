package com.xgh.test.model.query.Animal;


import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Sex;
import com.xgh.model.query.animal.Animal;
import com.xgh.test.model.command.owner.OwnerSampleData;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AnimalQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    protected JdbcTemplate connection;
    
    @Autowired
    private OwnerSampleData ownerSampleData;

    @Before
    public void before() {
        connection.update("truncate table animal");
    }

    @Test
    public void findById() {
        UUID animalId = createSampleEntity();

        ResponseEntity<Animal> response = restTemplate.getForEntity("/animals/{id}", Animal.class,
                animalId);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(animalId, response.getBody().getId());
        assertEquals("Severino", response.getBody().getName().toString());
        assertEquals("Xinauzer", response.getBody().getBreed().toString());
        assertEquals("Cachorro", response.getBody().getSpecie().toString());
        assertEquals("M", response.getBody().getSex().toString());
        assertEquals(new Date(LocalDate.of(1001, 01, 01)), response.getBody().getBirthDate());
        assertEquals(new Float(35), response.getBody().getWeight());
        assertEquals(false, response.getBody().isCastrated());
    }

    @Test
    public void findAllWithOnePage() throws JsonParseException, JsonMappingException, IOException {
        List<UUID> animals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            animals.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/animals", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Animal> response = new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<Page<Animal>>() {});
        for (int i = 0; i < 5; i++) {
            assertEquals(animals.get(i), response.getContent().get(i).getId());
        }
    }

    private UUID createSampleEntity() {
    	com.xgh.model.command.animal.Animal animal = new com.xgh.model.command.animal.Animal();
        Owner owner = ownerSampleData.getSample();

        animal.register(new AnimalId(), new Name("Severino"),owner.getId(), new Name("Xinauzer"), 
				new Name("Cachorro"), new Sex('M'), new Date(LocalDate.of(1001, 01, 01)), 
				new Float(35),false);
        return animal.getId().getValue();
    }

}
