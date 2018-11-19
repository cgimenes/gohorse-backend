package com.xgh.test.model.query.operational.enumerator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.operational.enumerator.Enumerator;
import com.xgh.model.query.operational.enumerator.EnumeratorRepository;
import com.xgh.test.model.query.Page;
import org.junit.Before;
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
public class EnumeratorQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EnumeratorRepository repository;

    @Before
    public void before() {
        repository.deleteAll();
    }


    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> enumeratos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            enumeratos.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/enumerators/", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Enumerator> response = new ObjectMapper().findAndRegisterModules().readValue(
                responseEntity.getBody(), new TypeReference<Page<Enumerator>>() {
                });
        for (int i = 0; i < 5; i++) {
            assertEquals(enumeratos.get(i), response.getContent().get(i).getId());
        }
    }

    private UUID createSampleEntity() {
        Enumerator enumerator = new Enumerator(UUID.randomUUID(), "Raça", "Xitzu", false, false);
        repository.save(enumerator);
        return enumerator.getId();
    }
}
