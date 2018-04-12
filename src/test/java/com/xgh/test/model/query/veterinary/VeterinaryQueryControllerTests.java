package com.xgh.test.model.query.veterinary;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.xgh.model.command.veterinary.VeterinaryId;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Crmv;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Email;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;
import com.xgh.model.command.valueobjects.PostalCode;
import com.xgh.model.query.veterinary.Veterinary;

// TODO: criar teste de falha de bad request e entity not found
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class VeterinaryQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostgresEventStore eventStore;

    @Autowired
    protected JdbcTemplate connection;

    @Before
    public void before() {
        connection.update("truncate table veterinary");
    }

    @Test
    public void findById() {
        UUID veterinaryId = createSampleEntity();

        ResponseEntity<Veterinary> response = restTemplate.getForEntity("/veterinarians/{id}", Veterinary.class,
                veterinaryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veterinaryId, response.getBody().getId());
        assertEquals("Ricardo Requena", response.getBody().getName());
        assertEquals("44998015821", response.getBody().getPhone());
        assertEquals("9375", response.getBody().getCrmv());
        assertEquals("espacoanimal.vet@hotmail.com", response.getBody().getEmail());
        assertEquals("1986-10-03", response.getBody().getBirthDate().toString());
    }

    // TODO corrigir
    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> veterinarians = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            veterinarians.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/veterinarians", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Veterinary> response = new ObjectMapper().readValue(responseEntity.getBody(),
                new TypeReference<Page<Veterinary>>() {
                });
        for (int i = 0; i < 5; i++) {
            assertEquals(veterinarians.get(i), response.getContent().get(i).getId());
        }
    }

    @Test
    public void findAllWithManyPages() {
        // TODO criar
    }

    private UUID createSampleEntity() {
        com.xgh.model.command.veterinary.Veterinary veterinary = new com.xgh.model.command.veterinary.Veterinary();
        veterinary.register(new VeterinaryId(), new Name("Ricardo Requena"),
                new Address(new PostalCode("87043-050", "Rua", "Rio Andaraí", "Oásis", "Maringá", "PR", "Brasil"), 374,
                        null),
                new Phone("44998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
                new Date(LocalDate.of(1986, 10, 03)));
        eventStore.push(veterinary);
        return veterinary.getId().getValue();
    }
}