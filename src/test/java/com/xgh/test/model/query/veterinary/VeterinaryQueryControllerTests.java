package com.xgh.test.model.query.veterinary;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.veterinary.Veterinary;
import com.xgh.model.query.veterinary.VeterinaryRepository;
import com.xgh.test.model.query.Page;
import com.xgh.test.model.query.address.AddressSampleData;
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
public class VeterinaryQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressSampleData addressSampleData;

    @Autowired
    private VeterinaryRepository repository;

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

    @Test
    public void findAllWithOnePage() throws IOException {
        List<UUID> veterinarians = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            veterinarians.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/veterinarians", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Veterinary> response = new ObjectMapper().findAndRegisterModules().readValue(
                responseEntity.getBody(), new TypeReference<Page<Veterinary>>() {
                });
        for (int i = 0; i < 5; i++) {
            assertEquals(veterinarians.get(i), response.getContent().get(i).getId());
        }
    }

    private UUID createSampleEntity() {
        com.xgh.model.query.address.Address address = addressSampleData.getSample();
        Veterinary veterinary = new Veterinary(UUID.randomUUID(), "Ricardo Requena", address, "44998015821",
                "9375", "espacoanimal.vet@hotmail.com", LocalDate.parse("1986-10-03"), false);
        repository.save(veterinary);
        return veterinary.getId();
    }
}