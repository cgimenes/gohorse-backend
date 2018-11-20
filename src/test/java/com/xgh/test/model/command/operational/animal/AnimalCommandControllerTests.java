package com.xgh.test.model.command.operational.animal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.animal.Animal;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.enumerator.Enumerator;
import com.xgh.model.command.operational.owner.Owner;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Sex;
import com.xgh.test.model.command.operational.enumerator.EnumeratorSampleData;
import com.xgh.test.model.command.operational.owner.OwnerSampleData;
import java.net.URI;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AnimalCommandControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Autowired
    private OwnerSampleData ownerSampleData;
    
    @Autowired
    private EnumeratorSampleData enumSampleData;

    @Autowired
    private AnimalSampleData animalSampleData;

    @Test
    public void registerWithSuccess() {
        Animal entity = new Animal();
        Owner owner = ownerSampleData.getSample();
        Enumerator breed = enumSampleData.getSample();
        Enumerator specie = enumSampleData.getSample();


        entity.register(new AnimalId(), new Name("Severino"), owner.getId(), breed.getId(),
        		specie.getId(), Sex.MALE, LocalDate.of(1001, 01, 01),
                new Float(35), false);

        ResponseEntity<Void> response = restTemplate.postForEntity("/animals", entity, Void.class);

        Animal entityFromStore = eventStore.pull(Animal.class, entity.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/animals/" + entity.getId(), response.getHeaders().getLocation().getPath());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Severino", entityFromStore.getName().toString());
        assertEquals(owner.getId().getValue(), entityFromStore.getOwner().getValue());
        assertEquals(breed.getId(), entityFromStore.getBreed());
        assertEquals(specie.getId(), entityFromStore.getSpecie());
        assertEquals("MALE", entityFromStore.getSex().toString());
        assertEquals(LocalDate.of(1001, 01, 01), entityFromStore.getBirthDate());
        assertEquals(new Float(35), entityFromStore.getWeight());
        assertEquals(false, entityFromStore.isCastrated());
        assertEquals("1", entityFromStore.getVersion().toString());
    }

    @Test
    public void updateWithSuccess() {
        Animal entity = animalSampleData.getSample();

        entity.update(new Name("Severino Benner"), entity.getOwner(), entity.getBreed(),
        		entity.getSpecie(), Sex.MALE, LocalDate.of(1002, 02, 02),
                new Float(10), false);

        RequestEntity<Animal> request = RequestEntity.put(URI.create("/animals")).body(entity);
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        Animal entityFromStore = eventStore.pull(Animal.class, entity.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(entity.equals(entityFromStore));
        assertEquals("Severino Benner", entityFromStore.getName().toString());
        assertEquals(entity.getOwner(), entityFromStore.getOwner());
        assertEquals(entity.getBreed(), entityFromStore.getBreed());
        assertEquals(entity.getSpecie(), entityFromStore.getSpecie());
        assertEquals("MALE", entityFromStore.getSex().toString());
        assertEquals(LocalDate.of(1002, 02, 02), entityFromStore.getBirthDate());
        assertEquals(new Float(10), entityFromStore.getWeight());
        assertEquals(false, entityFromStore.isCastrated());
        assertEquals("2", entityFromStore.getVersion().toString());
    }

    @Test
    public void deleteWithSuccess() {
        Animal entity = animalSampleData.getSample();

        HttpEntity<Animal> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(URI.create("/animals"), HttpMethod.DELETE,
                requestEntity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Animal entityFromStore = eventStore.pull(Animal.class, entity.getId());

        assertTrue(entityFromStore.isDeleted());
    }
}
