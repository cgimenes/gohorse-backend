package com.xgh.test.model.query.supplier;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.xgh.model.query.supplier.SupplierRepository;
import com.xgh.test.model.query.Page;
import com.xgh.test.model.query.address.AddressSampleData;
import com.xgh.test.model.query.distributionType.DistributionTypeSampleData;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.model.query.supplier.Supplier;

// TODO: criar teste de falha de bad request e entity not found
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class SupplierQueryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressSampleData addressSampleData;
    
    @Autowired
    private DistributionTypeSampleData distributionTypeSampleData;    
    
    @Autowired
    private SupplierRepository repository;

    @Before
    public void before() {
        repository.deleteAll();
    }

    @Test
    public void findById() {
        UUID supplierId = createSampleEntity();

        ResponseEntity<Supplier> response = restTemplate.getForEntity("/suppliers/{id}", Supplier.class,
                supplierId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(supplierId, response.getBody().getId());
        assertEquals("Nestle", response.getBody().getName());
        assertEquals("44998015821", response.getBody().getPhone());
        assertEquals("00000000191", response.getBody().getDocument());
    }

    @Test    
    public void findAllWithOnePage() throws IOException {
        List<UUID> suppliers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            suppliers.add(createSampleEntity());
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/suppliers", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Page<Supplier> response = new ObjectMapper().findAndRegisterModules().readValue(
                responseEntity.getBody(), new TypeReference<Page<Supplier>>() {});
        for (int i = 0; i < 5; i++) {
            assertEquals(suppliers.get(i), response.getContent().get(i).getId());
        }
        
    }

    @Test
    public void findAllWithManyPages() {
        // TODO criar
    }

    private UUID createSampleEntity() {
        com.xgh.model.query.address.Address address = addressSampleData.getSample();
        com.xgh.model.query.distributionType.DistributionType distributionType = distributionTypeSampleData.getSample();
        Supplier supplier = new Supplier(UUID.randomUUID(), "Nestle", "44998015821",
                "00000000191", address, distributionType, false);
        repository.save(supplier);
        return supplier.getId();
    }
}
